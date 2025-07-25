package servlet;

import java.io.IOException;
import java.util.List;

import dao.GuestbookDao;
import dao.GuestbookDaoInMemory;
import dao.GuestbookDaoMySQL;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Guestbook;

@WebServlet("/guestbook")
public class GuestbookServlet extends HttpServlet {
	
	//private GuestbookDao dao = new GuestbookDaoInMemory();
	private GuestbookDao dao = new GuestbookDaoMySQL();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Guestbook> guestbooks = dao.findAll(); // 取得訪客歷史留言紀錄
		// 調用 guestbook.jsp
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/guestbook.jsp");
		req.setAttribute("guestbooks", guestbooks); // 將 guestbooks 資料帶給 jsp
		rd.forward(req, resp); // 傳送
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String message = req.getParameter("message");
		// 儲存
		dao.add(name, message);
		// 重導到 /JavaWeb/guestbook <-- 網址 
		resp.sendRedirect("/JavaWeb/guestbook");
	}
	
}
