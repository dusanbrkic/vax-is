package com.vax.rest.service;

import java.util.List;

import proj.xml.gradj.tipovi.Gradjanin;

public interface GradjaninService {
	
	public Gradjanin getByJMBG(String jmbg);
	
	public List<Gradjanin> getAll();
	
	public boolean register(Gradjanin g);
	
	public Gradjanin login(String email, String password);

}
