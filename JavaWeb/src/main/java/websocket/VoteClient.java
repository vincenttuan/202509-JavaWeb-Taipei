package websocket;

import java.net.URI;
import javax.websocket.*;

@ClientEndpoint
public class VoteClient {
	
	private Session session;
	
	public VoteClient() throws Exception {
		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
		container.connectToServer(this, URI.create("ws://localhost:8080/JavaWeb/vote/server"));
	}
	
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
	}
	
	@OnMessage
	public void onMessage(String message) {
		
	}
	
	@OnClose
	public void onClose(Session session) {
		
	}
	
	@OnError
	public void onError(Throwable throwable) {
		
	}
	
	public void sendMessage(String jsonString) {
		session.getAsyncRemote().sendText(jsonString);
	}
	
	public static void main(String[] args) throws Exception  {
		new Thread(() -> {
			try {
				VoteClient voteClient = new VoteClient();
				while(true) {
					if(Math.random() > 0.5) {
						voteClient.sendMessage("{\"vote\":\"recall\", \"value\":1}");
					} else {
						voteClient.sendMessage("{\"vote\":\"nonRecall\", \"value\":1}");
					}
					Thread.sleep(10);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
		
		

	}

}
