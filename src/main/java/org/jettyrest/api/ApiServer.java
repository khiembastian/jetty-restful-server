package org.jettyrest.api;

import com.google.common.base.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApiServer {
    private Logger log = LoggerFactory.getLogger(ApiServer.class);

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-server.xml");
        final Service service = context.getBean(Service.class);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                service.stopAndWait();
            }
        });

        service.startAndWait();
    }
}
