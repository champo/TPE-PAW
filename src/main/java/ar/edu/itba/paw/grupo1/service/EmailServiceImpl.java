package ar.edu.itba.paw.grupo1.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ar.edu.itba.paw.grupo1.service.exception.MailingException;

public class EmailServiceImpl implements EmailService {
	
	private Properties props;

	public EmailServiceImpl(Properties props) {
		this.props = props;
	}
	
    public void sendEmail(String email, String body, String subject) throws MailingException {

		final String username = props.getProperty("mail.username");
		final String password = props.getProperty("mail.password");
		
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
}
