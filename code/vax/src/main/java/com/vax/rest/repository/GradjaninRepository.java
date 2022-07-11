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

import proj.xml.gradj.tipovi.Gradjanin;

@Repository
public class GradjaninRepository {

	private static String collectionId = "/db/sample/library/gradjanin";
	private static String contextPath = "proj.xml.gradj.tipovi";

	private static String schemaName = "http://www.xml.proj/gradj/tipovi";
	
	public void store(Gradjanin g) {
		XMLDatabase.storeXML(collectionId, g.getJMBG()+".xml", contextPath, g);
	}
	
	
	public List<Gradjanin> getAll() {
		List<Object> res =  XMLDatabase.retriveAllXML(collectionId, contextPath);
		List<Gradjanin> result = new ArrayList<Gradjanin>();
		for (Object object : res) {
			result.add((Gradjanin) object);
		}
		return result;
	}

	public Gradjanin retrieveByJmbg(String jmbg) {
		Gradjanin gradjanin = null;
		String xPathIzraz = String.format("//Gradjanin[JMBG = '%s']", jmbg);
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
	            	gradjanin = (Gradjanin) XMLParser.unmarshal(contextPath, "","", false,true,res);
	            	
	                ((EXistResource) res).freeResources();
	            } catch (XMLDBException exception) {
	                exception.printStackTrace();
	            }
	        }
	        
	        return gradjanin;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Gradjanin retrieveByEmail(String email) {
		Gradjanin gradjanin = null;
		String xPathIzraz = String.format("//Gradjanin[Email = '%s']", email);
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
	            	gradjanin = (Gradjanin) XMLParser.unmarshal(contextPath, "","", false,true,res);
	            	
	                ((EXistResource) res).freeResources();
	            } catch (XMLDBException exception) {
	                exception.printStackTrace();
	            }
	        }
	        
	        return gradjanin;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
