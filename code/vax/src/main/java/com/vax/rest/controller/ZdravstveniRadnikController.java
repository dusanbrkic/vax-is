package com.vax.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vax.rest.service.impl.ZdravstveniRadnikServiceImpl;

import proj.xml.gradj.tipovi.ZdravstveniRadnik;

@RestController
@RequestMapping("/api/zdravstveni-radnici")
public class ZdravstveniRadnikController {
	@Autowired
	private ZdravstveniRadnikServiceImpl zdravstveniRadnikService;
	
	@GetMapping(value = "/jmbg/{jmbg}", produces = MediaType.APPLICATION_XML_VALUE)
    public ZdravstveniRadnik getByJmbg(@PathVariable String jmbg) {
        return zdravstveniRadnikService.getByJMBG(jmbg);
    }
	
	@GetMapping(value = "", produces = MediaType.APPLICATION_XML_VALUE)
    public List<ZdravstveniRadnik> getAll() {
        return zdravstveniRadnikService.getAll();
    }

}
