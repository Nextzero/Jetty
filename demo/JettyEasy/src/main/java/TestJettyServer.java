import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerWrapper;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class TestJettyServer {

    public static class HandlerTest extends HandlerWrapper{

        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            //target标明了请求路径
            System.out.println("当前请求路径为：" + target);

            //获取当前请求的方法
            String method = request.getMethod();
            System.out.println(method);

            //获取GET方法的请求数据
            if(method.equalsIgnoreCase("GET")){
                String queryString = request.getQueryString();
                System.out.println(queryString);
            }else if(method.equalsIgnoreCase("POST")){
                ServletInputStream inputStream = request.getInputStream();
                byte[] buf = new byte[256];
                inputStream.read(buf);
                inputStream.close();
                System.out.println(new String(buf));
            }else{
                //其他方法暂不考虑
            }

            //返回数据到浏览器
            String responseData = "Hello, i'am Jetty, now is : " + new Date();
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(responseData.getBytes());
            outputStream.flush();
            outputStream.close();
        }
    }

    public static void main(String[] args){
        Server jettyServer = new Server(8081);
        HandlerTest handler = new HandlerTest();
        jettyServer.setHandler( handler);

        try {
            jettyServer.start();
            jettyServer.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

