package servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Breakfast;
import model.Category;

@WebServlet("/breakfast")
public class BreakfastServlet extends HttpServlet {
	// 用一個 Map 來管理所有餐點
	private static Map<String, Breakfast> breakfastMap;
	
	// 初始化
	static {
		// 注入早餐資訊
		breakfastMap = new ConcurrentHashMap();
		// 注入主餐
		breakfastMap.put("M01", new Breakfast("M01", "漢堡", 35, Category.MAIN));
		breakfastMap.put("M02", new Breakfast("M02", "三明治", 30, Category.MAIN));
		breakfastMap.put("M03", new Breakfast("M03", "蛋餅", 25, Category.MAIN));
		// 注入飲料
		breakfastMap.put("B01", new Breakfast("B01", "紅茶", 15, Category.BEVERAGE));
		breakfastMap.put("B02", new Breakfast("B02", "豆漿", 20, Category.BEVERAGE));
		// 注入配料
		breakfastMap.put("E01", new Breakfast("E01", "加蛋", 10, Category.SIDE));
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		
		String[] mains = req.getParameterValues("main");
		String[] beverages = req.getParameterValues("beverage");
		String egg = req.getParameter("egg");
		String amount = req.getParameter("amount");
		
		resp.getWriter().print("早餐資訊:<br />");
		resp.getWriter().print("<table border='1' cellpadding='5' cellspacing='0'>");
		resp.getWriter().print("<tr>");
		resp.getWriter().print("<th>編號</th>");
		resp.getWriter().print("<th>品名</th>");
		resp.getWriter().print("<th>價格 NT$</th>");
		resp.getWriter().print("</tr>");
		for(Entry<String, Breakfast> item : breakfastMap.entrySet()) {
			resp.getWriter().print("<tr>");
			resp.getWriter().print("<td>" + item.getValue().getId() + "</td>");
			resp.getWriter().print("<td>" + item.getValue().getName() + "</td>");
			resp.getWriter().print("<td align='right'>" + item.getValue().getPrice() + "</td>");
			resp.getWriter().print("</tr>");
		}
		resp.getWriter().print("</table>");
		resp.getWriter().print("<hr />");
		
		resp.getWriter().print("主餐:");
		for(String id : mains) {
			resp.getWriter().print(id + ":" + breakfastMap.get(id).getName() + " ");
		}
		resp.getWriter().print("<p />");
		
		resp.getWriter().print("飲料:");
		for(String id : beverages) {
			resp.getWriter().print(id + ":" + breakfastMap.get(id).getName());
		}
		resp.getWriter().print("<p />");
		
		resp.getWriter().print("加蛋:");
		if(egg != null) {
			resp.getWriter().print(egg + ":" + breakfastMap.get(egg).getName());
		}
		resp.getWriter().print("<p />");
		
		resp.getWriter().print("數量:");
		resp.getWriter().print(amount);
		resp.getWriter().print("<p />");
		
	}
	
}
