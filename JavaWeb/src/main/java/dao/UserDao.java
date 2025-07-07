package dao;

import java.util.List;

import model.User;

public interface UserDao {
	public User findByUsername(String username);
	public List<User> findAllUsers();
	public void updatePassword(String username, String newPassword);
}
