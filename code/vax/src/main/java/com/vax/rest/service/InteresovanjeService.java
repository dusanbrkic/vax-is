package com.vax.rest.service;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.xml.sax.SAXException;

import proj.xml.gradj.interesovanje.Interesovanje;

public interface InteresovanjeService {
	
	public Interesovanje testUnmarshal();
	
	
	public Interesovanje testMarshal();
	
	
	public Interesovanje testStore();
	
	
	public Interesovanje testRetrieve();
	
	
	public void testRDF() throws SAXException, IOException;

    public Interesovanje getInteresovanjeByJmbg(String jmbg);
}
