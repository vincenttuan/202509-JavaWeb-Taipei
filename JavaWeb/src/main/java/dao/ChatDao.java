package dao;

import java.util.List;

import model.Chat;

public interface ChatDao {
	void add(String username, String question, String answer);
	void delete(Integer id);
	List<Chat> queryByUsername(String username);
}
