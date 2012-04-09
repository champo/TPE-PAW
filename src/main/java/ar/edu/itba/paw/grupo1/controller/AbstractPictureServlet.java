package ar.edu.itba.paw.grupo1.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.paw.grupo1.model.Picture;

@SuppressWarnings("serial")
public class AbstractPictureServlet extends BaseServlet {

	public AbstractPictureServlet() {
		super();
	}

	protected Picture getPicture(HttpServletRequest req,
			HttpServletResponse resp) {

		Integer id = Integer.parseInt(req.getParameter("id"));
		int propId = Integer.parseInt(req.getParameter("propId"));
		String name = req.getParameter("name");
		String extension = req.getParameter("extension");
		
		return new Picture(id, propId, name, extension);
	}
}
