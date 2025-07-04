package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		// 取得登入表單的 username / password
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		// 判斷
		if(!(username.equals("admin") && password.equals("1234"))) {
			// 登入失敗
			resp.getWriter().print("登入失敗");
			return;
		}
		resp.getWriter().print("登入成功");
		// 建立 session 並取得 sessionId
		HttpSession session = req.getSession();
		resp.getWriter().print("Session is new: " + session.isNew() + "<p />");
		resp.getWriter().print("Session Id: " + session.getId() + "<p />");
	}
}
