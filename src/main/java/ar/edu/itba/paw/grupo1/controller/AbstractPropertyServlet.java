package ar.edu.itba.paw.grupo1.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.paw.grupo1.model.Property;

@SuppressWarnings("serial")
public class AbstractPropertyServlet extends BaseServlet {

	public AbstractPropertyServlet() {
		super();
	}

	protected Property getProperty(HttpServletRequest req,
			HttpServletResponse resp) {

		Integer id = Integer.parseInt(req.getParameter("id"));
		int propertyType = Integer.parseInt(req.getParameter("propertyType"));
		int operationType = Integer.parseInt(req.getParameter("operationType"));
		String neighbourhood = req.getParameter("neighbouthood");
		double price = Double.parseDouble("price");
		int rooms = Integer.parseInt(req.getParameter("rooms"));
		double indoorSpace = Double
				.parseDouble(req.getParameter("indoorSpace"));
		double outdoorSpace = Double.parseDouble(req
				.getParameter("outdoorSpace"));
		String description = req.getParameter("description");
		boolean cable = req.getParameter("cable").equals("1");
		boolean phone = req.getParameter("cable").equals("1");
		boolean pool = req.getParameter("pool").equals("1");
		boolean lounge = req.getParameter("lounge").equals("1");
		boolean paddle = req.getParameter("paddle").equals("1");
		boolean barbecue = req.getParameter("barbecue").equals("1");
		boolean sold = req.getParameter("sold").equals("1");
		int userId = Integer.parseInt(req.getParameter("userId"));

		return new Property(id, propertyType, operationType, neighbourhood,
				price, rooms, indoorSpace, outdoorSpace, description, cable,
				phone, pool, lounge, paddle, barbecue, sold, userId);
	}
}
