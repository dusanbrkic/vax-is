package com.vax.rest.service;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.RequestBody;
import org.xml.sax.SAXException;

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

    public DigitalniSertifikat getSertifikatByJmbg(String jmbg);

	public DigitalniSertifikat getSertifikatByBrojPasosa(String brojPasosa);
}
