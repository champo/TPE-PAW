package ar.edu.itba.paw.grupo1.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import ar.edu.itba.paw.grupo1.dao.UserDao.UserAlreadyExistsException;
import ar.edu.itba.paw.grupo1.model.User;
import ar.edu.itba.paw.grupo1.service.UserService;
import ar.edu.itba.paw.grupo1.web.LoginForm;
import ar.edu.itba.paw.grupo1.web.RegisterForm;

@Controller
@RequestMapping(value="user")
public class UserController extends BaseController {

	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	
	@RequestMapping(value="register", method = RequestMethod.GET)
	protected ModelAndView registerGet(HttpServletRequest req) {
		
		ModelAndView mav = new ModelAndView();
		if (isLoggedIn(req)) {
			RedirectView view = new RedirectView("/", true);
			return new ModelAndView(view);
		}

		mav.addObject(new RegisterForm());
		return render("register.jsp", "Register", mav);
	}
	
	@RequestMapping(value="register", method = RequestMethod.POST)
	protected ModelAndView registerPost(HttpServletRequest req, @Valid RegisterForm form, Errors errors) {
		
		ModelAndView mav = new ModelAndView();
		if (isLoggedIn(req)) {
			RedirectView view = new RedirectView("/", true);
			return new ModelAndView(view);
		}
		
		if (errors.hasErrors()) {
			return render("register.jsp", "Register", mav);
		}
		
		try {
			if (userService.register(form.build()) != null) {
				return render("registerSuccess.jsp", "Register", mav);
			}
			
		} catch (UserAlreadyExistsException e) {
			req.setAttribute("usernameDuplicate", true);
		}
		
		return render("register.jsp", "Register", mav);
	}
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	protected ModelAndView loginGet(HttpServletRequest req) {
		
		ModelAndView mav = new ModelAndView();
		if (isLoggedIn(req)) {
			RedirectView view = new RedirectView("/",true);
			return new ModelAndView(view);
		}

		mav.addObject("username", getRememberedName(req));
		
		return render("login.jsp", "Login", mav);
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	protected ModelAndView loginPost(LoginForm loginForm, @RequestParam String from, 
			HttpServletRequest req, HttpServletResponse resp) {
		
		ModelAndView mav = new ModelAndView();
		if (isLoggedIn(req)) {
			RedirectView view = new RedirectView("/", true);
			return new ModelAndView(view);
		}
		
		String username = loginForm.getUsername();
		String password = loginForm.getPassword();
		
		if (username != null && password != null) {
			User user = userService.login(username, password);
			
			if (user != null) {
				// We can log in now!
				setLoggedInUser(req, user);
				
				if (loginForm.isRememberName()) {
					rememberUsername(resp, user);
				}
				
				if (loginForm.isRememberMe()) {
					rememberUser(resp, user);
				}
				
				if (from == null || from.isEmpty()) {
					from = getServletContext().getContextPath();
					
					if (from.isEmpty()) {
						// This may happen if we're the default context
						from = "/";
					}
				}
				
				System.out.println(from);
				RedirectView view = new RedirectView(from, false);
				return new ModelAndView(view);
			}
		}
		
		mav.addObject("username", username);
		mav.addObject("invalidCredentials", true);
		
		return render("login.jsp", "Login", mav);
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	protected ModelAndView logoutGet(HttpServletRequest req, HttpServletResponse resp) {
		
		logout(req, resp);
		RedirectView view = new RedirectView("/", true);
		return new ModelAndView(view);
	}
}
