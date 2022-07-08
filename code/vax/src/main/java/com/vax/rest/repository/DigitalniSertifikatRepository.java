package com.vax.rest.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.Collection;

import com.vax.rest.util.XMLDatabase;
import com.vax.rest.util.XMLParser;

import proj.xml.gradj.digitalni_sertifikat.DigitalniSertifikat;

@Repository
public class DigitalniSertifikatRepository {
//	@Autowired
//	private XMLDatabase xmlDatabase;
//	
//	@Autowired
//	private XMLParser xmlParser;
	
	private static String collectionId = "/db/sample/library/ds";
	private static String contextPath = "proj.xml.gradj.digitalni_sertifikat";
	
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

}