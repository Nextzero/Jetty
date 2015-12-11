package test.jetty.scope;

import org.eclipse.jetty.server.Server;

public class TestJettyScope {

    public static void main(String[] args){
        Server jettyServer = new Server(8081);

        A a = new A();
        B b = new B();
        C c = new C();
        D d = new D();

        c.setHandler(d);
        b.setHandler(c);
        a.setHandler(b);
        jettyServer.setHandler(a);

        try {
            jettyServer.start();
            jettyServer.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}