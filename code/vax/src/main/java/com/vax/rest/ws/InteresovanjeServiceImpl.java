package com.vax.rest.ws;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.ws.rs.Path;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.transform.TransformerException;

import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.vax.rest.inter.InteresovanjeService;
import com.vax.rest.util.MetadataExtractor;
import com.vax.rest.util.XMLDatabase;
import com.vax.rest.util.XMLParser;

import proj.xml.gradj.digitalni_sertifikat.DigitalniSertifikat;
import proj.xml.gradj.digitalni_sertifikat.DigitalniSertifikat.OsnovniPodaci;
import proj.xml.gradj.interesovanje.Interesovanje;

@Service
@Path("/interesovanje")
public class InteresovanjeServiceImpl implements InteresovanjeService {

	@Override
	public Interesovanje testUnmarshal() throws SAXException, IOException {
		System.out.println("Testing unmarshal");
		
		Interesovanje interesovanje = (Interesovanje) XMLParser.unmarshal("proj.xml.gradj.interesovanje", "interesovanje.xsd", "interesovanje_primer.xml",true,false,null);	
		System.out.println(interesovanje);	
		
		/*
		
		String xmlFilePath = "./src/main/resources/xml/interesovanje_primer.xml";
		
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
		return interesovanje;
	}

	@Override
	public Interesovanje testMarshal() {

		Interesovanje interesovanje= new Interesovanje();
		Date d=new Date();
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(d);
		try {
			interesovanje.setDatum(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		
		XMLParser.marshal("proj.xml.gradj.interesovanje", "interesovanje.xsd",interesovanje,System.out,false);
		return null;
	}

	@Override
	public Interesovanje testStore() {
		Interesovanje interesovanje = (Interesovanje) XMLParser.unmarshal("proj.xml.gradj.interesovanje", "interesovanje.xsd", "interesovanje_primer.xml",true,false,null);
		XMLDatabase.storeXML("/db/sample/library", "2.xml", "proj.xml.gradj.interesovanje", interesovanje);
		return null;
	}

	@Override
	public Interesovanje testRetrieve() {
		Interesovanje interesovanje=(Interesovanje) XMLDatabase.retriveXML("/db/sample/library", "2.xml", "proj.xml.gradj.interesovanje");
		return interesovanje;
	}

	

}
