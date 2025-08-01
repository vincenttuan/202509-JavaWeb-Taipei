package servlet;

import java.io.IOException;

import dao.UserDao;
import dao.UserDaoMySQL;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import util.PasswordHash;

@WebServlet("/user/change/password")
public class UserChangePasswordServlet extends HttpServlet {
	
	private UserDao userDao = new UserDaoMySQL();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		// 檢查是否有 session
		HttpSession session = req.getSession(false);
		if(session == null) {
			resp.getWriter().print("請先登入");
			return;
		}
		// 檢查是否有 session 的 username 屬性是否有資料
		if(session.getAttribute("username") == null) {
			resp.getWriter().print("請先登入");
			return;
		}
		// 重導到修改密碼頁面
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/update_password.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		String oldPassword = req.getParameter("oldPassword");
		String newPassword = req.getParameter("newPassword");
		
		HttpSession session = req.getSession(false);
		String username = session.getAttribute("username").toString();
		
		// 1. 請先確認 oldPassword 是否正確(Homework)
		User user = userDao.findByUsername(username);
		String oldSalt = user.getSalt();
		String oldHash = user.getHash();
		if(!PasswordHash.checkPassword(oldPassword, oldSalt, oldHash)) {
			resp.getWriter().print("舊密碼不正確");
			return;
		}
		
		// 2. 密碼修改
		userDao.updatePassword(username, newPassword);
		resp.getWriter().print("密碼修改成功 !");
		
	}
	
	
}
