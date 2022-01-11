package com.vax.rest.ws;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.ws.rs.Path;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.vax.rest.inter.ObrazacService;
import com.vax.rest.util.XMLDatabase;
import com.vax.rest.util.XMLParser;

import proj.xml.gradj.obrazac.Obrazac;
import proj.xml.gradj.obrazac.Obrazac.PodaciOPacijentu;


@Service
@Path("/obrazac")
public class ObrazacServiceImpl implements ObrazacService {

	@Override
	public Obrazac testUnmarshal() throws SAXException, IOException {
		
		System.out.println("Testing unmarshal");
		
		Obrazac obrazac = (Obrazac) XMLParser.unmarshal("proj.xml.gradj.obrazac", "obrazac.xsd", "obrazac_primer.xml",true,false,null);	
		System.out.println(obrazac);	
		
		/*
		
		String xmlFilePath = "./src/main/resources/xml/obrazac_primer.xml";
		
		String rdfFilePath = "./src/main/resources/rdf/rezultat_test.rdf";
		MetadataExtractor metadataExtractor = new MetadataExtractor();
		
		System.out.println("[INFO] Extracting metadata from RDFa attributes...");
		try {
			metadataExtractor.extractMetadata(
					new FileInputStream(new File(xmlFilePath)), 
					new FileOutputStream(new File(rdfFilePath)));
		} catch (TransformerException e) {
			
			e.printStackTrace();
		}
		*/
				
		
		//upis u bazu
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

}
