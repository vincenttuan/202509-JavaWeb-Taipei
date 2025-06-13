package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/mcdonald")
public class McdonaldServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		
		String mainmeal = req.getParameter("mainmeal");
		String sidemeal = req.getParameter("sidemeal");
		String beverage = req.getParameter("beverage");
		String amount = req.getParameter("amount");
		
		int mainmealPrice = Integer.parseInt(mainmeal);
		int sidemealPrice = Integer.parseInt(sidemeal);
		int beveragePrice = Integer.parseInt(beverage);
		int paymentAmount = Integer.parseInt(amount);
		
		
	}
	
}
