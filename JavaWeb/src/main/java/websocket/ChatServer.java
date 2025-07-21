package websocket;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/chatserver")
public class ChatServer {
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.printf("Client 已經連上線 session id = %s%n", session.getId());
	}
	
	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.printf("Server 端收到來自 session id = %s 的訊息: %s%n", 
				session.getId(), message);
	}
	
	@OnClose
	public void onClose(Session session) {
		System.out.printf("Client 已經離線 session id = %s%n", session.getId());
	}
	
	@OnError
	public void onError(Session session, Throwable throwable) {
		System.out.printf("發生錯誤 session id = %s 錯誤原因: %s%n", 
				session.getId(), throwable);
	}
	
}
