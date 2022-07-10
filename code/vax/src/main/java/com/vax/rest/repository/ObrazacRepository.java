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
import proj.xml.gradj.obrazac.Obrazac;

@Repository
public class ObrazacRepository {
	private static String collectionId = "/db/sample/library/obrazac";
	private static String contextPath = "proj.xml.gradj.obrazac";
	private static String schemaName = "http://www.xml.proj/gradj/obrazac";

	public void store(Obrazac o) {
		XMLDatabase.storeXML(collectionId, o.getID().toString()+".xml", contextPath, o);
	}
	
	public Obrazac retrieveById(Integer id) {
		Obrazac ds=(Obrazac) XMLDatabase.retriveXML(collectionId, Integer.toString(id)+".xml", contextPath);
		return ds;
	}
	
	public List<Obrazac> getAll() {
		List<Object> res =  XMLDatabase.retriveAllXML(collectionId, contextPath);
		List<Obrazac> result = new ArrayList<Obrazac>();
		for (Object object : res) {
			result.add((Obrazac) object);
		}
		return result;
	}

	public Obrazac getObrazacByJmbg(String jmbg) {
		Obrazac obrazac = null;
		String xPathIzraz = String.format("//Drzavljanstvo/Srpski_drzavljanin[JMBG = '%s']", jmbg);
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
	            	obrazac = (Obrazac) XMLParser.unmarshal(contextPath, "","", false,true,res);
	            	
	                ((EXistResource) res).freeResources();
	            } catch (XMLDBException exception) {
	                exception.printStackTrace();
	            }
	        }
	        
	        return obrazac;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Obrazac getObrazacByBrPasosa(String brPasosa) {
		Obrazac obrazac = null;
		String xPathIzraz = String.format("//Drzavljanstvo/Strani_drzavljanin[Broj_pasosa = '%s']", brPasosa);
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
	            	obrazac = (Obrazac) XMLParser.unmarshal(contextPath, "","", false,true,res);
	            	
	                ((EXistResource) res).freeResources();
	            } catch (XMLDBException exception) {
	                exception.printStackTrace();
	            }
	        }
	        
	        return obrazac;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
