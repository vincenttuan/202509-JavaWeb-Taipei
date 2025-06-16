package servlet;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Tea;

@WebServlet("/tea") // <-- Servlet path
public class TeaServlet extends HttpServlet {
	// 商品資訊
	private Map<String, Tea> teasMap = Map.of(
			"A01", new Tea("A01", "紅茶", 20, 10),
			"A02", new Tea("A02", "綠茶", 25, 10));
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		// 取得傳來的參數(接收參數)
		String id = req.getParameter("id");
		String isHot = req.getParameter("isHot");
		String sweetness = req.getParameter("sweetness");
		String bag = req.getParameter("bag");
		String amount = req.getParameter("amount");
		// 印出參數
		resp.getWriter().print("印出參數:<br />");
		resp.getWriter().print("id:" + id + "<br />");
		resp.getWriter().print("isHot:" + isHot + "<br />");
		resp.getWriter().print("sweetness:" + sweetness + "<br />");
		resp.getWriter().print("bag:" + bag + "<br />");
		resp.getWriter().print("amount:" + amount + "<br />");
		
		
	}
	
}
