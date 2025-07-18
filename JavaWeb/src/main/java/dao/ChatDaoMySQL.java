package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.Chat;

public class ChatDaoMySQL extends BaseDao implements ChatDao {

	@Override
	public void add(String username, String question, String answer) {
		String sql = "insert into chat(username, question, answer) values (?, ?, ?)";
		try(PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
			pstmt.setString(1, username);
			pstmt.setString(2, question);
			pstmt.setString(3, answer);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Chat> queryByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
