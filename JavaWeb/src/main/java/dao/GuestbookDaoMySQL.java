package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Guestbook;

public class GuestbookDaoMySQL extends BaseDao implements GuestbookDao {

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
		String sql = "insert into guestbook(name, message) values(?, ?)";
		try(PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
			pstmt.setString(1, name);
			pstmt.setString(2, message);
			// 儲存
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Integer id) {
		String sql = "delete from guestbook where id=?";
		try(PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
			pstmt.setInt(1, id);
			// 刪除
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Guestbook get(Integer id) {
		Guestbook guestbook = null;
		String sql = "select id, name, message, create_at from guestbook where id=?";
		try(PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
			pstmt.setInt(1, id);
			try(ResultSet rs = pstmt.executeQuery()) {
				if(rs.next()) { // 是否有資料
					guestbook = new Guestbook();
					guestbook.setId(rs.getInt("id"));
					guestbook.setName(rs.getString("name"));
					guestbook.setMessage(rs.getString("message"));
					guestbook.setCreateAt(rs.getTimestamp("create_at"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return guestbook;
	}

	@Override
	public void update(Integer id, String name, String message) {
		// TODO Auto-generated method stub
		
	}
	
}
