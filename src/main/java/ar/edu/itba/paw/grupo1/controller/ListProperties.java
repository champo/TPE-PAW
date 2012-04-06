package ar.edu.itba.paw.grupo1.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.paw.grupo1.ApplicationContainer;
import ar.edu.itba.paw.grupo1.service.PropertyService;

@SuppressWarnings("serial")
public class ListProperties extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		PropertyService propService = ApplicationContainer.getInstance().getObject(PropertyService.class);
			
		req.setAttribute("properties", propService.getAll() );
		req.getRequestDispatcher("/WEB-INF/jsp/listProperties.jsp").forward(req,
				resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	
}
