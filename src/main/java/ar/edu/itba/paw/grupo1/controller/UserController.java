package ar.edu.itba.paw.grupo1.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import ar.edu.itba.paw.grupo1.dao.UserDao.UserAlreadyExistsException;
import ar.edu.itba.paw.grupo1.model.User;
import ar.edu.itba.paw.grupo1.service.UserService;
import ar.edu.itba.paw.grupo1.web.RegisterForm;

@Controller
@RequestMapping(value="user")
public class UserController extends BaseController {

	UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	
	@RequestMapping(value="register", method = RequestMethod.GET)
	protected ModelAndView registerGet(HttpServletRequest req)
			throws ServletException, IOException {
		
		ModelAndView mav = new ModelAndView();
		if (isLoggedIn(req)) {
			RedirectView view = new RedirectView("/", true);
			return new ModelAndView(view);
		}

		mav.addObject(new RegisterForm());
		return render("register.jsp", "Register", mav);
	}
	
	@RequestMapping(value="register", method = RequestMethod.POST)
	protected ModelAndView registerPost(HttpServletRequest req, @Valid RegisterForm form, Errors errors)
			throws ServletException, IOException {
		
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
	protected ModelAndView loginGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		ModelAndView mav = new ModelAndView();
		if (isLoggedIn(req)) {
			RedirectView view = new RedirectView("/",true);
			return new ModelAndView(view);
		}

		String username = req.getParameter("username");
		if (username == null || username.isEmpty()) {
			req.setAttribute("username", getRememberedName(req));
		} else {
			req.setAttribute("username", username);
		}
		
		return render("login.jsp", "Login", mav);
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	protected ModelAndView loginPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		ModelAndView mav = new ModelAndView();
		if (isLoggedIn(req)) {
			RedirectView view = new RedirectView("/", true);
			return new ModelAndView(view);
		}
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		if (username != null && password != null) {
			User user = userService.login(username, password);
			
			if (user != null) {
				// We can log in now!
				setLoggedInUser(req, user);
				
				if (req.getParameter("rememberName") != null) {
					rememberUsername(resp, user);
				}
				
				if (req.getParameter("rememberMe") != null) {
					rememberUser(resp, user);
				}
				
				String to = req.getParameter("from");
				if (to == null || to.isEmpty()) {
					to = getServletContext().getContextPath();
				}
				
				RedirectView view = new RedirectView(to, false);
				return new ModelAndView(view);
			}
		}
		
		req.setAttribute("username", username);
		
		req.setAttribute("invalidCredentials", true);
		return render("login.jsp", "Login", mav);
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	protected ModelAndView logoutGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		logout(req, resp);
		RedirectView view = new RedirectView("/", true);
		return new ModelAndView(view);
	}
}
