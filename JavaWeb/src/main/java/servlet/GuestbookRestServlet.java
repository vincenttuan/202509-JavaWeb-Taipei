package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.GuestbookDao;
import dao.GuestbookDaoMySQL;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Guestbook;

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
		String pathInfo = req.getPathInfo();
		resp.setContentType("application/json;charset=UTF-8");
		if(pathInfo == null || pathInfo.equals("/") || pathInfo.equals("/*")) {
			// 取得全部
			List<Guestbook> guestbooks = dao.findAll();
			String json = gson.toJson(guestbooks);
			resp.getWriter().write(json);
		} else {
			// 取得單筆
			String id = pathInfo.substring(1); // "/123" 會得到 "123"
			Guestbook guestbook = dao.get(Integer.valueOf(id));
			if(guestbook != null) {
				resp.getWriter().print(gson.toJson(guestbook));
			} else {
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		}
	}
	
	// 新增
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 取得前端傳來的 json 物件
		BufferedReader reader = req.getReader(); 
		// reader(裡面是 json 物件) 轉 Guestbook 物件
		Guestbook gb = gson.fromJson(reader, Guestbook.class);
		// 進行新增
		dao.add(gb.getName(), gb.getMessage());
		// 回應狀態
		resp.setStatus(HttpServletResponse.SC_CREATED); // SC: States Code
	}
	
	
	
}
