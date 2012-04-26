package ar.edu.itba.paw.grupo1.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.grupo1.ApplicationContainer;
import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.service.PropertyService;

@Controller
@RequestMapping
public class QueryController extends BaseController {

	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView query(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String operation = req.getParameter("operation");
		String property = req.getParameter("property");
		String rangeFrom = req.getParameter("rangeFrom");
		String rangeTo = req.getParameter("rangeTo");

		if (operation == null && property == null && rangeFrom == null
				&& rangeTo == null) {

			req.setAttribute("operationChecked", "any");
			req.setAttribute("propertyChecked", "any");
			return render(req, resp, "query.jsp", "Query");
		}

		if (operation == null) {
			operation = "any";
		}
		if (property == null) {
			property = "any";
		}

		req.setAttribute("operationChecked", operation);
		req.setAttribute("propertyChecked", property);

		double parsedRangeFrom = 0;
		double parsedRangeTo = Double.MAX_VALUE;
		boolean badParse = false;

		try {
			if (rangeFrom != null && !rangeFrom.isEmpty()) {
				parsedRangeFrom = Double.parseDouble(rangeFrom);
			}
			if (rangeTo != null && !rangeTo.isEmpty()) {
				parsedRangeTo = Double.parseDouble(rangeTo);
			}
		} catch (NumberFormatException e) {
			badParse = true;
		}

		if (badParse
				|| (rangeFrom != null && parsedRangeFrom < 0)
				|| (rangeTo != null && parsedRangeTo < 0)
				|| (rangeFrom != null && rangeTo != null && parsedRangeTo < parsedRangeFrom)) {

			req.setAttribute("invalidRange", true);
			return render(req, resp, "query.jsp", "Query");
		}

		req.setAttribute("rangeFromValue", rangeFrom);
		req.setAttribute("rangeToValue", rangeTo);
		
		if (rangeFrom == null) {
			parsedRangeFrom = 0;
		}
		if (rangeTo == null) {
			parsedRangeTo = Double.MAX_VALUE;
		}

		List<Property> query = ApplicationContainer.get(PropertyService.class)
				.query(operation, property, parsedRangeFrom, parsedRangeTo);

		req.setAttribute("queryResults", query);
		return render(req, resp, "query.jsp", "Query");
	}

}
