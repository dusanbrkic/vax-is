package com.sluzbenik.rest.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.xml.sax.SAXException;
import proj.xml.gradj.digitalni_sertifikat.DigitalniSertifikat;

import javax.ws.rs.*;
import java.io.IOException;

public interface DSService {
	
	@GET
	@Path("/testUnmarshal")
	@Produces("application/xml")
	public DigitalniSertifikat testUnmarshal();
	
	@GET
	@Path("/testMarshal")
	@Produces("application/xml")
	public DigitalniSertifikat testMarshal();
	
	@GET
	@Path("/testStore")
	@Produces("application/xml")
	public DigitalniSertifikat testStore();
	
	@GET
	@Path("/testRetrive/{id}")
	@Produces("application/xml")
	public DigitalniSertifikat testRetrive(@PathParam("id") Integer id);
	
	@GET
	@Path("/testRDF")
	public void testRDF() throws SAXException, IOException;
	
	@POST
	@Path("/testPostStore")
	@Produces("application/xml")
	public DigitalniSertifikat testPostStore(@RequestBody DigitalniSertifikat ds);
	

}
