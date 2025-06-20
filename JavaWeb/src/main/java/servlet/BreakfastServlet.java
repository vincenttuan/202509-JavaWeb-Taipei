package servlet;

import java.io.IOException;
import java.util.Arrays;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/breakfast")
public class BreakfastServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		
		String[] mains = req.getParameterValues("main");
		String[] beverages = req.getParameterValues("beverage");
		String egg = req.getParameter("egg");
		String amount = req.getParameter("amount");
		
		resp.getWriter().print("主餐:");
		resp.getWriter().print(Arrays.toString(mains));
		resp.getWriter().print("<p />");
		
		resp.getWriter().print("飲料:");
		resp.getWriter().print(Arrays.toString(beverages));
		resp.getWriter().print("<p />");
		
		resp.getWriter().print("加蛋:");
		resp.getWriter().print(egg);
		resp.getWriter().print("<p />");
		
		resp.getWriter().print("數量:");
		resp.getWriter().print(amount);
		resp.getWriter().print("<p />");
		
	}
	
}
