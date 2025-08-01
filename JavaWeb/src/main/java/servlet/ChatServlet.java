package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import dao.ChatDao;
import dao.ChatDaoMySQL;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Chat;

@WebServlet(urlPatterns = {"/chat", "/chat/history", "/chat/delete"})
public class ChatServlet extends HttpServlet {
	
	private ChatDao chatDao = new ChatDaoMySQL();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//resp.getWriter().print("chat ok");
		HttpSession session = req.getSession(false);
		String username = session.getAttribute("username").toString();
		
		switch(req.getServletPath()) {
			case "/chat":
				RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/chat.jsp");
				rd.forward(req, resp);
				break;
			case "/chat/delete":
				Integer id = Integer.valueOf(req.getParameter("id"));
				chatDao.delete(id);
			case "/chat/history":	
				List<Chat> chats = chatDao.queryByUsername(username);
				req.setAttribute("chats", chats);
				req.getRequestDispatcher("/WEB-INF/view/chat_result.jsp").forward(req, resp);
				break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		
		String message = req.getParameter("message");
		String ollamaResponse = "";
		
		// 準備一個 json 的請求
		String payload = """
				{ 
					"model": "qwen2.5:0.5b", 
					"messages": [ 
						{ 
							"role": "user", 
							"content": "%s" 
						} 
					], 
					"stream": false 
				}
				""";
		payload = String.format(payload, message);
		
		// 1.發送 POST 請求到 ollama
		// 1.1 建立連線
		URL url = new URL("http://localhost:11434/api/chat");
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		// 1.2 連線設定
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setDoOutput(true);
		// 1.3 資料送出
		try(OutputStream os = conn.getOutputStream()) {
			os.write(payload.getBytes("UTF-8"));
		}
		
		// 2.讀取回應
		StringBuffer sb = new StringBuffer();
		// Java IO 串接鏈
		// conn.getInputStream() -> byte stream 逐"位元組"讀取
		// InputStreamReader     -> char stream 逐"字"讀取
		// BufferedReader        -> 讀取到記憶體,可以逐"行"讀取(字串), 效率較高
		try(BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"))) {
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		}
		
		// 3.分析結果
		//resp.getWriter().print(sb.toString());
		// 分析 from: "content": to: }, 之間的內容
		int from = sb.toString().indexOf("\"content\"");
		int to = sb.toString().indexOf("},");
		String content = sb.toString().substring(from+11, to-1);
		
		// 4.將對話結果存放到資料表中
		HttpSession session = req.getSession(false);
		String username = (String)session.getAttribute("username");
		String question = message;
		String answer = content;
		chatDao.add(username, question, answer);
		
		// 5.將結果資料傳給瀏覽器
		//resp.getWriter().print(content);
		// 6.取得該使用者的歷史對話紀錄 
		List<Chat> chats = chatDao.queryByUsername(username);
		req.setAttribute("chats", chats);
		req.getRequestDispatcher("/WEB-INF/view/chat_result.jsp").forward(req, resp);
		
	}
	
}
