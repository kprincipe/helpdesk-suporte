package servidor;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import servlet.HelpdeskServlet;

import java.io.File;

public class IniciaServidor {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();

        tomcat.setPort(8080);
        tomcat.getConnector(); // Inicia o conector http

        String webapp = new File("src/main/webapp").getAbsolutePath();
        Context context = tomcat.addWebapp("", webapp);

        String nomeServlet = "HelpdeskServlet";

        Tomcat.addServlet(context, nomeServlet, new HelpdeskServlet());
        context.addServletMappingDecoded("/helpdesk", nomeServlet);

        tomcat.start();
        tomcat.getServer().await();
    }
}
