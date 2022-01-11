package com.vax.rest.inter;

import org.xml.sax.SAXException;
import proj.xml.gradj.interesovanje.Interesovanje;
import proj.xml.gradj.potvrda.Potvrda;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.IOException;

public interface PotvrdaService {

	@GET
	@Path("/testUnmarshal")
	@Produces("application/xml")
	public Potvrda testUnmarshal() throws SAXException, IOException;

	@GET
	@Path("/testMarshal")
	@Produces("application/xml")
	public Potvrda testMarshal();

	@GET
	@Path("/testStore")
	@Produces("application/xml")
	public Potvrda testStore();

	@GET
	@Path("/testRetrive")
	@Produces("application/xml")
	public Potvrda testRetrive();
}