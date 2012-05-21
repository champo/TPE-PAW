package ar.edu.itba.paw.grupo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value={"/","/index"})
public class IndexController extends BaseController {

	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		return render("index.jsp", "Index", mav);
	}
	
}
