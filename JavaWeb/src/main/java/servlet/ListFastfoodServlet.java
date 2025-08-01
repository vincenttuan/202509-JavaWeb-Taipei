package servlet;

import java.io.IOException;
import java.util.List;

import dao.FastfoodDao;
import dao.FastfoodDaoMySQL;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Fastfood;

@WebServlet("/list/fastfood")
public class ListFastfoodServlet extends HttpServlet {
	
	private FastfoodDao fastfoodDao = new FastfoodDaoMySQL();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Fastfood> fastfoods = fastfoodDao.findAllFastfoods();
		req.setAttribute("fastfoods", fastfoods);
		req.getRequestDispatcher("/WEB-INF/view/list_fastfood.jsp").forward(req, resp);
	}
	
}
