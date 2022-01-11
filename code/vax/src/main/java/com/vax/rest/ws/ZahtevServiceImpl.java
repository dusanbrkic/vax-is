package com.vax.rest.ws;

import com.vax.rest.inter.ZahtevService;
import com.vax.rest.util.XMLDatabase;
import com.vax.rest.util.XMLParser;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import proj.xml.gradj.zahtev.Zahtev;

import javax.ws.rs.Path;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
@Path("/zahtev")
public class ZahtevServiceImpl implements ZahtevService {

	@Override
	public Zahtev testUnmarshal() throws SAXException, IOException {
		System.out.println("Testing unmarshal");

		Zahtev zahtev = (Zahtev) XMLParser.unmarshal("proj.xml.gradj.zahtev", "zahtev.xsd", "zahtev_primer.xml",true,false,null);
		System.out.println(zahtev);

		/*

		String xmlFilePath = "./src/main/resources/xml/zahtev_primer.xml";

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
		return zahtev;
	}

	@Override
	public Zahtev testMarshal() {

		Zahtev zahtev= new Zahtev();
		Date d=new Date();
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(d);
		try {
			zahtev.setDatum(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}

		XMLParser.marshal("proj.xml.gradj.zahtev", "zahtev.xsd",zahtev,System.out,false);
		return null;
	}

	@Override
	public Zahtev testStore() {
		Zahtev zahtev = (Zahtev) XMLParser.unmarshal("proj.xml.gradj.zahtev", "zahtev.xsd", "zahtev_primer.xml",true,false,null);
		XMLDatabase.storeXML("/db/sample/library", "6.xml", "proj.xml.gradj.zahtev", zahtev);
		return null;
	}

	@Override
	public Zahtev testRetrive() {
		return (Zahtev) XMLDatabase.retriveXML("/db/sample/library", "6.xml", "proj.xml.gradj.zahtev");
	}
}