package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/chat")
public class ChatServlet extends HttpServlet {
	
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
		
		
	}
	
}
