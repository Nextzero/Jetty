import javax.servlet.*;
import java.io.IOException;

/**
 * Created by kinkoo on 2015/12/11.
 */
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
