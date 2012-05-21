package ar.edu.itba.paw.grupo1.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import ar.edu.itba.paw.grupo1.controller.exception.InvalidParameterException;
import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.service.EmailService;
import ar.edu.itba.paw.grupo1.service.PropertyService;
import ar.edu.itba.paw.grupo1.service.UserService;
import ar.edu.itba.paw.grupo1.service.exception.MailingException;
import ar.edu.itba.paw.grupo1.web.ContactForm;

@Controller
@RequestMapping(value="contact")
public class ContactController extends BaseController {
	
	private UserService userService;
	private PropertyService propertyService;
	private EmailService emailService;

	@Autowired
	public ContactController(PropertyService propertyService, UserService userService, EmailService emailService) {
		this.propertyService = propertyService;
		this.userService = userService;
		this.emailService = emailService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView doGet(HttpServletRequest req, HttpServletResponse resp, ContactForm contactForm)
			throws ServletException, IOException {

		ModelAndView mav = new ModelAndView();
		int propertyId;
		Property property;
		if (checkIntegerParameter(req, "propertyId")) {

			propertyId = Integer.parseInt(req.getParameter("propertyId"));			
			property = propertyService.getById(propertyId);
			
			if (property == null) {
				throw new InvalidParameterException();
			} else if (getLoggedInUser(req) != null && isMine(req, property)) {
				RedirectView view = new RedirectView("/property/showDetail?id=" + property.getId(),true);
				return new ModelAndView(view);
			} else if (!property.isPublished()) {
				RedirectView view = new RedirectView("/query?unpublished=true",true);
				return new ModelAndView(view);
			}			
		} else {
			throw new InvalidParameterException();
		}
		req.setAttribute("propertyId", property.getId());
		req.setAttribute("address", property.getAddress());
		req.setAttribute("neighbourhood", property.getNeighbourhood());
		return render(req, resp, "contact.jsp", "Contact", mav);
	}

	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView doPost(HttpServletRequest req, HttpServletResponse resp, @Valid ContactForm contactForm, Errors errors)
			throws ServletException, IOException {

		ModelAndView mav = new ModelAndView();
		Property property;
		if (checkIntegerParameter(req, "propertyId")) {
			property = propertyService.getById(Integer.parseInt(req.getParameter("propertyId")));
			
			if (errors.hasErrors()) {
				//FIXME: NPE if property is null.
				req.setAttribute("propertyId", property.getId());
				req.setAttribute("address", property.getAddress());
				req.setAttribute("neighbourhood", property.getNeighbourhood());
				return render(req, resp, "contact.jsp", "Contact", mav);
			}
		
			if (property == null) {
				throw new InvalidParameterException();
			} else if (getLoggedInUser(req) != null && isMine(req, property)) {
				RedirectView view = new RedirectView("/property/showDetail?id=" + property.getId(),true);
				return new ModelAndView(view);
			} else if (!property.isPublished()) {
				RedirectView view = new RedirectView("/query?unpublished=true",true);
				return new ModelAndView(view);
			}
		} else {
			throw new InvalidParameterException();
		}
		req.setAttribute("propertyId", property.getId());
		req.setAttribute("address", property.getAddress());
		req.setAttribute("neighbourhood", property.getNeighbourhood());
		req.setAttribute("publisher", property.getUser());
		
		try {
			emailService.sendContact(contactForm.getEmail(), contactForm.getName(), 
					contactForm.getComment(), property.getUser(), property);
		} catch (MailingException e) {
			Logger.getLogger(ContactController.class).warn("Failed to send contact email", e);
		}
		return render(req, resp, "contact.jsp", "Contact", mav);
	}
}
