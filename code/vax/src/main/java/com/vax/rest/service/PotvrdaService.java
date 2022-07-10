package com.vax.rest.service;

import org.xml.sax.SAXException;
import proj.xml.gradj.potvrda.Potvrda;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.IOException;

public interface PotvrdaService {

	public Potvrda testUnmarshal();

	public Potvrda testMarshal();

	public Potvrda testStore();

	public Potvrda testRetrive();
	
	public void testRDF() throws SAXException, IOException;

    public Potvrda getPotvrdaByJmbg(String jmbg);
}
