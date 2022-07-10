package com.vax.rest.service;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.xml.sax.SAXException;

import proj.xml.sluzb.izvestaj.Izvestaj;

public interface IzvestajService {
	public Izvestaj testUnmarshal();
	
	public Izvestaj testMarshal();
	
	public Izvestaj testStore();
	
	public Izvestaj testRetrieve();
	
	public void testRDF() throws SAXException, IOException;

}
