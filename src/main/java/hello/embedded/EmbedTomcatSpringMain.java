package hello.embedded;

import hello.servlet.HelloServlet;
import hello.spring.HelloConfig;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;

public class EmbedTomcatSpringMain {

    public static void main(String[] args) throws LifecycleException {

        System.out.println("EmbedTomcatSpringMain.main");
        //톰캣 설정
        Tomcat tomcat = new Tomcat();
        Connector connector = new Connector();
        connector.setPort(8080);
        tomcat.setConnector(connector);


        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(HelloConfig.class);

        //스프링 mvc 디스패처 서블릿 생성, 스프링 컨테이너 연결

        DispatcherServlet dispatcher = new DispatcherServlet();


        //서블릿 등록
        Context context = tomcat.addContext("", "/");

        tomcat.addServlet("","dispatcher",dispatcher);





//
//        File docBaseFile = new File(context.getDocBase());
//
//        if (!docBaseFile.isAbsolute()) {
//
//            docBaseFile = new File(((org.apache.catalina.Host) context.getParent()).getAppBaseFile(), docBaseFile.getPath());
//
//        }
//
//        docBaseFile.mkdirs();

        tomcat.addServlet("","helloServlet", new HelloServlet());
        context.addServletMappingDecoded("/hello-spring","helloServlet");
        tomcat.start();
    }
}
