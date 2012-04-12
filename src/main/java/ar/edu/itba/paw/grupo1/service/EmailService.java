package ar.edu.itba.paw.grupo1.service;

import ar.edu.itba.paw.grupo1.service.exception.MailingException;

public interface EmailService {

	public void sendEmail(String email, String body, String subject) throws MailingException;
}
