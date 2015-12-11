package test.jetty.scope;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.ScopedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class B extends ScopedHandler{

    @Override
    protected void doStart() throws Exception {
        System.out.println(this.getClass().getName());
        super.doStart();
    }

    @Override
    public void doScope(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println(this.getClass().getName() + ":doScpoe");
        super.nextScope(target,baseRequest,request,response);
    }

    @Override
    public void doHandle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println(this.getClass().getName() + ":doHandle");
        super.nextHandle(target,baseRequest,request,response);
    }
}
