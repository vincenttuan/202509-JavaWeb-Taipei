package filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/report/*", "/chat/*"})
public class LoginFilter extends HttpFilter {
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println(request.getRequestURL());
		
		// 判斷是否有登入 ?
		HttpSession session = request.getSession();
		if(session != null && session.getAttribute("username") != null) {
			// 放行指令
			chain.doFilter(request, response);
		} else {
			//response.getWriter().print("Forbidden ! Please login ~");
			// 將目的地 url 存放在 session 屬性中
			session.setAttribute("requestUrl", request.getRequestURL());
			// 重導到登入頁面
			response.sendRedirect("/JavaWeb/login.html");
		}
		
	}
}
