package ar.edu.itba.paw.grupo1.web.pages.Contact;

import java.security.InvalidParameterException;

import org.apache.log4j.Logger;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.PatternValidator;
import org.apache.wicket.validation.validator.StringValidator;

import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.model.User;
import ar.edu.itba.paw.grupo1.model.support.EntityModel;
import ar.edu.itba.paw.grupo1.repository.PropertyRepository;
import ar.edu.itba.paw.grupo1.service.EmailService;
import ar.edu.itba.paw.grupo1.service.exception.MailingException;
import ar.edu.itba.paw.grupo1.web.pages.Base.BasePage;
import ar.edu.itba.paw.grupo1.web.pages.PropertyDetail.PropertyDetailPage;
import ar.edu.itba.paw.grupo1.web.pages.UnpublishedProperty.UnpublishedPropertyPage;

@SuppressWarnings("serial")
public class ContactPage extends BasePage {

	private transient String name;
	@SuppressWarnings("unused")
	private transient String phone; //Its not used, we do what we are asked, no less, no more.
	private transient String email;
	private transient String comment;
	private Integer propId;

	@SpringBean
	private EmailService emailService;

	@SpringBean
	private PropertyRepository properties;
	private FeedbackPanel feedbackPanel;
	
	public ContactPage(Property property) {
		
		propId = property.getId();
		IModel<Property> model = new EntityModel<Property>(Property.class, property);
		addContactForm(model, true);
		addContactInfo(model, false);
	}

	public ContactPage(Property property, User user) {
		
		IModel<Property> model = new EntityModel<Property>(Property.class, property);
		addContactForm(model, false);
		addContactInfo(model, true);	}

	private void addContactInfo(IModel<Property> model, boolean visible) {
		add(new Label("contactInfoHeader", getLocalizer().getString("page.contactInfoHeader", this, model)), visible);
		add(new Label("contactPhone", getLocalizer().getString("phone", this)), visible);
		add(new Label("contactEmail", getLocalizer().getString("email", this)), visible);
		add(new Label("publisherPhone", model.getObject().getUser().getPhone()), visible);
		add(new Label("publisherEmail", model.getObject().getUser().getEmail()), visible);

	}

	private void addContactForm(IModel<Property> model, boolean visible) {
		
		add(new Label("contactFormHeader", getLocalizer().getString("page.contactFormHeader", this, model)), visible);

		feedbackPanel = new FeedbackPanel("feedback");
		feedbackPanel.setVisible(false);

		Form<ContactPage> form = new Form<ContactPage>("contactForm", new CompoundPropertyModel<ContactPage>(this)) {
			
			@Override
			protected void onSubmit() {
				
				Property property = properties.get(propId);
				if (property == null) {
					throw new InvalidParameterException();
				} else if (getSignedInUser() != null && isMine(property)) {
					setResponsePage(new PropertyDetailPage(property));
				} else if (!property.isPublished()) {
					setResponsePage(new UnpublishedPropertyPage());
				}

				try {
					emailService.sendContact(email, name, comment, property.getUser(), property);
				} catch (MailingException e) {
					Logger.getLogger(ContactPage.class).warn("Failed to send contact email", e);
				}
				
				setResponsePage(new ContactPage(property, property.getUser()));
			}

			@Override
			protected void onError() {
				super.onError();
				feedbackPanel.setVisible(true);
			}
		};
		
		TextField<String> nameTextField = new TextField<String>("name");
		nameTextField.setRequired(true);
		nameTextField.add(StringValidator.maximumLength(50));
		
		form.add(nameTextField);
		
		TextField<String> emailTextField = new TextField<String>("email");
		emailTextField.setRequired(true);
		emailTextField.add(EmailAddressValidator.getInstance());
		emailTextField.add(StringValidator.maximumLength(50));
		
		form.add(emailTextField);
		
		TextField<String> phoneTextField = new TextField<String>("phone");
		phoneTextField.setRequired(true);
		phoneTextField.add(new PatternValidator("^ *[0-9](-?[ 0-9])*[0-9] *$"));
		phoneTextField.add(StringValidator.maximumLength(20));
		
		form.add(phoneTextField);
		
		TextArea<String> commentTextArea = new TextArea<String>("comment");
		commentTextArea.setRequired(false);
		commentTextArea.add(StringValidator.maximumLength(1000));
		
		form.add(commentTextArea);
		form.add(new Label("nameLabel", getLocalizer().getString("name", this)));
		form.add(new Label("emailLabel", getLocalizer().getString("email", this)));
		form.add(new Label("phoneLabel", getLocalizer().getString("phone", this)));
		form.add(new Label("commentLabel", getLocalizer().getString("comment", this)));

		
		form.add(feedbackPanel);		
		form.add(new Button("submit", new ResourceModel("Submit")));
		add(form, visible);
	}
	
	@Override
	protected void onBeforeRender() {
		super.onBeforeRender();
		feedbackPanel.setVisible(feedbackPanel.anyMessage());
	}
	
}
