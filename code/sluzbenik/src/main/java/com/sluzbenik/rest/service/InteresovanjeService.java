package com.sluzbenik.rest.service;

import org.xml.sax.SAXException;
import proj.xml.gradj.interesovanje.Interesovanje;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.IOException;

public interface InteresovanjeService {
	@GET
	@Path("/testUnmarshal")
	@Produces("application/xml")
	public Interesovanje testUnmarshal();
	
	@GET
	@Path("/testMarshal")
	@Produces("application/xml")
	public Interesovanje testMarshal();
	
	@GET
	@Path("/testStore")
	@Produces("application/xml")
	public Interesovanje testStore();
	
	@GET
	@Path("/testRetrieve")
	@Produces("application/xml")
	public Interesovanje testRetrieve();
	
	@GET
	@Path("/testRDF")
	public void testRDF() throws SAXException, IOException;

}
