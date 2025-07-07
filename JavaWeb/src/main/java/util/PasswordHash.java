package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

// 本段程式要實現 SHA-256 雜湊生成與比對
public class PasswordHash {
	
	// 產生隨機 salt
	public static String generateSalt() {
		SecureRandom sr = new SecureRandom();
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return Base64.getEncoder().encodeToString(salt);
	}
	
	// 密碼雜湊函式
	public static String getHashPassword(String password, String salt) {
		
		try {
			// 建立一個 SHA-256 的雜湊演算法實例
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			// 先加鹽
			md.update(salt.getBytes());
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
	
	// 比對
	public static boolean checkPassword(String inputPassword, String salt, String storedHash) {
		String inputHash = getHashPassword(inputPassword, salt);
		return inputHash.equals(storedHash);
	}
	
	
	public static void main(String[] args) {
		String password = "1234";
		System.out.println("password:" + password);
		String salt = generateSalt();
		System.out.println("salt:" + salt);
		String storePassword = getHashPassword(password, salt);
		System.out.println("hash:" + storePassword);
		System.out.println(storePassword.length());
		
		String inputPassword = "1234";
		boolean check = checkPassword(inputPassword, salt, storePassword);
		System.out.println(check);
	}
	
}
