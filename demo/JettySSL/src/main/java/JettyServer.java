import org.eclipse.jetty.server.*;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Created by kinkoo on 2015/4/13.
 */
public class JettyServer {

    public static void main(String[] s){
        Server server = new Server();

        HttpConfiguration https_config = new HttpConfiguration();
        https_config.setSecureScheme("https");
        https_config.setSecurePort(443);
        https_config.setOutputBufferSize(32768);
        https_config.addCustomizer(new SecureRequestCustomizer());

        SslContextFactory sslContextFactory = new SslContextFactory();
        sslContextFactory.setKeyStorePath("openssl/keystore");
        sslContextFactory.setKeyStorePassword("123456");
        sslContextFactory.setKeyManagerPassword("123456");

        ServerConnector httpsConnector = new ServerConnector(server,
                new SslConnectionFactory(sslContextFactory,"http/1.1"),
                new HttpConnectionFactory(https_config));
        httpsConnector.setPort(443);
        httpsConnector.setIdleTimeout(500000);
        server.addConnector(httpsConnector);

        WebAppContext webApp = new WebAppContext();
        webApp = new WebAppContext();
        webApp.setContextPath("/");
        webApp.setResourceBase("WebRoot");
        webApp.setInitParameter("org.eclipse.jetty.servlet.Default.dirAllowed", "false");
        webApp.setInitParameter("org.eclipse.jetty.servlet.Default.useFileMappedBuffer", "false");

        //webApp.setHandler(new TestHandle());
        server.setHandler(webApp);

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
