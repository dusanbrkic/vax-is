package com.vax.rest.ws;

import java.io.File;
import java.math.BigInteger;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.ws.rs.Path;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.vax.rest.inter.DSService;
import com.vax.rest.util.SchemaValidationEventHandler;

import proj.xml.gradj.digitalni_sertifikat.DigitalniSertifikat;
import proj.xml.gradj.digitalni_sertifikat.DigitalniSertifikat.OsnovniPodaci;

@Service
@Path("/ds")
public class DSServiceImpl implements DSService {

	@Override
	public DigitalniSertifikat testUnmarshal() {
		System.out.println("Testing unmarshal");
		
		try {
			JAXBContext context = JAXBContext.newInstance("proj.xml.gradj.digitalni_sertifikat");
			
			Unmarshaller unmarshaller = context.createUnmarshaller();
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(new File("./src/main/resources/xsd/digitalni_sertifikat.xsd"));
			unmarshaller.setSchema(schema);
            unmarshaller.setEventHandler(new SchemaValidationEventHandler());
			
			DigitalniSertifikat ds = (DigitalniSertifikat) unmarshaller.unmarshal(new File("./src/main/resources/xml/digitalni_sertifikat_primer.xml"));
			
			System.out.println(ds);
			
			return ds;
		} catch (JAXBException | SAXException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public DigitalniSertifikat testMarshal() {
		try {
			JAXBContext context = JAXBContext.newInstance("proj.xml.gradj.digitalni_sertifikat");
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(new File("./src/main/resources/xsd/digitalni_sertifikat.xsd"));
			marshaller.setSchema(schema);
            marshaller.setEventHandler(new SchemaValidationEventHandler());
			
			DigitalniSertifikat ds=new DigitalniSertifikat();
			OsnovniPodaci os=new OsnovniPodaci();
			os.setBrojSertifikata(BigInteger.valueOf(100));
			Date d=new Date();
			GregorianCalendar c = new GregorianCalendar();
			c.setTime(d);
			os.setDatumIVremeIzdavanja(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
			ds.setOsnovniPodaci(os);
			
			marshaller.marshal(ds, System.out);
		} catch (JAXBException | DatatypeConfigurationException | SAXException e) {
			e.printStackTrace();
		}
		return null;
	}

}
