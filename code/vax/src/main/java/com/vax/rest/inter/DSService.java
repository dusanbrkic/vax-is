package com.vax.rest.inter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import proj.xml.gradj.digitalni_sertifikat.DigitalniSertifikat;

public interface DSService {
	
	@GET
	@Path("/testUnmarshal")
	@Produces("application/xml")
	public DigitalniSertifikat testUnmarshal();
	
	@GET
	@Path("/testMarshal")
	@Produces("application/xml")
	public DigitalniSertifikat testMarshal();

}
