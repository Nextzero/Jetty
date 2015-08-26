import net.sf.json.JSONObject;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.HandlerWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kinkoo on 2015/4/13.
 */
public class TestHandle extends HandlerWrapper {
    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        super.handle(target, baseRequest, request, response);
        System.out.println("test handle");
        Map a = new HashMap();
        a.put("a","212121");

        baseRequest.setHandled(true);
        HttpResponeUtils.responseJson(response, JSONObject.fromObject(a).toString());
    }
}
