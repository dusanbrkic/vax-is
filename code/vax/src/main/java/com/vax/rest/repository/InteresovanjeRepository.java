package com.vax.rest.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.vax.rest.util.XMLDatabase;

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
		String xPathIzraz = String.format("//Interesovanje[JMBG = '%s']", jmbg);
		try {
			return (Interesovanje) XMLDatabase.izvrsiXPathIzraz(collectionId, xPathIzraz, schemaName);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}
