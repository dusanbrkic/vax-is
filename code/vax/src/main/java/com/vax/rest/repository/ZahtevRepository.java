package com.vax.rest.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.vax.rest.util.XMLDatabase;

import proj.xml.gradj.zahtev.Zahtev;

@Repository
public class ZahtevRepository {
	
	private static String collectionId = "/db/sample/library/zahtev";
	private static String contextPath = "proj.xml.gradj.zahtev";
	
	public void store(Zahtev z) {
		XMLDatabase.storeXML(collectionId, z.getPodnosilacZahteva().getJMBG().getValue()+".xml", contextPath, z);
	}
	
	public Zahtev retrieveByJMBG(String jmbg) {
		Zahtev ds=(Zahtev) XMLDatabase.retriveXML(collectionId, jmbg+".xml", contextPath);
		return ds;
	}
	
	public List<Zahtev> getAll() {
		List<Object> res =  XMLDatabase.retriveAllXML(collectionId, contextPath);
		List<Zahtev> result = new ArrayList<Zahtev>();
		assert res != null;
		for (Object object : res) {
			result.add((Zahtev) object);
		}
		return result;
	}
}
