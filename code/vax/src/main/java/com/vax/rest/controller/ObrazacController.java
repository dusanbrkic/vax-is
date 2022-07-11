package com.vax.rest.controller;

import com.vax.rest.service.ObrazacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proj.xml.gradj.obrazac.Obrazac;

import java.util.List;

@RestController
@RequestMapping("/api/obrazac")
public class ObrazacController {
    @Autowired
    private ObrazacService obrazacService;

    @GetMapping(value = "/jmbg/{jmbg}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public Obrazac getObrazacByJmbg(@PathVariable String jmbg) {
        return obrazacService.getObrazacByJmbg(jmbg);
    }

    @GetMapping(value = "/brPasosa/{brPasosa}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public Obrazac getObrazacByBrPasosa(@PathVariable String brPasosa) {
        return obrazacService.getObrazacByBrPasosa(brPasosa);
    }

    @GetMapping()
    public ResponseEntity<List<Obrazac>> getAllObrasci() {
    	System.out.println("hrkfhskghdkgh");
        return new ResponseEntity<List<Obrazac>> (obrazacService.getAllObrasci(), HttpStatus.OK);
    }
    
    @GetMapping("/test")
    public ResponseEntity<String> test() {
    	System.out.println("hrkfhskghdkgh");
        return new ResponseEntity<String> ("test prosao", HttpStatus.OK);
    }

}
