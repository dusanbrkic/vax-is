package com.vax.rest.service;

import java.util.List;

import proj.xml.gradj.tipovi.Gradjanin;

public interface GradjaninService {
	
	public Gradjanin getByJMBG(String jmbg);
	
	public List<Gradjanin> getAll();

}
