package servlet;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Tea;

@WebServlet("/tea")
public class TeaServlet extends HttpServlet {
	// 商品資訊
	private Map<String, Tea> teasMap = Map.of(
			"A01", new Tea("A01", "紅茶", 20, 10),
			"A02", new Tea("A02", "綠茶", 25, 10));
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
}
