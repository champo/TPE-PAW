package ar.edu.itba.paw.grupo1.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import ar.edu.itba.paw.grupo1.dao.UserDao.UserAlreadyExistsException;
import ar.edu.itba.paw.grupo1.model.User;
import ar.edu.itba.paw.grupo1.service.UserService;

@Controller
@RequestMapping(value="user")
public class UserController extends BaseController {

	UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	
	@RequestMapping(value="register", method = RequestMethod.GET)
	protected ModelAndView registerGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		ModelAndView mav = new ModelAndView();
		if (isLoggedIn(req)) {
			RedirectView view = new RedirectView("/",true);
			return new ModelAndView(view);
		}
		
		return render(req, resp, "register.jsp", "Register", mav);
	}
	
	@RequestMapping(value="register", method = RequestMethod.POST)
	protected ModelAndView registerPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		ModelAndView mav = new ModelAndView();
		if (isLoggedIn(req)) {
			RedirectView view = new RedirectView("/",true);
			return new ModelAndView(view);
		}
		
		boolean error = false;
		
		error |= !checkParameter(req, "name", 0, 50);
		error |= !checkParameter(req, "surname", 0, 50);
		error |= !checkParameter(req, "username", 0, 50);
		error |= !hasParameter(req, "password");
		error |= !areParamsEqual(req, "password", "passwordConfirmation");
		error |= !checkEmail(req, "email", 0, 50);
		error |= !checkPhone(req, "phone", 0, 20);
		
		if (error) {
			return render(req, resp, "register.jsp", "Register", mav);
		}
		
		try {
			User user = userService.register(
				req.getParameter("name"),
				req.getParameter("surname"),
				req.getParameter("email"),
				req.getParameter("phone"),
				req.getParameter("username"),
				req.getParameter("password")
			);
			
			if (user != null) {
				return render(req, resp, "registerSuccess.jsp", "Register", mav);
			}
			
		} catch (UserAlreadyExistsException e) {
			req.setAttribute("usernameDuplicate", true);
		}
		
		return render(req, resp, "register.jsp", "Register", mav);
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
		
		return render(req, resp, "login.jsp", "Login", mav);
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	protected ModelAndView loginPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		ModelAndView mav = new ModelAndView();
		if (isLoggedIn(req)) {
			RedirectView view = new RedirectView("/",true);
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
					to = req.getContextPath();
				}
				
				RedirectView view = new RedirectView(to,true);
				return new ModelAndView(view);
			}
		}
		
		req.setAttribute("username", username);
		
		req.setAttribute("invalidCredentials", true);
		return render(req, resp, "login.jsp", "Login", mav);
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	protected ModelAndView logoutGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		logout(req, resp);
		RedirectView view = new RedirectView("/",true);
		return new ModelAndView(view);
	}
}
