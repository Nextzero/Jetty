import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证Jetty Handler的工作方式
 *
 * 通过浏览器分别访问地址
 * http://127.0.0.1/path1
 * 输出：
 * 当前target路径为：/path1
 * 传递到链表的下一个handler去处理
 * 当前target路径为：/path1
 * Hello world jetty /path1
 *
 * http://127.0.0.1/path2
 * 输出：
 * 当前target路径为：/path2
 * Hello world jetty /path2
 */
public class TestJettyServer {

    public class HandlerTest extends HandlerWrapper{
        protected String path;

        public HandlerTest(String path){
            this.path = path;
        }

        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            System.out.println("当前target路径为：" + target);
            if(path.equalsIgnoreCase(target)){
                System.out.println("Hello world jetty " + path + "\n");

            }else{
                System.out.println("传递到链表的下一个handler去处理\n");
                super.handle(target,baseRequest,request,response);
            }
        }
    }

    public void start(){
        Server jettyServer = new Server(80);
        HandlerTest h1 = new HandlerTest("/path1");
        HandlerTest h2 = new HandlerTest("/path2");

        //通过setHandler，构建handler职责链
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
