package websocket;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/chatserver")
public class ChatServer {
	
	// 建立一個集合用來存放已連接的客戶端 Socket session 資訊
	private static List<Session> sessions = new CopyOnWriteArrayList<>();
	
	// 廣播
	public void broadcase(String message, String sessionId) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		message = String.format("%8s id=%s 說: %s", sdf.format(new Date()), sessionId, message);
		for(Session session : sessions) {
			if(session.isOpen()) {
				session.getAsyncRemote().sendText(message);
			}
		}
	}
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.printf("Client 已經連上線 session id = %s%n", session.getId());
	}
	
	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.printf("Server 端收到來自 session id = %s 的訊息: %s%n", session.getId(), message);
		// 廣播
		broadcase(message, session.getId());
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
