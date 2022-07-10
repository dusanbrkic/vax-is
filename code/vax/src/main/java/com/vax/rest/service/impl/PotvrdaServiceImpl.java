package com.vax.rest.service.impl;

import com.vax.rest.repository.PotvrdaRepository;
import com.vax.rest.service.PotvrdaService;
import com.vax.rest.util.RDFUtil;
import com.vax.rest.util.XMLDatabase;
import com.vax.rest.util.XMLParser;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PotvrdaServiceImpl implements PotvrdaService {
	@Autowired
	private PotvrdaRepository potvrdaRepository;

	@Override
	public Potvrda testUnmarshal() {
		System.out.println("Testing unmarshal");

		Potvrda potvrda = (Potvrda) XMLParser.unmarshal("proj.xml.gradj.potvrda", "potvrda.xsd", "potvrda_primer.xml",true,false,null);
		System.out.println(potvrda);


		return potvrda;
	}

	@Override
	public Potvrda testMarshal() {

		Potvrda potvrda= new Potvrda();
		Date d=new Date();
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(d);
		Potvrda.Datum pd=new Potvrda.Datum();
		try {
			pd.setValue(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
			potvrda.setDatum(pd);
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

	@Override
	public void testRDF() throws SAXException, IOException {
		String xmlFilePath = "./src/main/resources/xml/potvrda_primer.xml";

		String rdfFilePath = "./src/main/resources/rdf/potvrda.rdf";

		//kreira RDF fajl
		RDFUtil.generateRDFFromXML(xmlFilePath, rdfFilePath);

		//upis u bazu
		RDFUtil.updateFuseki(rdfFilePath, "potvrda");
	}

	@Override
	public Potvrda getPotvrdaByJmbg(String jmbg) {
		return potvrdaRepository.retrieveByJMBG(jmbg);
	}
}
