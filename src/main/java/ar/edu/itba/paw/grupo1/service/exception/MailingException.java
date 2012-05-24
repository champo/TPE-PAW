package ar.edu.itba.paw.grupo1.service.exception;

import javax.mail.MessagingException;

@SuppressWarnings("serial")
public class MailingException extends Exception {

	@SuppressWarnings("unused")
	private MessagingException exception;

	public MailingException(MessagingException e) {
		this.exception = e;
	}
}
