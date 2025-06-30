package servlet;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.GuestbookDao;
import dao.GuestbookDaoMySQL;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 提供訪客留言版的 RESTful API 並支援 JSON 格式資料儲存
 * 
 * API 路徑與用法
 * ------------------------------------------
 * 1. 取得全部留言
 * 	  GET  /api/guestbook
 * 2. 取得單筆留言
 * 	  GET  /api/guestbook/{id}
 * 3. 新增留言
 * 	  POST /api/guestbook
 * 	  Request Body (JSON): {"name": "John", "message": "abc"}
 * 4. 修改留言
 *    PUT  /api/guestbook/{id}
 * 	  Request Body (JSON): {"name": "Mary", "message": "def"}
 * 5. 刪除留言
 *    DELETE /api/guestbook/{id}
 * */

@WebServlet("/api/guestbook/*")
public class GuestbookRestServlet extends HttpServlet {
	
	private GuestbookDao dao = new GuestbookDaoMySQL();
	// Gson 設定:日期格式與 null 欄位
	private Gson gson = new GsonBuilder()
			.setDateFormat("yyyy-MM-dd HH:mm:ss")
			.serializeNulls()
			.create();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	
}
