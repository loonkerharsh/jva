package mypkg;

import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpFilter;
import java.io.IOException;
import jakarta.servlet.*;

/**
 * Servlet Filter implementation class myFilter
 */
public class myFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public myFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		String html="<html>"
				+ "<body>"
				+ "<p>"
				+ "    <span style=\"float:left;\"><a href=Home><img height=45px width=45px src=shop.png></a></span>\r\n"
				+ "    <span style=\"float:right;\"><a href=kart><img height=45px width=45px src=cart.png></a></span>\r\n"
				+ "</p><br><hr><br>"
				+ "</body></html>";
		response.getWriter().println(html);
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
