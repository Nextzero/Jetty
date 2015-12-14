import javax.servlet.*;
import java.io.IOException;


public class HelloFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("hello Filter, init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("hello Filter, doFilter");
    }

    @Override
    public void destroy() {
        System.out.println("hello Filter, destory");
    }
}
