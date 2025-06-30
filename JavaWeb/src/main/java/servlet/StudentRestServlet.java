package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/rest/student")
public class StudentRestServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String json = """
						[
							{
								'name': 'John',
								'age': 18 
							},
							{
								'name': 'Mary',
								'age': 19 
							}
						]
					  """;
		resp.setContentType("application/json;charset=TUF-8");
		resp.getWriter().print(json);
	}
	
}
