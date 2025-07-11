package servlet;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import dao.UserDao;
import dao.UserDaoMySQL;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import util.PasswordHash;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private UserDao userDao = new UserDaoMySQL();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		// 取得登入表單的 username / password / code
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String code = req.getParameter("code");
		
		// 建立 session 並取得 sessionId
		HttpSession session = req.getSession();
		
		// 判斷帳號 username
		User user = userDao.findByUsername(username);
		
		if(user == null) {
			// 登入失敗
			resp.getWriter().print("登入失敗 - 無此帳號");
			if(session != null) {
				session.invalidate();
			}
			return;
		}
		
		// 判斷密碼 password
		boolean passwordMatch = PasswordHash.checkPassword(password, user.getSalt(), user.getHash());
		if(!passwordMatch) {
			// 登入失敗
			resp.getWriter().print("登入失敗 - 密碼錯誤");
			if(session != null) {
				session.invalidate();
			}
			return;
		}
		
		// 判斷驗證碼 code
		String codeInSession = session.getAttribute("code").toString(); // 取得 session 屬性中的 code
		if(!code.equals(codeInSession)) {
			// 登入失敗
			resp.getWriter().print("登入失敗 - 驗證碼錯誤");
			if(session != null) {
				session.invalidate();
			}
			return;
		}
		
		// 判斷 session 是否有 requestUrl 屬性資料
		if(session.getAttribute("requestUrl") != null) {
			// 重導網址
			resp.sendRedirect(session.getAttribute("requestUrl").toString());
			// 清除 requestUrl
			session.setAttribute("requestUrl", null);
			return;
		}
		
		resp.getWriter().print("登入成功 <p />");
		session.setAttribute("username", username); // 將 username 存放到 session 屬性中
		resp.getWriter().print("Session is new: " + session.isNew() + "<p />");
		resp.getWriter().print("Session Id: " + session.getId() + "<p />");
	}
}
