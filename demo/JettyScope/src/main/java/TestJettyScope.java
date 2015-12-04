import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ResourceHandler;

public class TestJettyScope {

    public static void main(String[] args){
        Server jettyServer = new Server(8081);

        ContextHandler contextHandler = new ContextHandler();
        ContextHandler contextHandler1 = new ContextHandler();

        contextHandler.setHandler(contextHandler1);

        jettyServer.setHandler(contextHandler);

        try {
            jettyServer.start();
            jettyServer.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}