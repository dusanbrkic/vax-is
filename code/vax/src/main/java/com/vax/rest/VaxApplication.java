package com.vax.rest;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.event.EventListener;

import com.vax.rest.util.XMLDatabase;

@SpringBootApplication
@ImportResource({ "classpath*:podaci/cxf-servlet.xml" })
public class VaxApplication {

	public static void main(String[] args) {
		SpringApplication.run(VaxApplication.class, args);
	}
	
	@Bean
	public ServletRegistrationBean<CXFServlet> cxfServlet() {
		CXFServlet cxfServlet = new CXFServlet();
		ServletRegistrationBean<CXFServlet> servletReg = new ServletRegistrationBean<CXFServlet>(cxfServlet, "/*");
		servletReg.setLoadOnStartup(1);
		return servletReg;
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void createCollections() {
		XMLDatabase.createCollectionIfNotExist("/db/sample/library/ds");
		XMLDatabase.createCollectionIfNotExist("/db/sample/library/interesovanje");
		XMLDatabase.createCollectionIfNotExist("/db/sample/library/izvestaj");
		XMLDatabase.createCollectionIfNotExist("/db/sample/library/obrazac");
		XMLDatabase.createCollectionIfNotExist("/db/sample/library/potvrda");
		XMLDatabase.createCollectionIfNotExist("/db/sample/library/zahtev");
		
	}
	
	
	

}
