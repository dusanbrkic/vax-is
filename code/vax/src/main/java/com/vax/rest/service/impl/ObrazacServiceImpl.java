package com.vax.rest.service.impl;

import java.io.IOException;
import java.util.List;

import com.vax.rest.repository.ObrazacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.vax.rest.service.ObrazacService;
import com.vax.rest.util.RDFUtil;
import com.vax.rest.util.XMLDatabase;

import proj.xml.gradj.obrazac.Obrazac;


@Service
public class ObrazacServiceImpl implements ObrazacService {
	@Autowired
	ObrazacRepository obrazacRepository;

	@Override
	public void store(Obrazac obrazac) {
		XMLDatabase.storeXML("/db/sample/library", "4.xml", "proj.xml.gradj.obrazac", obrazac);
	}

	@Override
	public Obrazac testRetrieve() {
		Obrazac obrazac = (Obrazac) XMLDatabase.retriveXML("/db/sample/library", "4.xml", "proj.xml.gradj.obrazac");
		return obrazac;
	}

	@Override
	public void testRDF() throws SAXException, IOException {
		String xmlFilePath = "./src/main/resources/xml/obrazac_primer.xml";
		
		String rdfFilePath = "./src/main/resources/rdf/obrazac.rdf";
		
		//kreira RDF fajl
		RDFUtil.generateRDFFromXML(xmlFilePath, rdfFilePath);
		
		//upis u bazu
		RDFUtil.updateFuseki(rdfFilePath, "obrazac");
	}

	@Override
	public List<Obrazac> getAllObrasci() {
		return obrazacRepository.getAll();
	}

	@Override
	public Obrazac getObrazacByJmbg(String jmbg) {
		return obrazacRepository.getObrazacByJmbg(jmbg);
	}

	@Override
	public Obrazac getObrazacByBrPasosa(String brPasosa) {
		return obrazacRepository.getObrazacByBrPasosa(brPasosa);
	}

}
