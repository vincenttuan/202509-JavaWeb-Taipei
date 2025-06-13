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
		// 1.告知瀏覽器回應內容的型態
		resp.setContentType("text/html;charset=UTF-8"); 
		
		// 2.接收參數(height=170.5&weight=60.5)
		String height = req.getParameter("height");
		String weight = req.getParameter("weight");
		
		// 3.檢查是否有輸入參數 ?
		if(height == null || weight == null) {
			resp.getWriter().print("請輸入身高/體重"); // 在瀏覽器上印出資料
			return;
		}
		
		// 4.計算 bmi (利用 Double.parseDouble 將字串轉浮點數)
		double h = Double.parseDouble(height);
		double w = Double.parseDouble(weight);
		double bmi = w / Math.pow(h/100, 2);
		
		// 5.在網頁上印出計算結果
		resp.getWriter().print("BMI Result: " + bmi); // 在瀏覽器上印出資料
	}
	
}
