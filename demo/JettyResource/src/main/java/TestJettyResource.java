import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ResourceHandler;

public class TestJettyResource {

    public static void main(String[] args){
        Server jettyServer = new Server(8081);

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("F:/web");

        jettyServer.setHandler(resourceHandler);

        try {
            jettyServer.start();
            jettyServer.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}