package dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import model.Guestbook;

public class GuestbookDaoInMemory implements GuestbookDao {
	// In-Memory 訪客留言紀錄
	private static List<Guestbook> guestbooks;
	
	static {
		// 預設初始資訊 3 筆
		guestbooks = new CopyOnWriteArrayList<>();
		guestbooks.add(new Guestbook(1, "Alice", "Hello, this is Alice!", new Timestamp(System.currentTimeMillis())));
		guestbooks.add(new Guestbook(2, "Bob", "Nice to meet you!", new Timestamp(System.currentTimeMillis())));
		guestbooks.add(new Guestbook(3, "Charlie", "Java Web is fun!", new Timestamp(System.currentTimeMillis())));
	}
	
	@Override
	public List<Guestbook> findAll() {
		return guestbooks;
	}

	@Override
	public void add(String name, String message) {
		Integer nextId = guestbooks.stream().mapToInt(Guestbook::getId).max().orElse(0) + 1;
		Timestamp createAt = new Timestamp(System.currentTimeMillis());
		Guestbook guestbook = new Guestbook(nextId, name, message, createAt);
		guestbooks.add(guestbook);
	}
	
}
