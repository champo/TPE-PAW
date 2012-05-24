package ar.edu.itba.paw.grupo1.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.model.User;
import ar.edu.itba.paw.grupo1.service.exception.MailingException;

public class EmailServiceImpl implements EmailService {
	
	private String username;
	private String password;
	private String host;
	private String port;
	private String auth;
	private String starttls;
	
	
	public EmailServiceImpl(String username, String password, String host, String port, String auth, String starttls) {
		this.username = username;
		this.password = password;
		this.host = host;
		this.port = port;
		this.auth = auth;
		this.starttls = starttls;
	}
	

    public void sendEmail(String email, String body, String subject) throws MailingException {

    	Properties props = new Properties();
    	props.setProperty("mail.username", username);
    	props.setProperty("mail.password", password);
    	props.setProperty("mail.smtp.port", port);
		props.setProperty("mail.smtp.auth", auth);
		props.setProperty("mail.smtp.host", host);
		props.setProperty("mail.smtp.starttls.enable", starttls);
		
		Session session = Session.getInstance(props, 
				new javax.mail.Authenticator() {
			    	protected PasswordAuthentication getPasswordAuthentication() {
			    		return new PasswordAuthentication(username, password);
			    	}
				});

		try {
		    Message message = new MimeMessage(session);
		    message.setFrom(new InternetAddress(username));
		    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
		    message.setSubject(subject);
		    message.setText(body);
	
		    Transport.send(message);
		} catch (MessagingException e) {
			throw new MailingException(e);
		}
    }

	@Override
	public void sendContact(String email, String name, String comment, User owner,
			Property property) throws MailingException {

		String subject = name + " is interested in your property and wants to contact you!";
		String body = name + " (" + email + ") has made a request to contact you about your property " +
				"in " + property.getAddress() + "-" + property.getNeighbourhood() + "\n\n" + comment;
		
		sendEmail(owner.getEmail(), body, subject);
	}
}
