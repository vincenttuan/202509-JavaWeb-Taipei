package websocket;

import java.util.concurrent.CopyOnWriteArrayList;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/vote/server")
public class VoteServer {
	
	private static CopyOnWriteArrayList<Session> sessions = new CopyOnWriteArrayList<>();
	
	@OnOpen
	public void onOpen(Session session) {
		sessions.add(session);
		System.out.printf("session id: %d 已加入%n", session.getId());
	}
	
	@OnClose
	public void onClose(Session session) {
		sessions.remove(session);
		System.out.printf("session id: %d 已離開%n", session.getId());
	}
	
	@OnMessage
	public void onMessage(String jsonString, Session session) {
		
		broadcast(jsonString);
	}
	
	@OnError
	public void onError(Session session, Throwable throwable) {
		System.out.printf("session id: %d 發生錯誤: %s%n", session.getId(), throwable.toString());
	}
	
	// 廣播
	private void broadcast(String jsonString) {
		sessions.forEach(s -> {
			if(s.isOpen()) {
				s.getAsyncRemote().sendText(jsonString);
			}
		});
	}
}
