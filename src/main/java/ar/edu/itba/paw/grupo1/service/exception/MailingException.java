package ar.edu.itba.paw.grupo1.service.exception;

import javax.mail.MessagingException;

@SuppressWarnings("serial")
public class MailingException extends Exception {

	private MessagingException exception;

	public MailingException(MessagingException e) {
		this.exception = e;
	}
}
