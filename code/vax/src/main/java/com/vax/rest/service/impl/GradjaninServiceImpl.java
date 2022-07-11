package com.vax.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.vax.rest.repository.GradjaninRepository;
import com.vax.rest.service.GradjaninService;

import proj.xml.gradj.tipovi.Gradjanin;

@Service
public class GradjaninServiceImpl implements GradjaninService {
	@Autowired
	private GradjaninRepository gradjaninRepository;
	
	@Override
	public Gradjanin getByJMBG(String jmbg) {
		return gradjaninRepository.retrieveByJmbg(jmbg);
	}
	
	@Override
	@JacksonXmlElementWrapper(useWrapping = false)
	public List<Gradjanin> getAll(){
		return gradjaninRepository.getAll();
	}
}
