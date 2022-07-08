package com.vax.rest.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.vax.rest.util.XMLDatabase;

import proj.xml.gradj.potvrda.Potvrda;

@Repository
public class PotvrdaRepository {
	private static String collectionId = "/db/sample/library/potvrda";
	private static String contextPath = "proj.xml.gradj.potvrda";
	
	public void store(Potvrda p) {
		XMLDatabase.storeXML(collectionId, p.getJMBG().getValue()+".xml", contextPath, p);
	}
	
	public Potvrda retrieveByJMBG(String jmbg) {
		return (Potvrda) XMLDatabase.retriveXML(collectionId, jmbg+".xml", contextPath);
	}
	
	public List<Potvrda> getAll() {
		List<Object> res =  XMLDatabase.retriveAllXML(collectionId, contextPath);
		List<Potvrda> result = new ArrayList<Potvrda>();
		for (Object object : res) {
			result.add((Potvrda) object);
		}
		return result;
	}
}
