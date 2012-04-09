package ar.edu.itba.paw.grupo1.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.paw.grupo1.ApplicationContainer;
import ar.edu.itba.paw.grupo1.service.PictureService;

@SuppressWarnings("serial")
public class DeletePictureServlet extends BaseServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.sendRedirect("listProperties");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		PictureService pictureService = ApplicationContainer.get(PictureService.class); 
		pictureService.delete(Integer.parseInt(req.getParameter("id")));
		
		resp.sendRedirect("listProperties");
	}
}
