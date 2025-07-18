package dao;

import java.util.List;

import model.Chat;

public interface ChatDao {
	void add(String username, String question, String answer);
	List<Chat> queryByUsername(String username);
}
