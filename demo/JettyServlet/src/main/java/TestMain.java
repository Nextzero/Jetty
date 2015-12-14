import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerWrapper;
import org.eclipse.jetty.server.handler.ScopedHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.lang.reflect.Field;

public class TestMain {
    /**
     * 检验HandlerWrapper构成的链结构
     * @param head
     */
    public static void checkHandlerWrapperLink(Server head){
        System.out.println("=======checkHandlerWrapperLink=======");
        HandlerWrapper handlerWrapper = head;
        while(null != handlerWrapper){
            System.out.println(handlerWrapper.getClass().getName());
            handlerWrapper = (HandlerWrapper)handlerWrapper.getHandler();
        }
        System.out.println("=====================================");
    }

    /**
     * 检验ScopeHandler构成的链结构，因为ScopeHandler的_nextScope是保护成员，因此通过反射来获取值
     * @param head
     */
    public static void checkScopeHandlerLink(Server head) throws IllegalAccessException {
        System.out.println("=======checkScopeHandlerLink===========");
        Field[] fields = ScopedHandler.class.getDeclaredFields();
        Field _nextScopyFiledID = null;
        for(Field field : fields){
            if(field.getName().equalsIgnoreCase("_nextScope")){
                field.setAccessible(true);
                _nextScopyFiledID = field;
                break;
            }
        }

        //检验handler链结构
        HandlerWrapper handlerWrapper = head;
        while(null != handlerWrapper){
            System.out.println(handlerWrapper.getClass().getName());

            if(handlerWrapper instanceof ScopedHandler){
                ScopedHandler scopedHandler = (ScopedHandler)handlerWrapper;
                while(null != scopedHandler) {
                    System.out.println("\t\t" + scopedHandler.getClass().getName());
                    ScopedHandler next = (ScopedHandler)_nextScopyFiledID.get(scopedHandler);
                    scopedHandler = next;
                }
            }

            handlerWrapper = (HandlerWrapper)handlerWrapper.getHandler();
        }
        System.out.println("=====================================");
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SECURITY | ServletContextHandler.SESSIONS);
        context.setContextPath("/test");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new HelloServlet()), "/hello"); // http://localhost:8080/test/hello
        //context.addFilter(new FilterHolder(new HelloFilter()), "/hello", EnumSet.allOf(DispatcherType.class));
        context.addServlet(new ServletHolder(new GoodbyeServlet()), "/goodbye"); // http://localhost:8080/test/goodbye

        checkHandlerWrapperLink(server);
        server.start();
        checkScopeHandlerLink(server);
        server.join();
    }
}