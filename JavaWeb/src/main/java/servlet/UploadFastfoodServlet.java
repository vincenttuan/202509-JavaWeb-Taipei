package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import dao.FastfoodDao;
import dao.FastfoodDaoMySQL;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/upload/fastfood")
@MultipartConfig(
		fileSizeThreshold = 1 * 1024 * 1024, // 檔案超過 1 MB 才寫入暫存檔
		maxFileSize = 10 * 1024 * 1024 // 最大檔案不得超過 10 MB
)
public class UploadFastfoodServlet extends HttpServlet {
	
	private FastfoodDao dao = new FastfoodDaoMySQL();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 重導到 /WEB-INF/view/upload_fastfood.jsp
		req.getRequestDispatcher("/WEB-INF/view/upload_fastfood.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		
		// 取得上傳資料
		String productId = req.getParameter("productId");
		String productName = req.getParameter("productName");
		String productPrice = req.getParameter("productPrice");
		// 取得上傳檔案資料
		Part filePart = req.getPart("productImage"); // 檔案實體
		byte[] fileBytes = filePart.getInputStream().readAllBytes(); // 檔案轉 byte[]
		String productImage = Base64.getEncoder().encodeToString(fileBytes); // byte[] 轉 base64 字串
		
		// 儲存
		String message = "";
		try {
			dao.addFastfood(productId, productName, productPrice, productImage);
			message = "資料儲存成功";
		} catch (Exception e) {
			message = "資料儲存失敗: " + e.getMessage();
		}
		
		req.setAttribute("message", message);
		req.getRequestDispatcher("/WEB-INF/view/upload_result.jsp").forward(req, resp);
	}
	
}
