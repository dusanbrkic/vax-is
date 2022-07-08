package com.sluzbenik.rest.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sluzbenik.rest.util.XMLDatabase;

import proj.xml.sluzb.izvestaj.Izvestaj;

@Repository
public class IzvestajRepository {
	private static String collectionId = "/db/sample/library/izvestaj";
	private static String contextPath = "proj.xml.sluzb.izvestaj";
	
	public void store(Izvestaj i) {
		XMLDatabase.storeXML(collectionId, i.getID().toString()+".xml", contextPath, i);
	}
	
	public Izvestaj retrieveById(Integer id) {
		Izvestaj ds=(Izvestaj) XMLDatabase.retriveXML(collectionId, Integer.toString(id)+".xml", contextPath);
		return ds;
	}
	
	public List<Izvestaj> getAll() {
		List<Object> res =  XMLDatabase.retriveAllXML(collectionId, contextPath);
		List<Izvestaj> result = new ArrayList<Izvestaj>();
		for (Object object : res) {
			result.add((Izvestaj) object);
		}
		return result;
	}

}
