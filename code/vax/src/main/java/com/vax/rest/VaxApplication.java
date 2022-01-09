package com.vax.rest;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

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

}
