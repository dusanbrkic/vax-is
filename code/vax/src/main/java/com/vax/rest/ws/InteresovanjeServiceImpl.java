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

	@Override
	public void testRDF() throws SAXException, IOException {
		// TODO Auto-generated method stub
		
	}

	

}
