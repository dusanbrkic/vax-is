package com.vax.rest.service;

import java.io.IOException;
import java.util.List;

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
