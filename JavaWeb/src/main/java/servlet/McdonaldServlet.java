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
		String amount   = req.getParameter("amount");
		
		int mainmealPrice = Integer.parseInt(mainmeal);
		int sidemealPrice = Integer.parseInt(sidemeal);
		int beveragePrice = Integer.parseInt(beverage);
		int paymentAmount = Integer.parseInt(amount);
		
		int total = mainmealPrice + sidemealPrice + beveragePrice; // 餐點總價
		int change = paymentAmount - total;
		
		if(change >= 0) {
			// 出單(付款金額足夠才能出單)
			resp.getWriter().print("結帳成功<p />");
			resp.getWriter().print("====================<p />");
			resp.getWriter().print("餐點金額:" + total + "<p />");
			resp.getWriter().print("付款金額:" + paymentAmount + "<p />");
			resp.getWriter().print("找零金額:" + change + "<p />");
		} else {
			// 結帳失敗
			resp.getWriter().print("結帳失敗<p />");
			resp.getWriter().print("====================<p />");
			resp.getWriter().print("餐點金額:" + total + "<p />");
			resp.getWriter().print("付款金額:" + paymentAmount + "<p />");
			resp.getWriter().print("不足金額:" + Math.abs(change) + "<p />");
		}
		
	}
}
