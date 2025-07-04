package servlet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// 製作動態認證碼圖片
@WebServlet("/code/image")
public class CodeImageServlet extends HttpServlet {
	
	// 利用 Java2D 產生動態認證碼
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 利用 Random 產生一組四位數隨機認證碼
		Random random = new Random();
		String code = String.format("%04d", random.nextInt(10000)); // 0000~9999
		// 將 code 存放到 session 變數中以利後續比對
		HttpSession session = req.getSession();
		session.setAttribute("code", code);
		// 1. 建立圖檔暫存區
		BufferedImage img = new BufferedImage(80, 30, BufferedImage.TYPE_INT_RGB);
		// 2. 建立畫布
		Graphics g = img.getGraphics();
		// 3. 設定顏色
		g.setColor(Color.YELLOW);
		// 4. 塗滿背景
		g.fillRect(0, 0, 80, 30);
		
		// 設定回傳類型
		resp.setContentType("image/png");
		// 將圖片以串流回傳給瀏覽器
		ImageIO.write(img, "PNG", resp.getOutputStream());
		
	}
	
}
