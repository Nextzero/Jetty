import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class ServletContextServer {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        // http://localhost:8080/hello
        context.addServlet(new ServletHolder(new HelloServlet()), "/hello");
        // http://localhost:8080/hello/kongxx
        context.addServlet(new ServletHolder(new HelloServlet("Hello Kongxx!")), "/hello/kongxx");

        // http://localhost:8080/goodbye
        context.addServlet(new ServletHolder(new GoodbyeServlet()), "/goodbye");
        // http://localhost:8080/goodbye/kongxx
        context.addServlet(new ServletHolder(new GoodbyeServlet("Goodbye kongxx!")), "/goodbye/kongxx");

        server.start();
        server.join();
    }
}