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
				
		try {
			
			int propertyType = Integer.parseInt(req.getParameter("propertyType"));
			int operationType = Integer.parseInt(req.getParameter("operationType"));
			String neighbourhood = req.getParameter("neighbourhood");
			double price = Double.parseDouble(req.getParameter("price"));
			int rooms = Integer.parseInt(req.getParameter("rooms"));
			double indoorSpace = Double.parseDouble(req.getParameter("indoorSpace"));
			double outdoorSpace = Double.parseDouble(req.getParameter("outdoorSpace"));
			String description = req.getParameter("description");
			boolean cable = req.getParameter("cable") != null;
			boolean phone = req.getParameter("phone") != null;
			boolean pool = req.getParameter("pool") != null;
			boolean lounge = req.getParameter("lounge") != null;			
			boolean paddle = req.getParameter("paddle") != null;
			boolean barbecue = req.getParameter("barbecue") != null;
			boolean sold = req.getParameter("sold") != null;
			int userId = Integer.parseInt(req.getParameter("userId"));
				
			Integer id = null;
			if (!req.getParameter("id").equals("")) {
				id = Integer.parseInt(req.getParameter("id"));
			}
			
			return new Property(id, propertyType, operationType, neighbourhood, price, rooms, 
						indoorSpace, outdoorSpace, description, cable, phone, pool, lounge, paddle, 
						barbecue, sold, userId);

			

		} catch (NumberFormatException e) {
			e.printStackTrace();
			//TODO handle exception properly
			return null;
		}

	}
}
