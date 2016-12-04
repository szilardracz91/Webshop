package WebShop.filters;
 
import java.io.IOException;
 


import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;


 
public class PostLoginFilter implements Filter {
 
    @Override
    public void destroy() {
    }
 
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException,
            ServletException {
    	 //HttpServletResponse resp = (HttpServletResponse) servletResponse;
    	
        filterChain.doFilter(servletRequest, servletResponse);
        //resp.sendRedirect("index.xhtml?faces-redirect=true");
        
    }
 
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
}