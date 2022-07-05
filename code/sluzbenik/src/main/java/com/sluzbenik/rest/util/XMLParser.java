package com.sluzbenik.rest.util;

import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.OutputStream;

public class XMLParser {
	
	public static Object unmarshal(String contextPath,String schemaName,String fileName,boolean validate,boolean fromDB,XMLResource res) {
		JAXBContext context;
		Unmarshaller unmarshaller;
		try {
			context = JAXBContext.newInstance(contextPath);
			unmarshaller = context.createUnmarshaller();
			
			if(validate) {
				SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
				Schema schema = schemaFactory.newSchema(new File("./src/main/resources/xsd/"+schemaName));
				unmarshaller.setSchema(schema);
		        unmarshaller.setEventHandler(new SchemaValidationEventHandler());
			}
			
			if(fromDB) {
				return unmarshaller.unmarshal(res.getContentAsDOM());
			}
			return unmarshaller.unmarshal(new File("./src/main/resources/xml/"+fileName));
		} catch (JAXBException | SAXException | XMLDBException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public static void marshal(String contextPath,String schemaName,Object o,OutputStream destination,boolean validate) {
		JAXBContext context;
		Marshaller marshaller;
		try {
			context = JAXBContext.newInstance(contextPath);
			marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			
			if(validate) {
				SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
				Schema schema = schemaFactory.newSchema(new File("./src/main/resources/xsd/"+schemaName));
				marshaller.setSchema(schema);
	            marshaller.setEventHandler(new SchemaValidationEventHandler());
			}
            
            marshaller.marshal(o, destination);
			
		} catch (JAXBException | SAXException e) {
			e.printStackTrace();
		}
		
	}

}
