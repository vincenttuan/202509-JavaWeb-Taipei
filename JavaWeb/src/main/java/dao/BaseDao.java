package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDao {
	
	private static Connection conn;
	
	public Connection getConnection() {
		if(conn == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String username = "root";
				String password = "12345678";
				String jdbcUrl = "jdbc:mysql://localhost:3306/web?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Taipei&useSSL=false";
				//String jdbcUrl = "jdbc:mysql://localhost:3306/guestbook_db?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Taipei&useSSL=false&allowPublicKeyRetrieval=true";
				conn = DriverManager.getConnection(jdbcUrl, username, password);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return conn;
	}
	
	
}
