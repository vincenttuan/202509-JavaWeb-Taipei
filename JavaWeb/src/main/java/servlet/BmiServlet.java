package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/bmi_servlet")
public class BmiServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8"); // 告知瀏覽器回應內容的型態
		
		// 接收參數(height=170.5&weight=60.5)
		String height = req.getParameter("height");
		String weight = req.getParameter("weight");
		// 檢查是否有輸入參數 ?
		if(height == null || weight == null) {
			resp.getWriter().print("請輸入身高/體重"); // 在瀏覽器上印出資料
			return;
		}
		
		resp.getWriter().print("BMI Result"); // 在瀏覽器上印出資料
	}
	
}
