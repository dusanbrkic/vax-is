package com.sluzbenik.rest.service.impl;

import com.sluzbenik.rest.service.IzvestajService;
import com.sluzbenik.rest.util.RDFUtil;
import com.sluzbenik.rest.util.XMLDatabase;
import com.sluzbenik.rest.util.XMLParser;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import proj.xml.sluzb.izvestaj.Izvestaj;

import javax.ws.rs.Path;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
public class IzvestajServiceImpl implements IzvestajService {

	@Override
	public Izvestaj testUnmarshal(){
		
		System.out.println("Testing unmarshal");
		
		Izvestaj izvestaj = (Izvestaj) XMLParser.unmarshal("proj.xml.sluzb.izvestaj", "izvestaj.xsd", "izvestaj_primer.xml",true,false,null);	
		System.out.println(izvestaj);	
		
				
		
		//upis u bazu
		return izvestaj;
	}

	@Override
	public Izvestaj testMarshal() {
		Izvestaj izvestaj = new Izvestaj();
		Date d=new Date();
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(d);
		try {
			izvestaj.setDatumOd(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		
		XMLParser.marshal("proj.xml.sluzb.izvestaj", "izvestaj.xsd",izvestaj,System.out,false);
		return null;
	}

	@Override
	public Izvestaj testStore() {
		Izvestaj izvestaj = (Izvestaj) XMLParser.unmarshal("proj.xml.sluzb.izvestaj", "izvestaj.xsd", "izvestaj_primer.xml",true,false,null);
		XMLDatabase.storeXML("/db/sample/library", "3.xml", "proj.xml.sluzb.izvestaj", izvestaj);
		return null;
	}

	@Override
	public Izvestaj testRetrieve() {
		Izvestaj izvestaj = (Izvestaj) XMLDatabase.retriveXML("/db/sample/library", "3.xml", "proj.xml.sluzb.izvestaj");
		return izvestaj;
	}

	@Override
	public void testRDF() throws SAXException, IOException {
		String xmlFilePath = "./src/main/resources/xml/izvestaj_primer.xml";
		
		String rdfFilePath = "./src/main/resources/rdf/izvestaj.rdf";
		
		//kreira RDF fajl
		RDFUtil.generateRDFFromXML(xmlFilePath, rdfFilePath);
		
		//upis u bazu
		RDFUtil.updateFuseki(rdfFilePath, "izvestaj");
	}

	@Override
	public Izvestaj generisiIzvestaj(LocalDateTime fromTimestamp, LocalDateTime toTimestamp) {
		// TODO
		return null;
	}

}
