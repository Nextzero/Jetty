import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpResponeUtils {

    public static void responseHtmlError(HttpServletResponse response, int code, String msg){
        try{
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            response.sendError(code, new String(msg.getBytes(), "UTF-8"));
        }catch(IOException e){
        }
    }

    public static void responseJson(HttpServletResponse response, String json){
        try{
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Cache-Control", "no-cache");
            response.setContentType("application/json");
            byte[] resultBytes =json.getBytes();
            response.setContentLength(resultBytes.length);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(resultBytes, 0, resultBytes.length);
            outputStream.flush();
            outputStream.close();
        }catch(IOException e){
        }
    }

    public static String getClientIp(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");

        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
