package com.vax.rest;

import com.vax.rest.util.XMLDatabase;
import lombok.RequiredArgsConstructor;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;


import javax.xml.datatype.DatatypeConfigurationException;

@SpringBootApplication
@RequiredArgsConstructor
@ImportResource({"classpath*:podaci/cxf-servlet.xml"})
public class VaxApplication {
    public static void main(String[] args) {
        SpringApplication.run(VaxApplication.class, args);
    }

//    @Bean
//    public ServletRegistrationBean<CXFServlet> cxfServlet() {
//        CXFServlet cxfServlet = new CXFServlet();
//        ServletRegistrationBean<CXFServlet> servletReg = new ServletRegistrationBean<CXFServlet>(cxfServlet, "/*");
//        servletReg.setLoadOnStartup(1);
//        return servletReg;
//    }

    @EventListener(ApplicationReadyEvent.class)
    public void createCollections() throws DatatypeConfigurationException {
        XMLDatabase.createCollectionIfNotExist("/db/sample/library/ds");
        XMLDatabase.createCollectionIfNotExist("/db/sample/library/interesovanje");
        XMLDatabase.createCollectionIfNotExist("/db/sample/library/izvestaj");
        XMLDatabase.createCollectionIfNotExist("/db/sample/library/obrazac");
        XMLDatabase.createCollectionIfNotExist("/db/sample/library/potvrda");
        XMLDatabase.createCollectionIfNotExist("/db/sample/library/zahtev");
    }
    
    @Component
    public class EndpointsListener {

        private final Logger LOGGER =LoggerFactory.getLogger(EndpointsListener.class);

        @EventListener
        public void handleContextRefresh(ContextRefreshedEvent event) {
            ApplicationContext applicationContext = event.getApplicationContext();
            applicationContext.getBean(RequestMappingHandlerMapping.class)
                .getHandlerMethods().forEach((key, value) -> LOGGER.info("{} {}", key, value));
        }
    }
    
//    @Bean
//    public HttpMessageConverter oxmHttpMessageConverter() {
//    return new Jaxb2RootElementHttpMessageConverter();
//    }


}
