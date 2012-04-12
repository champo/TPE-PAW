package ar.edu.itba.paw.grupo1.service;

import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.model.User;
import ar.edu.itba.paw.grupo1.service.exception.MailingException;

public interface EmailService {

	public void sendEmail(String email, String body, String subject) throws MailingException;

	public void sendContact(String email, String name,
			String body, User owner, Property property) throws MailingException;
}
