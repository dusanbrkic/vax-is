package com.vax.rest.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.ws.rs.Path;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import com.vax.rest.email.EmailService;
import com.vax.rest.repository.GradjaninRepository;
import com.vax.rest.repository.InteresovanjeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.vax.rest.service.InteresovanjeService;
import com.vax.rest.util.RDFUtil;
import com.vax.rest.util.XMLDatabase;
import com.vax.rest.util.XMLParser;

import proj.xml.gradj.interesovanje.Interesovanje;

@Service
public class InteresovanjeServiceImpl implements InteresovanjeService {

	@Autowired
	private InteresovanjeRepository interesovanjeRepository;
	
	@Autowired
	private GradjaninRepository gradjaninRepository;
	@Autowired
	private EmailService emailService;

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

	@Override
	public Interesovanje getInteresovanjeByJmbg(String jmbg) {
		return interesovanjeRepository.retrieveByJmbg(jmbg);
	}

	@Override
	public void createInteresovanje(Interesovanje i) {
		interesovanjeRepository.store(i);
		emailService.sendMailInteresovanjeCreated(i);
		
	}


}
