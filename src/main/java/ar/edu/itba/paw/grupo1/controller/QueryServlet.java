package ar.edu.itba.paw.grupo1.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.paw.grupo1.controller.BaseServlet;


public class QueryServlet extends BaseServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String operation = req.getParameter("operation");
		String property = req.getParameter("property");
		String rangeFrom = req.getParameter("range_from");
		String rangeTo = req.getParameter("range_to");
		
		if (operation == null && property == null && rangeFrom == null && rangeTo == null ) {
			render(req, resp, "query.jsp", "Query");
			return;
		}
		
		if (operation == null) {
			operation = "any";
		}
		if (property == null) {
			property = "any";
		}

		double parsedRangeFrom = 0;
		double parsedRangeTo = 0;
		boolean badParse = false;
		
		try {
			if (rangeFrom != null) {
				parsedRangeFrom = Double.parseDouble(rangeFrom);
			}
			if (rangeTo != null) {
				parsedRangeTo = Double.parseDouble(rangeTo);
			}
		} catch(NumberFormatException e) {
			badParse = true;
		}

		if ( badParse ||
			 ( rangeFrom != null && parsedRangeFrom < 0 ) ||
             ( rangeTo != null && parsedRangeTo < 0 ) ||
             ( rangeFrom != null && rangeTo != null && parsedRangeTo < parsedRangeFrom ) ){
			
			req.setAttribute("invalidRange", true);
			req.setAttribute("queryResults", new ArrayList<String>());
			render(req, resp, "query.jsp", "Query");
			return;
		}
		
		if (rangeFrom == null) {
			parsedRangeFrom = 0;
		}
		if (rangeTo == null) {
			parsedRangeTo = Double.MAX_VALUE;
		}
		
		String query = "SELECT operationType, propertyType, address, neighbourhood, price FROM properties WHERE ";

		if (operation.equals("selling")) {
			query += "operationType = 0 AND ";
		} else if (operation.equals("leasing")) {
			query += "operationType = 1 AND ";
		}
		
		if (property.equals("house")) {
			query += "propertyType = 0 AND ";
		} else if (property.equals("flat")) {
			query += "propertyType = 1 AND ";
		}
		
		query += "price >= " + parsedRangeFrom + " AND price <= " + parsedRangeTo;
		
		// TODO: actual query

		// render query result

		
		render(req, resp, "query.jsp", "Query");
	}

}
