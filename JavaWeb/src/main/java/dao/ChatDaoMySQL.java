package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
		List<Chat> chats = new ArrayList<>();
		String sql = "select id, username, question, answer, create_time from chat where username=?";
		try(PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
			pstmt.setString(1, username);
			
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					Chat chat = new Chat();
					chat.setId(rs.getInt("id"));
					chat.setUsername(rs.getString("username"));
					chat.setQuestion(rs.getString("question"));
					chat.setAnswer(rs.getString("answer"));
					chat.setCreateTime(rs.getDate("create_time"));
					// 加入到集合
					chats.add(chat);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chats;
	}
	
}
