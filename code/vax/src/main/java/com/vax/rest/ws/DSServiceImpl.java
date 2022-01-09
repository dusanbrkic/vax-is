package com.vax.rest.ws;

import java.io.File;

import javax.ws.rs.Path;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Service;

import com.vax.rest.inter.DSService;

import proj.xml.gradj.digitalni_sertifikat.DigitalniSertifikat;

@Service
@Path("/ds")
public class DSServiceImpl implements DSService {

	@Override
	public DigitalniSertifikat testUnmarshal() {
		System.out.println("Testing unmarshal");
		
		try {
			JAXBContext context = JAXBContext.newInstance("proj.xml.gradj.digitalni_sertifikat");
			
			Unmarshaller unmarshaller = context.createUnmarshaller();
			
			DigitalniSertifikat ds = (DigitalniSertifikat) unmarshaller.unmarshal(new File("./src/main/resources/xml/digitalni_sertifikat_primer.xml"));
			
			System.out.println(ds);
			
			return ds;
		} catch (JAXBException e) {
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
			
			DigitalniSertifikat ds=new DigitalniSertifikat();
			
			marshaller.marshal(ds, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

}
