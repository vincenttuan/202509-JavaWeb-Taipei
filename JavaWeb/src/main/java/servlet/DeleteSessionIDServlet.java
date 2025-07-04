package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/delete/sessionId")
public class DeleteSessionIDServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		HttpSession session = req.getSession(false);
		if(session == null) {
			resp.getWriter().print("目前沒有 session");
		} else {
			session.invalidate(); // session 強制過期
			resp.getWriter().print("已將目前 session 強制過期");
		}
		
	}
}
