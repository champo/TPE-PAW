package ar.edu.itba.paw.grupo1.controller;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.ejb.criteria.predicate.IsEmptyPredicate;
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
	
	@SuppressWarnings("unchecked")
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
		
		ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
		List<FileItem> items = null;

		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			mav.addObject("fatal", 1);
			return render("register.jsp", "Register", mav);
		}
		
		FileItem file = null;
		Iterator<FileItem> iter = items.iterator();
		while (iter.hasNext()) {
			file = (FileItem) iter.next();
			if ("file".equals(file.getFieldName())) {
				break;
			}
		}
		
		if (file.getName() != null && !file.getName().trim().isEmpty()) {

			if (form.getRealEstateName() == null || form.getRealEstateName().trim().isEmpty()) {
				mav.addObject("missingRealEstateNameError", 1);
				return render("register.jsp", "Register", mav);
			}

			if (!file.getName().matches("\\.(jpg|png|jpeg|gif)$")) {
				mav.addObject("extensionError", 1);
				return render("register.jsp", "Register", mav);
			}
			
			String extension = file.getName().substring(file.getName().lastIndexOf('.'));

			form.setLogoExtension(extension);
		}
		
		User user;
		
		try {
			user = userService.register(form.build());
		} catch (UserAlreadyExistsException e) {
			mav.addObject("usernameDuplicate", true);
			return render("register.jsp", "Register", mav);
		}

		if (user == null) {
			return render("register.jsp", "Register", mav);
		}

		if (user.getLogoExtension() != null) {
			try {
				file.write(new File(getServletContext().getRealPath("/images") + "/logo_" + user.getId() + user.getLogoExtension()));
			} catch (Exception e) {
				// TODO: delete user from db
				mav.addObject("writeError", 1);
				return render("register.jsp", "Register", mav);
			}
		}

		return render("registerSuccess.jsp", "Register", mav);
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
