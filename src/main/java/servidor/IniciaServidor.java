package servidor;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import servlet.HelpdeskServlet;

import java.io.File;

public class IniciaServidor {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat(); // Instancia Tomcat para servidor web

        tomcat.setPort(8080); // Define porta de acesso
        tomcat.getConnector(); // Inicia o conector http

        String webapp = new File("src/main/webapp").getAbsolutePath(); // Obtém diretório da aplicação web
        Context context = tomcat.addWebapp("", webapp); // Conecta aplicação ao contexto do Tomcat

        String nomeServlet = "HelpdeskServlet"; // Define nome da rota para o servlet do helpdesk

        Tomcat.addServlet(context, nomeServlet, new HelpdeskServlet()); // Adiciona servlet ao contexto do Tomcat
        context.addServletMappingDecoded("/helpdesk", nomeServlet); // Mapeia rota do servlet

        tomcat.start(); // Inicializa servidor Tomcat
        tomcat.getServer().await(); // Recebe respostas do clientes2
    }
}
