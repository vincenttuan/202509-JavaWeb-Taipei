package servlet;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet("/upload/fastfood")
@MultipartConfig(
		fileSizeThreshold = 1 * 1024 * 1024, // 檔案超過 1 MB 才寫入暫存檔
		maxFileSize = 10 * 1024 * 1024 // 最大檔案不得超過 10 MB
)
public class UploadFastfoodServlet extends HttpServlet {
	
	
}
