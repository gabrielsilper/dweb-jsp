package com.github.gabrielsilper;

import com.github.gabrielsilper.servlet.PersonServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class Main {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();

        int port = 8081;

        tomcat.setPort(port);
        tomcat.getConnector(); // Inicia o conector http;

        String webapp = new File("src/main/webapp").getAbsolutePath();

        Context context = tomcat.addWebapp("", webapp);

        String servletName = "PersonServlet";

        Tomcat.addServlet(context, servletName, new PersonServlet());
        context.addServletMappingDecoded("/pessoas", servletName);

        System.out.println("Starting server and listening on port" + port + "...");

        tomcat.start();
        tomcat.getServer().await();
    }
}