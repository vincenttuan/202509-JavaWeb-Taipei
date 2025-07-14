package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/chat")
public class ChatServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//resp.getWriter().print("chat ok");
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/chat.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		
		// 3.將結果資料傳給瀏覽器
		resp.getWriter().print(sb.toString());
		
	}
	
}
