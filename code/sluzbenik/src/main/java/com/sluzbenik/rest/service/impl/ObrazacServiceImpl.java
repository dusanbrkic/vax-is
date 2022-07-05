package com.sluzbenik.rest.service.impl;

import com.sluzbenik.rest.service.ObrazacService;
import com.sluzbenik.rest.util.RDFUtil;
import com.sluzbenik.rest.util.XMLDatabase;
import com.sluzbenik.rest.util.XMLParser;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import proj.xml.gradj.obrazac.Obrazac;
import proj.xml.gradj.obrazac.Obrazac.PodaciOPacijentu;

import javax.ws.rs.Path;
import java.io.IOException;


@Service
@Path("/obrazac")
public class ObrazacServiceImpl implements ObrazacService {

	@Override
	public Obrazac testUnmarshal() {
		
		System.out.println("Testing unmarshal");
		
		Obrazac obrazac = (Obrazac) XMLParser.unmarshal("proj.xml.gradj.obrazac", "obrazac.xsd", "obrazac_primer.xml",true,false,null);	
		System.out.println(obrazac);	
		
		
		return obrazac;
	}

	@Override
	public Obrazac testMarshal() {
		Obrazac obrazac = new Obrazac();
		obrazac.setPodaciOPacijentu(new PodaciOPacijentu());
		/*
		try {
			
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		*/
		
		XMLParser.marshal("proj.xml.gradj.obrazac", "obrazac.xsd",obrazac,System.out,false);
		return null;
	}

	@Override
	public Obrazac testStore() {
		Obrazac obrazac = (Obrazac) XMLParser.unmarshal("proj.xml.gradj.obrazac", "obrazac.xsd", "obrazac_primer.xml",true,false,null);
		XMLDatabase.storeXML("/db/sample/library", "4.xml", "proj.xml.gradj.obrazac", obrazac);
		return null;
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

}
