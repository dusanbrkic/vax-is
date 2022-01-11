package com.vax.rest.inter;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.xml.sax.SAXException;

import proj.xml.gradj.interesovanje.Interesovanje;

public interface InteresovanjeService {
	@GET
	@Path("/testUnmarshal")
	@Produces("application/xml")
	public Interesovanje testUnmarshal() throws SAXException, IOException;
	
	@GET
	@Path("/testMarshal")
	@Produces("application/xml")
	public Interesovanje testMarshal();
	
	@GET
	@Path("/testStore")
	@Produces("application/xml")
	public Interesovanje testStore();
	
	@GET
	@Path("/testRetrive")
	@Produces("application/xml")
	public Interesovanje testRetrive();

}
