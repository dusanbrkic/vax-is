package com.vax.rest.ws;

import java.math.BigInteger;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.ws.rs.Path;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.springframework.stereotype.Service;

import com.vax.rest.inter.DSService;
import com.vax.rest.util.XMLDatabase;
import com.vax.rest.util.XMLParser;

import proj.xml.gradj.digitalni_sertifikat.DigitalniSertifikat;
import proj.xml.gradj.digitalni_sertifikat.DigitalniSertifikat.OsnovniPodaci;

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
		try {
			os.setDatumIVremeIzdavanja(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
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
		XMLDatabase.storeXML("/db/sample/library", "1.xml", "proj.xml.gradj.digitalni_sertifikat", ds);
		
		return null;
	}

	@Override
	public DigitalniSertifikat testRetrive() {
		DigitalniSertifikat ds=(DigitalniSertifikat) XMLDatabase.retriveXML("/db/sample/library", "1.xml", "proj.xml.gradj.digitalni_sertifikat");
		return ds;
	}

}
