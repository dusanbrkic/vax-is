package com.vax.rest.ws;

import com.vax.rest.inter.PotvrdaService;
import com.vax.rest.util.XMLDatabase;
import com.vax.rest.util.XMLParser;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import proj.xml.gradj.potvrda.Potvrda;

import javax.ws.rs.Path;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
@Path("/potvrda")
public class PotvrdaServiceImpl implements PotvrdaService {

	@Override
	public Potvrda testUnmarshal() throws SAXException, IOException {
		System.out.println("Testing unmarshal");

		Potvrda potvrda = (Potvrda) XMLParser.unmarshal("proj.xml.gradj.potvrda", "potvrda.xsd", "potvrda_primer.xml",true,false,null);
		System.out.println(potvrda);

		/*

		String xmlFilePath = "./src/main/resources/xml/potvrda_primer.xml";

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
		return potvrda;
	}

	@Override
	public Potvrda testMarshal() {

		Potvrda potvrda= new Potvrda();
		Date d=new Date();
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(d);
		try {
			potvrda.setDatum(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}

		XMLParser.marshal("proj.xml.gradj.potvrda", "potvrda.xsd",potvrda,System.out,false);
		return null;
	}

	@Override
	public Potvrda testStore() {
		Potvrda potvrda = (Potvrda) XMLParser.unmarshal("proj.xml.gradj.potvrda", "potvrda.xsd", "potvrda_primer.xml",true,false,null);
		XMLDatabase.storeXML("/db/sample/library", "5.xml", "proj.xml.gradj.potvrda", potvrda);
		return null;
	}

	@Override
	public Potvrda testRetrive() {
		return (Potvrda) XMLDatabase.retriveXML("/db/sample/library", "5.xml", "proj.xml.gradj.potvrda");
	}
}
