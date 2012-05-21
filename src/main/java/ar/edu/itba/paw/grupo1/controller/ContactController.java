package ar.edu.itba.paw.grupo1.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import ar.edu.itba.paw.grupo1.controller.exception.InvalidParameterException;
import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.service.EmailService;
import ar.edu.itba.paw.grupo1.service.exception.MailingException;
import ar.edu.itba.paw.grupo1.web.ContactForm;

@Controller
@RequestMapping(value="contact")
public class ContactController extends BaseController {
	
	private EmailService emailService;

	@Autowired
	public ContactController(EmailService emailService) {
		this.emailService = emailService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView doGet(@RequestParam("propertyId") Property property, HttpServletRequest req, ContactForm contactForm)
		throws InvalidParameterException {

		ModelAndView mav = new ModelAndView();
			
		if (property == null) {
			throw new InvalidParameterException();
		} else if (getLoggedInUser(req) != null && isMine(req, property)) {
			RedirectView view = new RedirectView("/property/showDetail?id=" + property.getId(), true);
			return new ModelAndView(view);
		} else if (!property.isPublished()) {
			RedirectView view = new RedirectView("/query?unpublished=true", true);
			return new ModelAndView(view);
		}			
		
		mav.addObject(property);
		return render("contact.jsp", "Contact", mav);
	}

	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView doPost(@RequestParam("propertyId") Property property, HttpServletRequest req, @Valid ContactForm contactForm, Errors errors)
			throws ServletException, IOException {

		ModelAndView mav = new ModelAndView();

		if (property == null) {
			throw new InvalidParameterException();
		} else if (getLoggedInUser(req) != null && isMine(req, property)) {
			RedirectView view = new RedirectView("/property/showDetail?id=" + property.getId(),true);
			return new ModelAndView(view);
		} else if (!property.isPublished()) {
			RedirectView view = new RedirectView("/query?unpublished=true",true);
			return new ModelAndView(view);
		}

		mav.addObject(property);

		if (errors.hasErrors()) {
			return render("contact.jsp", "Contact", mav);
		}

		try {
			emailService.sendContact(contactForm.getEmail(), contactForm.getName(), 
					contactForm.getComment(), property.getUser(), property);
		} catch (MailingException e) {
			Logger.getLogger(ContactController.class).warn("Failed to send contact email", e);
		}
		
		mav.addObject("publisher", property.getUser());
		return render("contact.jsp", "Contact", mav);
	}
}
