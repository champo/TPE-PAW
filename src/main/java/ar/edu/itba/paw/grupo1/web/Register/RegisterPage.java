package ar.edu.itba.paw.grupo1.web.Register;

import java.util.regex.Pattern;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.StringValidator;

import ar.edu.itba.paw.grupo1.model.User;
import ar.edu.itba.paw.grupo1.model.User.UserType;
import ar.edu.itba.paw.grupo1.repository.UserRepository;
import ar.edu.itba.paw.grupo1.repository.UserRepository.UserAlreadyExistsException;
import ar.edu.itba.paw.grupo1.service.HashingService;
import ar.edu.itba.paw.grupo1.web.WicketSession;
import ar.edu.itba.paw.grupo1.web.Base.BasePage;
import ar.edu.itba.paw.grupo1.web.Home.HomePage;

@AuthorizeInstantiation(WicketSession.GUEST)
public class RegisterPage extends BasePage {
	
	@SpringBean
	private UserRepository users;
	
	private String name;

	private String surname;

	private String email;

	private String phone;

	private String username;

	private String password;

	private String passwordConfirm;

	private String realEstateName;

	private String logoExtension;

	private UserType userType = UserType.REGULAR;

	private FeedbackPanel feedbackPanel;

	public RegisterPage() {
		
		feedbackPanel = new FeedbackPanel("feedback");
		feedbackPanel.setVisible(false);
		add(feedbackPanel);
		
		add(new RegisterForm("registerForm"));
	}
	
	private class RegisterForm extends Form<RegisterPage> {

		public RegisterForm(String id) {
			super(id, new CompoundPropertyModel<RegisterPage>(RegisterPage.this));
			
			addStringField("name", 50);
			addStringField("surname", 50);
			
			addStringField("email", 50)
				.add(EmailAddressValidator.getInstance());
			
			addStringField("phone", 20)
				.add(new IValidator<String>() {
					
					private final Pattern regexp = Pattern.compile("^ *[0-9](-?[ 0-9])*[0-9] *$");
					
					@Override
					public void validate(IValidatable<String> validatable) {
						
						
						if (!regexp.matcher(validatable.getValue()).matches()) {
							validatable.error(new ValidationError().addMessageKey("phone.invalid"));
						}

					}
						
				});
			
			addStringField("username", 50);

			PasswordTextField password = new PasswordTextField("password");
			add(password);
			
			PasswordTextField passwordConfirm = new PasswordTextField("passwordConfirm");
			add(passwordConfirm);
			
			add(new EqualPasswordInputValidator(password, passwordConfirm));
			
			//addStringField("realStateName", 50, false);
			//add(new TextField<String>("logoExtension"));
			
			add(new Button("register", new ResourceModel("register")));
			
		}
		
		protected TextField<String> addStringField(String name, int maxLength) {
			return addStringField(name, maxLength, true);
		}
		
		protected TextField<String> addStringField(String name, int maxLength, boolean required) {
			
			TextField<String> field = new TextField<String>(name);
			field.add(StringValidator.maximumLength(50));
			field.setRequired(required);
			
			add(field);
			
			return field;
		}
		
		@Override
		protected void onSubmit() {
			
			try {
				String hash = HashingService.hash(password);
				users.register(new User(name, surname, email, phone, username, hash, realEstateName, logoExtension));
			} catch (UserAlreadyExistsException e) {
				error(getString("username.taken"));
			}
			
			setResponsePage(HomePage.class);
		}
		
		@Override
		protected void onError() {
			feedbackPanel.setVisible(true);
		}
	}
}
