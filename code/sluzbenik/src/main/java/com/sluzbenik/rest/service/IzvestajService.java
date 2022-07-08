package com.sluzbenik.rest.service;

import org.xml.sax.SAXException;
import proj.xml.sluzb.izvestaj.Izvestaj;

import java.io.IOException;
import java.time.LocalDateTime;

public interface IzvestajService {
	public Izvestaj testUnmarshal();

	public Izvestaj testMarshal();

	public Izvestaj testStore();

	public Izvestaj testRetrieve();

	public void testRDF() throws SAXException, IOException;

    public Izvestaj generisiIzvestaj(LocalDateTime fromTimestamp, LocalDateTime toTimestamp);
}
