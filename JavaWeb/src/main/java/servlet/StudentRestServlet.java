package servlet;

import java.io.IOException;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Student;

@WebServlet("/rest/student")
public class StudentRestServlet extends HttpServlet {
	
	private Gson gson = new Gson();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Student[] students = {
				new Student("John", 19), new Student("Mary", 20), new Student("Candy", 21)
		};
		
		String json = gson.toJson(students);
		
		resp.setContentType("application/json;charset=UTF-8");
		resp.getWriter().print(json);
	}
	
}
