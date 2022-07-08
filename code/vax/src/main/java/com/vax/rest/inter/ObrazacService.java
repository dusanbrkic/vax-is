package com.vax.rest.inter;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.xml.sax.SAXException;

import proj.xml.gradj.obrazac.Obrazac;

public interface ObrazacService {
	public void store(Obrazac obrazac);

	public Obrazac testRetrieve();
	// TODO iz XML stringa treba da se pravi rdf
	public void testRDF() throws SAXException, IOException;
    public List<Obrazac> getAllObrasci();
	Obrazac getObrazacByJmbg(String jmbg);
	Obrazac getObrazacByBrPasosa(String brPasosa);
}
