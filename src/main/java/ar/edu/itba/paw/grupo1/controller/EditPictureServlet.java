package ar.edu.itba.paw.grupo1.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.paw.grupo1.ApplicationContainer;
import ar.edu.itba.paw.grupo1.controller.exception.InvalidParameterException;
import ar.edu.itba.paw.grupo1.model.Picture;
import ar.edu.itba.paw.grupo1.service.PictureService;

@SuppressWarnings("serial")
public class EditPictureServlet extends AbstractPictureServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Picture picture = null;
		if (req.getParameter("id") != null) {
			PictureService pictureService = ApplicationContainer.get(PictureService.class);

			picture = pictureService.getById(Integer.parseInt(req.getParameter("id")));
			req.setAttribute("edit", 1);
			req.setAttribute("picture", picture);
		} else {
			throw new InvalidParameterException();
		}
		render(req, resp, "editPicture.jsp", "Edit Picture");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		PictureService pictureService = ApplicationContainer.get(PictureService.class);
		Picture picture = getPicture(req, resp);

		pictureService.save(picture);
		
		resp.sendRedirect("propertyDetail?id=" + picture.getPropId());

	}
}
