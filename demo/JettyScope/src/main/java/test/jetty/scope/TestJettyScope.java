package test.jetty.scope;

import org.eclipse.jetty.server.Server;

public class TestJettyScope {

    public static void main(String[] args){
        Server jettyServer = new Server(8081);

        A a = new A();
        B b = new B();
        C c = new C();
        C1 c1= new C1();
        D d = new D();

        c.setHandler(d);
        c1.setHandler(c);
        b.setHandler(c1);
        a.setHandler(b);
        jettyServer.setHandler(a);
        //a,b,c1,c,d
        try {
            jettyServer.start();
            jettyServer.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}