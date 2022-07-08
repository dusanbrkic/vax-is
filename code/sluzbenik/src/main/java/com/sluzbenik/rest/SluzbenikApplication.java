package com.sluzbenik.rest;

import com.sluzbenik.rest.util.XMLDatabase;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@ImportResource({ "classpath*:podaci/cxf-servlet.xml" })
public class SluzbenikApplication {

	public static void main(String[] args) {
		SpringApplication.run(SluzbenikApplication.class, args);
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
		XMLDatabase.createCollectionIfNotExist("/db/sample/library/izvestaj");
		XMLDatabase.createCollectionIfNotExist("/db/sample/library/vakcinarnik");
	}

}
