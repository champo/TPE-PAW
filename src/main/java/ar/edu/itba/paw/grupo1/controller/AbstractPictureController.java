package ar.edu.itba.paw.grupo1.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;

import ar.edu.itba.paw.grupo1.model.Picture;

@Controller
public abstract class AbstractPictureController extends BaseController {

	public AbstractPictureController() {
		super();
	}

	protected Picture buildPicture(HttpServletRequest req,
			HttpServletResponse resp) {

		int propId = -1;
		
		try {
			propId = Integer.parseInt(req.getParameter("propId"));
		} catch (NumberFormatException e) {
			return null;
		}
		
		Integer id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String extension = req.getParameter("extension");
		
		return new Picture(id, propId, name, extension);
	}
}
