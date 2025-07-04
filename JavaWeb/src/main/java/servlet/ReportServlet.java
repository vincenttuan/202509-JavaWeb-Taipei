package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/report")
public class ReportServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = req.getSession(false);
		
		if(session == null || session.getAttribute("username") == null) {
			resp.getWriter().print("此為會員專屬報告請先登入!");
			return;
		}
		
		String username = session.getAttribute("username").toString();
		// 此網頁必須要有會員登入才能看
		resp.getWriter().print(username + "您好 !");
		resp.getWriter().print("會員專屬報告:<p />");
		resp.getWriter().print("明天放假一天");
		
		
	}
	
}
