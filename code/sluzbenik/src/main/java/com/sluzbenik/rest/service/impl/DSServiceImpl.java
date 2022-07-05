package com.sluzbenik.rest.service.impl;

import com.sluzbenik.rest.service.DSService;
import com.sluzbenik.rest.util.RDFUtil;
import com.sluzbenik.rest.util.XMLDatabase;
import com.sluzbenik.rest.util.XMLParser;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import proj.xml.gradj.digitalni_sertifikat.DigitalniSertifikat;
import proj.xml.gradj.digitalni_sertifikat.DigitalniSertifikat.OsnovniPodaci;

import javax.ws.rs.Path;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
@Path("/ds")
public class DSServiceImpl implements DSService {

	@Override
	public DigitalniSertifikat testUnmarshal() {
		System.out.println("Testing unmarshal");
		
		DigitalniSertifikat ds = (DigitalniSertifikat) XMLParser.unmarshal("proj.xml.gradj.digitalni_sertifikat", "digitalni_sertifikat.xsd", "digitalni_sertifikat_primer.xml",true,false,null);	
		System.out.println(ds);	
		
		
		return ds;
	}

	@Override
	public DigitalniSertifikat testMarshal() {
		DigitalniSertifikat ds=new DigitalniSertifikat();
		OsnovniPodaci os=new OsnovniPodaci();
		os.setBrojSertifikata(BigInteger.valueOf(100));
		Date d=new Date();
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(d);
		DigitalniSertifikat.OsnovniPodaci.DatumIVremeIzdavanja dsd = new DigitalniSertifikat.OsnovniPodaci.DatumIVremeIzdavanja();
		try {
			dsd.setValue(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
			os.setDatumIVremeIzdavanja(dsd);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		ds.setOsnovniPodaci(os);
		XMLParser.marshal("proj.xml.gradj.digitalni_sertifikat", "digitalni_sertifikat.xsd",ds,System.out,false);
		return null;
	}

	@Override
	public DigitalniSertifikat testStore() {
		DigitalniSertifikat ds = (DigitalniSertifikat) XMLParser.unmarshal("proj.xml.gradj.digitalni_sertifikat", "digitalni_sertifikat.xsd", "digitalni_sertifikat_primer.xml",true,false,null);
		XMLDatabase.storeXML("/db/sample/library/ds", "1.xml", "proj.xml.gradj.digitalni_sertifikat", ds);
		
		return null;
	}
	
	@Override
	public void testRDF() throws SAXException, IOException {

		
		String xmlFilePath = "./src/main/resources/xml/digitalni_sertifikat_primer.xml";
		
		String rdfFilePath = "./src/main/resources/rdf/digitalni_sertifikat.rdf";
		
		//kreira RDF fajl
		RDFUtil.generateRDFFromXML(xmlFilePath, rdfFilePath);
		
		//upis u bazu
		RDFUtil.updateFuseki(rdfFilePath, "ds");
		
	}

	@Override
	public DigitalniSertifikat testPostStore(DigitalniSertifikat ds) {
		XMLDatabase.storeXML("/db/sample/library/ds", "2.xml", "proj.xml.gradj.digitalni_sertifikat", ds);
		
		return ds;
	}

	@Override
	public DigitalniSertifikat testRetrive(Integer id) {
		System.out.println("Retrieve");
		DigitalniSertifikat ds=(DigitalniSertifikat) XMLDatabase.retriveXML("/db/sample/library/ds", Integer.toString(id)+".xml", "proj.xml.gradj.digitalni_sertifikat");
		return ds;
	}


}
