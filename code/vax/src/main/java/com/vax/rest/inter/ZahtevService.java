package com.vax.rest.inter;

import org.xml.sax.SAXException;
import proj.xml.gradj.potvrda.Potvrda;
import proj.xml.gradj.zahtev.Zahtev;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.IOException;

public interface ZahtevService {

	@GET
	@Path("/testUnmarshal")
	@Produces("application/xml")
	public Zahtev testUnmarshal() throws SAXException, IOException;

	@GET
	@Path("/testMarshal")
	@Produces("application/xml")
	public Zahtev testMarshal();

	@GET
	@Path("/testStore")
	@Produces("application/xml")
	public Zahtev testStore();

	@GET
	@Path("/testRetrive")
	@Produces("application/xml")
	public Zahtev testRetrive();
}
