package ar.edu.itba.paw.grupo1.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import ar.edu.itba.paw.grupo1.model.Picture;
import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.service.PictureService;
import ar.edu.itba.paw.grupo1.service.PropertyService;
import ar.edu.itba.paw.grupo1.web.EditPictureForm;
import ar.edu.itba.paw.grupo1.web.NewPictureForm;

@Controller
@RequestMapping(value="picture")
public class PictureController extends BaseController implements HandlerExceptionResolver {

	protected PictureService pictureService;
		
	@Autowired
	public PictureController(PropertyService propertyService, PictureService pictureService) {
		this.pictureService = pictureService;
	}
	
	@RequestMapping(value="add/{propId}", method = RequestMethod.GET)
	protected ModelAndView addGet(HttpServletRequest req, @PathVariable("propId") Property property) {
		
		ModelAndView mav = new ModelAndView();
		
		if (property != null && isMine(req, property)) {
			Picture picture = new Picture();
			picture.setProperty(property);
			mav.addObject("property", property);
		} else {
			mav.addObject("noPermissions", 1);
		}
		
		mav.addObject("newPictureForm", new NewPictureForm());
		return render("editPicture.jsp", "Add Picture", mav);	
	}

	@RequestMapping(value="add/{propId}", method = RequestMethod.POST)
	protected ModelAndView addPost(HttpServletRequest req, @Valid NewPictureForm pictureForm, 
			Errors errors, @PathVariable("propId") Property property) {
		
		ModelAndView mav = new ModelAndView();
		Picture picture = new Picture();
		MultipartFile file = pictureForm.getFile();
		
		if (errors.hasErrors()) {
			mav.addObject("property", property);
			return render("editPicture.jsp", "Add Picture", mav);
		}
		
		String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
		pictureForm.build(picture, extension, property);
		
		if (property == null || !isMine(req, picture)) {
			mav.addObject("noPermissions", 1);
			return render("editPicture.jsp", "Add Picture", mav);
		}
		
		pictureService.save(picture);
		
		try {
			file.transferTo(new File(getServletContext().getRealPath("/images") + "/" + picture.getId() + picture.getExtension()));
		} catch (Exception e) {
			mav.addObject("picture", picture);
			mav.addObject("writeError", 1);
			render("editPicture.jsp", "Add Picture", mav);
		}
		RedirectView view = new RedirectView("/property/edit?id=" + picture.getProperty().getId(),true);
		return new ModelAndView(view);

	}

	@RequestMapping(value="edit/{propId}/{pictureId}", method = RequestMethod.GET)
	protected ModelAndView editGet(HttpServletRequest req, @PathVariable("propId") Property property, 
			@PathVariable("pictureId") Picture picture) {
		
		ModelAndView mav = new ModelAndView();	
			
		if (picture != null && isMine(req, picture)) {
			mav.addObject("edit", 1);
			mav.addObject("picture", picture);
		} else {
			mav.addObject("noPermissions", 1);
		}

		mav.addObject("editPictureForm", new EditPictureForm());
		return render("editPicture.jsp", "Edit Picture", mav);
	}

	@RequestMapping(value="edit/{propId}/{pictureId}", method = RequestMethod.POST)
	protected ModelAndView editPost(HttpServletRequest req, @PathVariable("propId") Property property, 
			@PathVariable("pictureId") Picture picture, @Valid EditPictureForm pictureForm, Errors errors) { 
					
		ModelAndView mav = new ModelAndView();

		if (picture == null || !isMine(req, picture)) {
			mav.addObject("noPermissions", 1);
			return render("editPicture.jsp", "Edit Picture", mav);
		}
		
		if (errors.hasErrors()) {
			mav.addObject("edit", 1);
			mav.addObject("picture", picture);
			return render("editPicture.jsp", "Edit Picture", mav);
		} 
		picture.setName(pictureForm.getName());
		pictureService.save(picture);
		
		RedirectView view = new RedirectView("/property/edit?id=" + picture.getProperty().getId(), true);
		return new ModelAndView(view);

	}
	
	@RequestMapping(value="delete/{propId}/{pictureId}", method = RequestMethod.POST)
	protected ModelAndView deletePost(HttpServletRequest req, @PathVariable("propId") Property property, 
			@PathVariable("pictureId") Picture picture) {
		
		ModelAndView mav = new ModelAndView();
		
		if (picture == null || !isMine(req, picture)) {
			mav.addObject("noPermissions", 1);
			return render("editPicture.jsp", "Edit Picture", mav);
		}
		
		pictureService.delete(picture);
		File file = new File(getServletContext().getRealPath("/images/") + "/" + picture.getId() + picture.getExtension());
		if(!file.delete()) {
			mav.addObject("edit", 1);
			mav.addObject("picture", picture);
			mav.addObject("deleteError", 1);
			return render("editPicture.jsp", "Edit Picture", mav);
		}
		
		RedirectView view = new RedirectView("/property/edit?id=" + picture.getProperty().getId(), true);
		return new ModelAndView(view);
	}
	
	
	@Override
	public ModelAndView resolveException(HttpServletRequest req, HttpServletResponse resp, 
			Object handler, Exception e) {
		
		if (e instanceof MaxUploadSizeExceededException) {
            RedirectView view = new RedirectView(req.getRequestURI() + "?maxUploadSizeError=true", true);
    		return new ModelAndView(view);
        } else {
        	return null;
        }
		
	}
	
}