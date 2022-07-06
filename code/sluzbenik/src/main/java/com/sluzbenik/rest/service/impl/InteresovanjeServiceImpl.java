package com.sluzbenik.rest.service.impl;

import com.sluzbenik.rest.service.InteresovanjeService;
import com.sluzbenik.rest.util.RDFUtil;
import com.sluzbenik.rest.util.XMLDatabase;
import com.sluzbenik.rest.util.XMLParser;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import proj.xml.gradj.interesovanje.Interesovanje;

import javax.ws.rs.Path;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
@Path("/interesovanje")
public class InteresovanjeServiceImpl implements InteresovanjeService {

	@Override
	public Interesovanje testUnmarshal() {
		System.out.println("Testing unmarshal");
		
		Interesovanje interesovanje = (Interesovanje) XMLParser.unmarshal("proj.xml.gradj.interesovanje", "interesovanje.xsd", "interesovanje_primer.xml",true,false,null);	
		System.out.println(interesovanje);	
		
		return interesovanje;
	}

	@Override
	public Interesovanje testMarshal() {

		Interesovanje interesovanje= new Interesovanje();
		
		Date d=new Date();
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(d);
		Interesovanje.Datum intD = new Interesovanje.Datum();
		try {
			intD.setValue(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
			interesovanje.setDatum(intD);
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

	@Override
	public void testRDF() throws SAXException, IOException {
		String xmlFilePath = "./src/main/resources/xml/interesovanje_primer.xml";
		
		String rdfFilePath = "./src/main/resources/rdf/interesovanje.rdf";
		
		//kreira RDF fajl
		RDFUtil.generateRDFFromXML(xmlFilePath, rdfFilePath);
		
		//upis u bazu
		RDFUtil.updateFuseki(rdfFilePath, "interesovanje");
	}

	

}