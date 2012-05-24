package ar.edu.itba.paw.grupo1.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.grupo1.dto.PaginatedList;
import ar.edu.itba.paw.grupo1.dto.PropertyQuery;
import ar.edu.itba.paw.grupo1.model.User;
import ar.edu.itba.paw.grupo1.repository.PropertyRepository;

@Controller
@RequestMapping(value="query")
public class QueryController extends BaseController {

	private PropertyRepository propertyRepository;
	
	private final int resultsPerPage = 2;
	
	@Autowired
	public QueryController(PropertyRepository propertyRepository) {
		this.propertyRepository = propertyRepository;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView query(HttpServletRequest req, @Valid PropertyQuery propertyQuery, Errors errors)
			throws ServletException, IOException {

		ModelAndView mav = new ModelAndView();

		if (errors.hasErrors()) {
			propertyQuery = new PropertyQuery();
		}

		PaginatedList paginatedList = propertyRepository.query(propertyQuery, resultsPerPage);

		mav.addObject("queryResults", paginatedList.getList());
		mav.addObject("pageNumber", propertyQuery.getPageNumber());
		mav.addObject("lastPage", paginatedList.getLastPage());
		mav.addObject("paginationFrom", Math.max(1, propertyQuery.getPageNumber()-5));
		mav.addObject("paginationTo", Math.min(paginatedList.getLastPage(), propertyQuery.getPageNumber()+4));
		
		String pageURL = req.getRequestURL() + "?";
		String queryString = req.getQueryString();
		
		if (queryString != null) {
			queryString = queryString.replaceAll("pageNumber=[0-9]*&", "");
			queryString = queryString.replaceAll("&?pageNumber=[0-9]*", "");
		}

		if (queryString != null && !queryString.isEmpty()) {
			pageURL += queryString + "&pageNumber=";
		} else {
			pageURL += "pageNumber=";
		}

		mav.addObject("pageURL", pageURL);

		return render("query.jsp", "Query", mav);
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	protected ModelAndView user(@RequestParam(value = "id") User user) {
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("searchUser", user);
		mav.addObject("queryResults", propertyRepository.getListedProperties(user));
		
		return render("list.jsp", "Property list", mav);
	}
}
