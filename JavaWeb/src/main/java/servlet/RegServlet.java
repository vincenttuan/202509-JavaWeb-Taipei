package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/JavaWeb/reg")
public class RegServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 收到來自表單的參數
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		
		// 建立一個 JSP 分派器
		RequestDispatcher rd = req.getRequestDispatcher("reg_result.jsp");
		// 準備要傳給 jsp 渲染的資料
		req.setAttribute("name", name);
		req.setAttribute("phone", phone);
		// 傳送
		rd.forward(req, resp);
	}
	
}
