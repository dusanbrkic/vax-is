package com.vax.rest.inter;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.xml.sax.SAXException;

import proj.xml.gradj.digitalni_sertifikat.DigitalniSertifikat;

public interface DSService {
	
	@GET
	@Path("/testUnmarshal")
	@Produces("application/xml")
	public DigitalniSertifikat testUnmarshal() throws SAXException, IOException;
	
	@GET
	@Path("/testMarshal")
	@Produces("application/xml")
	public DigitalniSertifikat testMarshal();
	
	@GET
	@Path("/testStore")
	@Produces("application/xml")
	public DigitalniSertifikat testStore();
	
	@GET
	@Path("/testRetrive")
	@Produces("application/xml")
	public DigitalniSertifikat testRetrive();

}
