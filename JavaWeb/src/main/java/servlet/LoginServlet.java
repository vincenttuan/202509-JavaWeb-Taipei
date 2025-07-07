package servlet;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private static List<User> users;
	
	static {
		users = new CopyOnWriteArrayList<>();
		// 請先建立 3 個使用者其中 salt 與 hash 請透過 PasswordHash.java 得到
		// 1.帳號: admin, 密碼: 1234, salt: oKbdpbcvQqP+Oa+Uy0XjdA==, hash: e8c929460c099c457a06a70050e5b0e339bf3cd9841acb2ce57765a1b837f856
		// 2.帳號: john,  密碼: 1234, salt: rLky4w7gGHvtwy7TCklMOA==, hash: 6d894de2d714f161cc943c841717f5dfb1c37f63b235e1e6efe0d49559affb8e
		// 3.帳號: mary,  密碼: 5678, salt: FZWu03S4tEt/S1gZ63J89Q==, hash: 2fdaed2d97b89351858d82a6358cb8f6e759ef1e7c1bb59d783851f542f5abcb
		users.add(new User("admin", "oKbdpbcvQqP+Oa+Uy0XjdA==", "e8c929460c099c457a06a70050e5b0e339bf3cd9841acb2ce57765a1b837f856"));
		users.add(new User("john", "rLky4w7gGHvtwy7TCklMOA==", "6d894de2d714f161cc943c841717f5dfb1c37f63b235e1e6efe0d49559affb8e"));
		users.add(new User("mary", "FZWu03S4tEt/S1gZ63J89Q==", "2fdaed2d97b89351858d82a6358cb8f6e759ef1e7c1bb59d783851f542f5abcb"));
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		// 取得登入表單的 username / password / code
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String code = req.getParameter("code");
		
		// 建立 session 並取得 sessionId
		HttpSession session = req.getSession();
		// 判斷帳密 username & password
		if(!(username.equals("admin") && password.equals("1234"))) {
			// 登入失敗
			resp.getWriter().print("登入失敗 - 帳密錯誤");
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
		
		resp.getWriter().print("登入成功 <p />");
		session.setAttribute("username", username); // 將 username 存放到 session 屬性中
		resp.getWriter().print("Session is new: " + session.isNew() + "<p />");
		resp.getWriter().print("Session Id: " + session.getId() + "<p />");
	}
}
