package com.vax.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.vax.rest.repository.ZdravstveniRadnikRepository;
import com.vax.rest.service.ZdravstveniRadnikService;

import proj.xml.gradj.tipovi.ZdravstveniRadnik;

@Service
public class ZdravstveniRadnikServiceImpl implements ZdravstveniRadnikService{
	@Autowired
	private ZdravstveniRadnikRepository zdravstveniRadnikRepository;
	
	@Override
	public ZdravstveniRadnik getByJMBG(String jmbg) {
		return zdravstveniRadnikRepository.retrieveByJmbg(jmbg);
	}
	
	@Override
	@JacksonXmlElementWrapper(useWrapping = false)
	public List<ZdravstveniRadnik> getAll(){
		return zdravstveniRadnikRepository.getAll();
	}
}
