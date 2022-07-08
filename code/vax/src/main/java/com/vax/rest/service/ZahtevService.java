package com.vax.rest.service;

import org.xml.sax.SAXException;
import proj.xml.gradj.zahtev.Zahtev;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.IOException;
import java.util.List;

public interface ZahtevService {

	public Zahtev testUnmarshal();

	public Zahtev testMarshal();

	public Zahtev testStore();

	public Zahtev testRetrive();

	public void testRDF() throws SAXException, IOException;

    public List<Zahtev> retrieveAll();
}
