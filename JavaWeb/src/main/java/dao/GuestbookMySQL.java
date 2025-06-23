package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Guestbook;

public class GuestbookMySQL extends BaseDao implements GuestbookDao {

	@Override
	public List<Guestbook> findAll() {
		List<Guestbook> guestbooks = new ArrayList<>();
		String sql = "select id, name, message, create_at from guestbook order by id desc";
		
		try(Statement stmt = getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);) {
			
			while (rs.next()) {
				// 取得欄位資料
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String message = rs.getString("message");
				Timestamp createAt = rs.getTimestamp("create_at");
				// 建立 guestbook 物件
				Guestbook guestbook = new Guestbook(id, name, message, createAt);
				// 放到 guestbooks 集合中收集起來
				guestbooks.add(guestbook);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return guestbooks;
	}

	@Override
	public void add(String name, String message) {
		// TODO Auto-generated method stub
		
	}
	
}
