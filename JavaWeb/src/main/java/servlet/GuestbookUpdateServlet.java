package servlet;

import java.io.IOException;

import dao.GuestbookDao;
import dao.GuestbookDaoMySQL;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Guestbook;

@WebServlet("/guestbook/update")
public class GuestbookUpdateServlet extends HttpServlet {
	
	private GuestbookDao dao = new GuestbookDaoMySQL();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 根據 id 取得要修改的資料
		String id = req.getParameter("id");
		Guestbook guestbook = dao.get(Integer.valueOf(id));
		// forward 到指定修改頁
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/guestbook_update.jsp");
		req.setAttribute("guestbook", guestbook);
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	
}
