package servlet;

import java.io.IOException;

import dao.GuestbookDao;
import dao.GuestbookDaoMySQL;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/guestbook/delete")
public class GuestbookDeleteServlet extends HttpServlet {
	
	private GuestbookDao dao = new GuestbookDaoMySQL();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 取得欲刪除的 id
		String id = req.getParameter("id");
		// 進行刪除 (字串 id 要轉 Integer)
		dao.delete(Integer.valueOf(id));
		// 重導到 /JavaWeb/guestbook <-- 網址 
		resp.sendRedirect("/JavaWeb/guestbook");
	}
	
}
