package filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/report/*"})
public class LoginFilter extends HttpFilter {
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		// 判斷是否有登入 ?
		HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute("username") != null) {
			// 放行指令
			chain.doFilter(request, response);
		} else {
			response.getWriter().print("Forbidden ! Please login ~");
		}
		
	}
}
