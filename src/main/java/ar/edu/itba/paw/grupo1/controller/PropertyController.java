package ar.edu.itba.paw.grupo1.controller;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.grupo1.controller.exception.InvalidParameterException;
import ar.edu.itba.paw.grupo1.controller.exception.PermissionDeniedException;
import ar.edu.itba.paw.grupo1.model.Picture;
import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.model.Property.Services;
import ar.edu.itba.paw.grupo1.model.Room;
import ar.edu.itba.paw.grupo1.model.User;
import ar.edu.itba.paw.grupo1.repository.PictureRepository;
import ar.edu.itba.paw.grupo1.repository.PropertyRepository;
import ar.edu.itba.paw.grupo1.web.PropertyForm;
import ar.edu.itba.paw.grupo1.web.RoomForm;
import ar.edu.itba.paw.grupo1.web.Service;


@Controller
@RequestMapping(value="property")
public class PropertyController extends BaseController {

	private PropertyRepository propertyRepository;
	private PictureRepository pictureRepository;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		binder.registerCustomEditor(Set.class, "services", new CustomCollectionEditor(Set.class, true));
	}
	
	@Autowired
	public PropertyController(PropertyRepository propertyRepository, PictureRepository pictureRepository) {
		this.propertyRepository = propertyRepository;
		this.pictureRepository = pictureRepository;
	}
	
	@RequestMapping(value="add", method = RequestMethod.GET)
	protected ModelAndView addGet() { 

		ModelAndView mav = new ModelAndView();
		mav.addObject("propertyForm", new PropertyForm());
		mav.addObject("services", getServices(new Property(), null));
		
		return render("editProperty.jsp", "Add Property", mav);
	}

	@RequestMapping(value="add", method = RequestMethod.POST)
	protected ModelAndView addPost(HttpServletRequest req, @Valid PropertyForm propertyForm, Errors errors) { 

		ModelAndView mav = new ModelAndView();
		Property property = propertyForm.build(getLoggedInUser(req));
		
		if (errors.hasErrors()) {
			mav.addObject("services", getServices(new Property(), req));
			return render("editProperty.jsp", "Add Property", mav);
		}
		
		property.publish();
		if (!isMine(req, property)) {
			throw new PermissionDeniedException();
		}
		propertyRepository.save(property);
		return redirect("/property/list");
	}
	
	@RequestMapping(value="edit", method = RequestMethod.GET)
	protected ModelAndView editGet(HttpServletRequest req, @RequestParam("id") Property property) 
					throws ServletException, IOException {
		
		if (property == null) {
			throw new InvalidParameterException();
		} else if (!isMine(req, property)) {
			throw new PermissionDeniedException();
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("edit", 1);
		mav.addObject("propertyForm", new PropertyForm(property));
		mav.addObject("pictures", pictureRepository.get(property.getId()));				
		mav.addObject("services", getServices(property, null));
		mav.addObject("rooms", property.getRooms());

		return render("editProperty.jsp", "Edit Property", mav);
	}

	@RequestMapping(value="edit", method = RequestMethod.POST)
	protected ModelAndView editPost(HttpServletRequest req, 
			@Valid PropertyForm propertyForm, Errors errors, @RequestParam("id") Property property) 
			throws ServletException, IOException {

		ModelAndView mav = new ModelAndView();
		
		if (property == null) {
			throw new InvalidParameterException();
		} else if (!isMine(req, property)) {
			throw new PermissionDeniedException();
		}
		
		if (errors.hasErrors()) {
			mav.addObject("edit", 1);
			mav.addObject("services", getServices(property, req));
			mav.addObject("rooms", property.getRooms());
			mav.addObject("pictures", pictureRepository.get(property.getId()));
			return render("editProperty.jsp", "Edit Property", mav);
		}
		
		propertyForm.update(property);
			
		propertyRepository.save(property);

		return redirect("/property/list");
	}

	@RequestMapping(value="showDetail", method = RequestMethod.GET)
	protected ModelAndView showDetail(HttpServletRequest req, @RequestParam("id") Property property) {

		if (property == null) {
			return redirect("/query");
		} else if (!property.isPublished() && (!isLoggedIn(req) || !isMine(req, property))) {
			return redirect("/query?unpublished=true");

		}

		ModelAndView mav = new ModelAndView();
		List<Picture> pictures = pictureRepository.getPictures(property);

		if (isLoggedIn(req) && isMine(req, property)) {
			mav.addObject("ownProperty", 1);
		}

		property.visited();
		propertyRepository.save(property);
		
		User user = property.getUser();
		
		if (user.getRealEstateName() != null) {
			mav.addObject("realEstateName", user.getRealEstateName());
			if (user.getLogoExtension() != null) {
				mav.addObject("logo", "logo_"+user.getId()+user.getLogoExtension());
			}
		}

		mav.addObject("property", property);
		mav.addObject("services", getServices(property, null));
		if (pictures.size() > 0) {
			mav.addObject("pictures", pictures);
		}
		
		return render("propertyDetail.jsp", "Property Detail", mav);
	}
	
	@RequestMapping(value="list",method = RequestMethod.GET)
	protected ModelAndView list(HttpServletRequest req) {

		ModelAndView mav = new ModelAndView();
		
		User user = getLoggedInUser(req);		
		mav.addObject("properties", propertyRepository.getProperties(user));
		
		return render("listProperties.jsp", "List Properties", mav);
	}	
	
	@RequestMapping(value="publish", method = RequestMethod.GET)
	protected ModelAndView publish(HttpServletRequest req, @RequestParam("id") Property property)
			throws ServletException {

		if (property == null) {
			throw new InvalidParameterException();
		} else if (!isMine(req, property)) {
			throw new PermissionDeniedException();
		}

		property.publish();
		propertyRepository.save(property);
		
		return redirect("/property/list");
	}
	
	@RequestMapping(value="unpublish",method = RequestMethod.GET)
	protected ModelAndView unpublish(HttpServletRequest req, @RequestParam("id") Property property)
			throws ServletException {

		if (property == null) {
			throw new InvalidParameterException();
		} else if (!isMine(req, property)) {
			throw new PermissionDeniedException();
		}

		property.unpublish();		
		propertyRepository.save(property);			

		return redirect("/property/list");
	}
	
	@RequestMapping(value="reserve", method = RequestMethod.GET)
	protected ModelAndView reserve(HttpServletRequest req, @RequestParam("id") Property property)
			throws ServletException {

		if (property == null) {
			throw new InvalidParameterException();
		} else if (!isMine(req, property)) {
			throw new PermissionDeniedException();
		}
		
		property.reserve();
		
		propertyRepository.save(property);			
		return redirect("/property/list");
	}
	
	@RequestMapping(value="unreserve",method = RequestMethod.GET)
	protected ModelAndView unreserve(HttpServletRequest req, @RequestParam("id") Property property)
			throws ServletException {
		
		if (property == null) {
			throw new InvalidParameterException();
		} else if (!isMine(req, property)) {
			throw new PermissionDeniedException();
		}
			
		property.unreserve();
		propertyRepository.save(property);			
		
		return redirect("/property/list");
	}
	
	@RequestMapping(value="addRoom/{propId}", method = RequestMethod.GET)
	protected ModelAndView addRoomGet(HttpServletRequest req, 
			@PathVariable("propId") Property property) throws ServletException, IOException {
		
		ModelAndView mav = new ModelAndView();

		if (isMine(req, property)) {
			mav.addObject("roomForm", new RoomForm());
			mav.addObject("property", property);
			mav.addObject("hasPermissions", 1);
		}
		
		return render("addRoom.jsp", "Add Room", mav);	
	}
	
	@RequestMapping(value="addRoom/{propId}", method = RequestMethod.POST)
	protected ModelAndView addRoomPost(HttpServletRequest req,
			@Valid RoomForm roomForm, Errors errors, @PathVariable("propId") Property property) 
					throws ServletException, IOException {
		
		ModelAndView mav = new ModelAndView();
		
		if (!isMine(req, property)) {
			return render("addRoom.jsp", "Add Room", mav);
		}
		
		if (errors.hasErrors()) {
			mav.addObject("hasPermissions", 1);
			mav.addObject("property", property);
			return render("addRoom.jsp", "Add Room", mav);
		}
		
		Room room = roomForm.buildRoom(property);
		property.addRoom(room);
		
		propertyRepository.save(property);
		return redirect("/property/edit?id=" + property.getId());
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
