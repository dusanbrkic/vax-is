package com.vax.rest.service;

import java.util.List;


import proj.xml.gradj.tipovi.ZdravstveniRadnik;


public interface ZdravstveniRadnikService {
	
	public ZdravstveniRadnik getByJMBG(String jmbg);
	
	
	public List<ZdravstveniRadnik> getAll();

}
