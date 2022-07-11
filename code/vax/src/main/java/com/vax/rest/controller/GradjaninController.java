package com.vax.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vax.rest.service.GradjaninService;

import proj.xml.gradj.tipovi.Gradjanin;

@RestController
@RequestMapping("/api/gradjani")
public class GradjaninController {
	@Autowired
	private GradjaninService gradjaninService;
	
	@GetMapping(value = "/jmbg/{jmbg}", produces = MediaType.APPLICATION_XML_VALUE)
    public Gradjanin getGradjaninByJmbg(@PathVariable String jmbg) {
        return gradjaninService.getByJMBG(jmbg);
    }
	
	@GetMapping(value = "", produces = MediaType.APPLICATION_XML_VALUE)
    public List<Gradjanin> getAll() {
        return gradjaninService.getAll();
    }

}
