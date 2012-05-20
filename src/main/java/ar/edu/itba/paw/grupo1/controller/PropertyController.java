package ar.edu.itba.paw.grupo1.controller;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import ar.edu.itba.paw.grupo1.controller.exception.InvalidParameterException;
import ar.edu.itba.paw.grupo1.controller.exception.PermissionDeniedException;
import ar.edu.itba.paw.grupo1.model.Picture;
import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.model.Property.Services;
import ar.edu.itba.paw.grupo1.model.User;
import ar.edu.itba.paw.grupo1.service.PictureService;
import ar.edu.itba.paw.grupo1.service.PropertyService;
import ar.edu.itba.paw.grupo1.web.PropertyForm;
import ar.edu.itba.paw.grupo1.web.Service;

@Controller
@RequestMapping(value="property")
public class PropertyController extends BaseController {

	protected PropertyService propertyService;
	protected PictureService pictureService;
	
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		binder.registerCustomEditor(Set.class, "services", new CustomCollectionEditor(Set.class, true));
	}
	
	@Autowired
	public PropertyController(PropertyService propertyService, PictureService pictureService) {
		this.propertyService = propertyService;
		this.pictureService = pictureService;
	}
	
	@RequestMapping(value="add", method = RequestMethod.GET)
	protected ModelAndView addGet() 
			throws ServletException, IOException {

		ModelAndView mav = new ModelAndView();
		mav.addObject("propertyForm", new PropertyForm());
		mav.addObject("services", getServices(new Property(), null));
		
		return render("editProperty.jsp", "Add Property", mav);
	}

	@RequestMapping(value="add", method = RequestMethod.POST)
	protected ModelAndView addPost(HttpServletRequest req, @Valid PropertyForm propertyForm, Errors errors) 
			throws ServletException, IOException {

		ModelAndView mav = new ModelAndView();
		Property property = propertyForm.build(getLoggedInUser(req));
		
		if (errors.hasErrors()) {
			mav.addObject("services", getServices(new Property(), req));
			return render("editProperty.jsp", "Edit Property", mav);
		}
		
		property.publish();
		propertyService.save(property, getLoggedInUser(req));
		RedirectView view = new RedirectView("/property/list",true);
		return new ModelAndView(view);
	}
	
	@RequestMapping(value="edit", method = RequestMethod.GET)
	protected ModelAndView editGet(HttpServletRequest req, @RequestParam("id") int id) 
					throws ServletException, IOException {
		
		Property property = propertyService.getById(id);
		List<Picture> pictures = pictureService.getByPropId(id);
		ModelAndView mav = new ModelAndView();
		
		if (property == null) {
			throw new InvalidParameterException();
		} else if (!isMine(req, property)) {
			throw new PermissionDeniedException();
		}
		
		mav.addObject("edit", 1);
		mav.addObject("propertyForm", new PropertyForm(property));
		mav.addObject("pictures", pictures);				
		mav.addObject("services", getServices(property, null));

		return render("editProperty.jsp", "Edit Property", mav);
	}

	@RequestMapping(value="edit", method = RequestMethod.POST)
	protected ModelAndView editPost(HttpServletRequest req, 
			@Valid PropertyForm propertyForm, Errors errors, @RequestParam("id") int id) 
			throws ServletException, IOException {

		ModelAndView mav = new ModelAndView();
		
		Property property = propertyService.getById(id);
		if (property == null) {
			throw new InvalidParameterException();
		} else if (!isMine(req, property)) {
			throw new PermissionDeniedException();
		}
		
		if (errors.hasErrors()) {
			mav.addObject("edit", 1);
			mav.addObject("services", getServices(property, req));
			return render("editProperty.jsp", "Edit Property", mav);
		}
		
		propertyForm.update(property);
		propertyService.save(property, getLoggedInUser(req));
		
		RedirectView view = new RedirectView("/property/list",true);
		return new ModelAndView(view);
	}

	
	
	@RequestMapping(value="showDetail",method = RequestMethod.GET)
	protected ModelAndView showDetail(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		ModelAndView mav = new ModelAndView();
		
		if (checkIntegerParameter(req, "id")) {

			int id = Integer.parseInt(req.getParameter("id"));
			Property property = propertyService.getById(id);
			List<Picture> pictures = pictureService.getByPropId(id);

			if (property == null) {
				RedirectView view = new RedirectView("/query",true);
				return new ModelAndView(view);
			} else if (!property.isPublished() && (!isLoggedIn(req) || !isMine(req, property))) {
				RedirectView view = new RedirectView("/query?unpublished=true",true);
				return new ModelAndView(view);
			}

			req.setAttribute("property", property);
			req.setAttribute("services", getServices(property, null));
			if (pictures.size() > 0) {
				req.setAttribute("pictures", pictures);
			}
		} else {
			throw new InvalidParameterException();
		}
		
		return render(req, resp, "propertyDetail.jsp", "Property Detail", mav);
	}
	
	@RequestMapping(value="list",method = RequestMethod.GET)
	protected ModelAndView list(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		ModelAndView mav = new ModelAndView();
		User user = getLoggedInUser(req);		
		req.setAttribute("properties", propertyService.getProperties(user.getId()));
		return render(req, resp, "listProperties.jsp", "List Properties", mav);
	}	
	
	@RequestMapping(value="publish", method = RequestMethod.GET)
	protected ModelAndView publish(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		if (checkIntegerParameter(req, "id")) {
			int id = Integer.parseInt(req.getParameter("id"));
			Property property = propertyService.getById(id);
			
			if (property == null) {
				throw new InvalidParameterException();
			} else if (!isMine(req, property)) {
				throw new PermissionDeniedException();
			}
			
			property.publish();
			propertyService.save(property, getLoggedInUser(req));			
		}
		
		RedirectView view = new RedirectView("/property/list",true);
		return new ModelAndView(view);
	}
	
	@RequestMapping(value="unpublish",method = RequestMethod.GET)
	protected ModelAndView unpublish(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		if (checkIntegerParameter(req, "id")) {
			int id = Integer.parseInt(req.getParameter("id"));
			Property property = propertyService.getById(id);
			
			if (property == null) {
				throw new InvalidParameterException();
			} else if (!isMine(req, property)) {
				throw new PermissionDeniedException();
			}
			
			property.unpublish();
			propertyService.save(property, getLoggedInUser(req));			
		}
		
		RedirectView view = new RedirectView("/property/list",true);
		return new ModelAndView(view);
	}
	
	@RequestMapping(value="reserve", method = RequestMethod.GET)
	protected ModelAndView reserve(HttpServletRequest req, @RequestParam("id") int id)
			throws ServletException, IOException {

		Property property = propertyService.getById(id);
		
		if (property == null) {
			throw new InvalidParameterException();
		} else if (!isMine(req, property)) {
			throw new PermissionDeniedException();
		}
		
		property.reserve();
		//TODO: When hibernate flushes by itself next line should begone
		propertyService.save(property, getLoggedInUser(req));			
		
		RedirectView view = new RedirectView("/property/list",true);
		return new ModelAndView(view);
	}
	
	@RequestMapping(value="unreserve",method = RequestMethod.GET)
	protected ModelAndView unreserve(HttpServletRequest req, @RequestParam("id") int id)
			throws ServletException, IOException {
		
		
		Property property = propertyService.getById(id);
		
		if (property == null) {
			throw new InvalidParameterException();
		} else if (!isMine(req, property)) {
			throw new PermissionDeniedException();
		}
			
		property.unreserve();
		//TODO: When hibernate flushes by itself next line should begone
		propertyService.save(property, getLoggedInUser(req));			
		
		RedirectView view = new RedirectView("/property/list",true);
		return new ModelAndView(view);
	}
	
	private SortedSet<Service> getServices(Property property, HttpServletRequest postReq) {
		Comparator<Service> comparator = new Comparator<Service>() {
			public int compare(Service a, Service b) {
				return a.getName().compareTo(b.getName());
			}
		};
		
		SortedSet<Service> services = new TreeSet<Service>(comparator);
		
		if (postReq != null) {
			for (Services service: Services.values()) {
				services.add(new Service(service.toString(), postReq.getParameter(service.toString()) != null));
			}
		} else {
			Set<Services> propertyServices = property.getServices();
			for (Services service : Services.values()) {
				services.add(new Service(service.toString(), propertyServices.contains(service)));
			}
		}
		return services;
	}
}
