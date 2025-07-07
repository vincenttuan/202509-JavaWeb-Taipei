package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// 本段程式要實現 SHA-256 雜湊生成與比對
public class PasswordHash {
	
	// 密碼雜湊函式
	public static String getHashPassword(String password) {
		
		try {
			// 建立一個 SHA-256 的雜湊演算法實例
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			// 將密碼字串轉成位元組陣列並進行雜湊運算
			byte[] hashBytes = md.digest(password.getBytes());
			// 建立一個 StringBuilder 用來組合雜湊後的十六進位字串
			StringBuilder sb = new StringBuilder();
			for(byte b : hashBytes) {
				String hex = Integer.toHexString(0xff & b); // 轉成正整數並轉為十六進位
				if(hex.length() == 1) sb.append('0'); // 若只有一位數, 補 0
				sb.append(hex); // 加入到字串中
			}
			// 回傳完整的十六進位雜湊字串
			return sb.toString();
			
		} catch (NoSuchAlgorithmException e) {
			System.err.println("無此雜湊演算法");
		}
		return "";
	}
	
	public static void main(String[] args) {
		String password = "1234";
		String hashPassword = getHashPassword(password);
		System.out.println(hashPassword);
		System.out.println(hashPassword.length());
	}
	
}
