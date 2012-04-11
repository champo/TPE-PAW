import java.io.IOException;

import ar.edu.itba.paw.grupo1.controller.BaseServlet.java;


public class QueryServlet extends java {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		render(req, resp, "query.jsp", "Query");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		render(req, resp, "query.jsp", "Query");
	}
}
