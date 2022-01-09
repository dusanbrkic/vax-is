package com.vax.rest.jaxb;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "customer")
public class Customer {
	
	private long id;
	
	private String name;
	
	// Obavezno imati prazan konstruktor
	public Customer() {
		
	}
	
	public Customer(long id, String name) {
		this.id = id;
		this.name = name;		
	}
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
