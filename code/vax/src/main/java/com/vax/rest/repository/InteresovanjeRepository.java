package com.vax.rest.repository;

import java.util.ArrayList;
import java.util.List;

import org.exist.xmldb.EXistResource;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import com.vax.rest.util.XMLDatabase;
import com.vax.rest.util.XMLParser;

import proj.xml.gradj.digitalni_sertifikat.DigitalniSertifikat;
import proj.xml.gradj.interesovanje.Interesovanje;

@Repository
public class InteresovanjeRepository {
	private static String collectionId = "/db/sample/library/interesovanje";
	private static String contextPath = "proj.xml.gradj.interesovanje";

	private static String schemaName = "http://www.xml.proj/gradj/interesovanje";
	
	public void store(Interesovanje i) {
		XMLDatabase.storeXML(collectionId, i.getID().toString()+".xml", contextPath, i);
	}
	
	public Interesovanje retrieveById(Integer id) {
		Interesovanje ds=(Interesovanje) XMLDatabase.retriveXML(collectionId, Integer.toString(id)+".xml", contextPath);
		return ds;
	}
	
	public List<Interesovanje> getAll() {
		List<Object> res =  XMLDatabase.retriveAllXML(collectionId, contextPath);
		List<Interesovanje> result = new ArrayList<Interesovanje>();
		for (Object object : res) {
			result.add((Interesovanje) object);
		}
		return result;
	}

    public Interesovanje retrieveByJmbg(String jmbg) {
    	Interesovanje interesovanje = null;
		String xPathIzraz = String.format("//Interesovanje[JMBG = '%s']", jmbg);
		try {
			ResourceSet rs= XMLDatabase.izvrsiXPathIzraz(collectionId, xPathIzraz, schemaName);
			
			if (rs == null)
	            return null;

	        ResourceIterator i = rs.getIterator();
	        XMLResource res = null;

	        if (i.hasMoreResources()) {
	            res = (XMLResource) i.nextResource();
	        }

	        if (res != null) {
	            try {
	            	interesovanje = (Interesovanje) XMLParser.unmarshal(contextPath, "","", false,true,res);
	            	
	                ((EXistResource) res).freeResources();
	            } catch (XMLDBException exception) {
	                exception.printStackTrace();
	            }
	        }
	        
	        
			return interesovanje;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}
