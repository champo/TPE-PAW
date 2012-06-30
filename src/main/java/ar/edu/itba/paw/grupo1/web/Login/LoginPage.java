package ar.edu.itba.paw.grupo1.web.Login;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.paw.grupo1.model.User;
import ar.edu.itba.paw.grupo1.repository.UserRepository;
import ar.edu.itba.paw.grupo1.service.HashingService;
import ar.edu.itba.paw.grupo1.web.WicketSession;
import ar.edu.itba.paw.grupo1.web.Base.BasePage;
import ar.edu.itba.paw.grupo1.web.Register.RegisterPage;

@SuppressWarnings("serial")
@AuthorizeInstantiation(WicketSession.GUEST)
public class LoginPage extends BasePage {

	@SpringBean
	private UserRepository users;
	
	private transient String username;
	
	private transient String password;
	
	private transient boolean rememberName;
	
	private transient boolean rememberMe;

	public LoginPage() {
		
		final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		feedbackPanel.setVisible(false);
		add(feedbackPanel);
		
		add(new BookmarkablePageLink<Void>("registerLink", RegisterPage.class));
		
		Form<LoginPage> form = new Form<LoginPage>("loginForm", new CompoundPropertyModel<LoginPage>(this)) {
			
			@Override
			protected void onSubmit() {
				WicketSession session = WicketSession.get();
				User user = users.get(username);
				
				if (session.signIn(user, HashingService.hash(password))) {
					
					if (!continueToOriginalDestination()) {
						setResponsePage(getApplication().getHomePage());
					}
				} else {
					error(getString("invalidCredentials"));
					feedbackPanel.setVisible(true);
				}
			}
			
			@Override
			protected void onError() {
				super.onError();
				feedbackPanel.setVisible(true);
			}
		};
		
		form.add(new TextField<String>("username").setRequired(true));
		form.add(new PasswordTextField("password"));
		form.add(new CheckBox("rememberName"));
		form.add(new CheckBox("rememberMe"));
		form.add(new Button("login", new ResourceModel("login")));
		
		add(form);
		
	}	
}
