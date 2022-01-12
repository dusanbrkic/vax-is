package com.vax.rest.inter;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.xml.sax.SAXException;

import proj.xml.sluzb.izvestaj.Izvestaj;

public interface IzvestajService {
	@GET
	@Path("/testUnmarshal")
	@Produces("application/xml")
	public Izvestaj testUnmarshal();
	
	@GET
	@Path("/testMarshal")
	@Produces("application/xml")
	public Izvestaj testMarshal();
	
	@GET
	@Path("/testStore")
	@Produces("application/xml")
	public Izvestaj testStore();
	
	@GET
	@Path("/testRetrieve")
	@Produces("application/xml")
	public Izvestaj testRetrieve();
	
	@GET
	@Path("/testRDF")
	public void testRDF() throws SAXException, IOException;

}
