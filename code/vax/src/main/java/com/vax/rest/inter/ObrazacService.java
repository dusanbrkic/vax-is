package com.vax.rest.inter;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.xml.sax.SAXException;

import proj.xml.gradj.obrazac.Obrazac;

public interface ObrazacService {
	@GET
	@Path("/testUnmarshal")
	@Produces("application/xml")
	public Obrazac testUnmarshal() throws SAXException, IOException;
	
	@GET
	@Path("/testMarshal")
	@Produces("application/xml")
	public Obrazac testMarshal();
	
	@GET
	@Path("/testStore")
	@Produces("application/xml")
	public Obrazac testStore();
	
	@GET
	@Path("/testRetrieve")
	@Produces("application/xml")
	public Obrazac testRetrieve();
}
