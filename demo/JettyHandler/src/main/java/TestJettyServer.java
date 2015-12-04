import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestJettyServer {

    public class HandlerTest extends HandlerWrapper{
        protected String name;

        public HandlerTest(String name){
            this.name = name;
        }

        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            if(target.equals(name)){
                System.out.println("当前Handler名称为：" + name + "   当前请求路径为：" + target);
            }else{
                //根据target判断，若不是自己处理的请求是，则通过基础将请求向下传递
                super.handle(target, baseRequest, request, response);
            }
        }
    }

    public void start(){
        Server jettyServer = new Server(80);
        HandlerTest h1 = new HandlerTest("/path1");
        HandlerTest h2 = new HandlerTest("/path2");

        h2.setHandler(h1);
        jettyServer.setHandler( h2);

        try {
            jettyServer.start();
            jettyServer.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        TestJettyServer testJettyServer = new TestJettyServer();
        testJettyServer.start();
    }
}
