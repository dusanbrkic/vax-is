package com.sluzbenik.rest.repository;

import com.sluzbenik.rest.util.XMLDatabase;
import org.springframework.stereotype.Repository;
import proj.xml.sluzb.vakcinarnik.Vakcinarnik;

@Repository
public class VakcinarnikRepository {
    private static String collectionId = "/db/sample/library/vakcinarnik";
    private static String contextPath = "proj.xml.sluzb.vakcinarnik";
    private static String documentId = "vakcinarnik.xml";

    public void store(Vakcinarnik v) {
        XMLDatabase.storeXML(collectionId, documentId, contextPath, v);
    }

    public Vakcinarnik retrieve() {
        return (Vakcinarnik) XMLDatabase.retriveXML(collectionId, documentId, contextPath);
    }
}
