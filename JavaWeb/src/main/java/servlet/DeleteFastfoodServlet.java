package servlet;

import java.io.IOException;

import dao.FastfoodDao;
import dao.FastfoodDaoMySQL;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/delete/fastfood")
public class DeleteFastfoodServlet extends HttpServlet {
	
	private FastfoodDao fastfoodDao = new FastfoodDaoMySQL();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String productId = req.getParameter("productId");
		fastfoodDao.deleteFastfoodById(productId);
		String message = "刪除完畢";
		req.setAttribute("message", message);
		req.getRequestDispatcher("/WEB-INF/view/upload_result.jsp").forward(req, resp);
	}
	
}
