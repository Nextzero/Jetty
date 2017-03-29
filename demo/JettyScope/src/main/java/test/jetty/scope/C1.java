package test.jetty.scope;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.HandlerWrapper;
import org.eclipse.jetty.server.handler.ScopedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class C1 extends HandlerWrapper{

    @Override
    protected void doStart() throws Exception {
        System.out.println(this.getClass().getName());
        super.doStart();
    }

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println(this.getClass().getName() + ":handle");
        super.handle(target,baseRequest,request,response);
    }
}
