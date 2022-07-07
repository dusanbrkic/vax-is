package com.vax.rest.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.vax.rest.util.XMLDatabase;

import proj.xml.gradj.obrazac.Obrazac;

@Repository
public class ObrazacRepository {
	private static String collectionId = "/db/sample/library/obrazac";
	private static String contextPath = "proj.xml.gradj.obrazac";
	
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
}
