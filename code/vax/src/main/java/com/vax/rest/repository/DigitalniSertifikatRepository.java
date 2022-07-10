package com.vax.rest.repository;

import java.util.ArrayList;
import java.util.List;

import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import com.vax.rest.util.XMLDatabase;
import com.vax.rest.util.XMLParser;

import proj.xml.gradj.digitalni_sertifikat.DigitalniSertifikat;
import proj.xml.gradj.interesovanje.Interesovanje;
import proj.xml.gradj.obrazac.Obrazac;

@Repository
public class DigitalniSertifikatRepository {
//	@Autowired
//	private XMLDatabase xmlDatabase;
//	
//	@Autowired
//	private XMLParser xmlParser;
	
	private static String collectionId = "/db/sample/library/ds";
	private static String contextPath = "proj.xml.gradj.digitalni_sertifikat";

	private static String schemaName = "http://www.xml.proj/gradj/digitalni_sertifikat";
	
	public void store(DigitalniSertifikat ds) {
		XMLDatabase.storeXML(collectionId, ds.getOsnovniPodaci().getBrojSertifikata().toString()+".xml", contextPath, ds);
	}
	
	public DigitalniSertifikat retrieveByBrojSertifikata(Integer brojSertifikata) {
		DigitalniSertifikat ds=(DigitalniSertifikat) XMLDatabase.retriveXML(collectionId, Integer.toString(brojSertifikata)+".xml", contextPath);
		return ds;
	}
	
	public List<DigitalniSertifikat> getAll() {
		List<Object> res =  XMLDatabase.retriveAllXML(collectionId, contextPath);
		List<DigitalniSertifikat> result = new ArrayList<DigitalniSertifikat>();
		for (Object object : res) {
			result.add((DigitalniSertifikat) object);
		}
		return result;
	}

	public DigitalniSertifikat retrieveByJmbg(String jmbg) {
		DigitalniSertifikat digitalniSertifikat = null;
		String xPathIzraz = String.format("//Digitalni_sertifikat/Podaci_o_gradjaninu[JMBG = '%s']", jmbg);
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
	            	digitalniSertifikat = (DigitalniSertifikat) XMLParser.unmarshal(contextPath, "","", false,true,res);
	            	
	                ((EXistResource) res).freeResources();
	            } catch (XMLDBException exception) {
	                exception.printStackTrace();
	            }
	        }
	        
	        return digitalniSertifikat;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public DigitalniSertifikat retrieveByBrojPasosa(String brojPasosa) {
		DigitalniSertifikat digitalniSertifikat = null;
		String xPathIzraz = String.format("//Digitalni_sertifikat/Podaci_o_gradjaninu[Broj_pasosa = '%s']", brojPasosa);
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
	            	digitalniSertifikat = (DigitalniSertifikat) XMLParser.unmarshal(contextPath, "","", false,true,res);
	            	
	                ((EXistResource) res).freeResources();
	            } catch (XMLDBException exception) {
	                exception.printStackTrace();
	            }
	        }
	        
	        return digitalniSertifikat;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
