package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserDaoMySQL extends BaseDao implements UserDao {

	@Override
	public User findByUsername(String username) {
		String sql = "select username, salt, hash from user where username = ?";
		try(PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
			pstmt.setString(1, username);
			
			try(ResultSet rs = pstmt.executeQuery()) {
				if(rs.next()) {
					User user = new User();
					user.setUsername(rs.getString("username"));
					user.setSalt(rs.getString("salt"));
					user.setHash(rs.getString("hash"));
					return user;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> findAllUsers() {
		List<User> users = new ArrayList<>();
		String sql = "select username, salt, hash from user";
		try(Statement stmt = getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);) {
			
			while (rs.next()) {
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setSalt(rs.getString("salt"));
				user.setHash(rs.getString("hash"));
				
				users.add(user);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
}
