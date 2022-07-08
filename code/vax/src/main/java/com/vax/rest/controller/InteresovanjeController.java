package com.vax.rest.controller;

import com.vax.rest.service.InteresovanjeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proj.xml.gradj.interesovanje.Interesovanje;

@RestController
@RequestMapping("api/interesovanje")
public class InteresovanjeController {
    @Autowired
    private InteresovanjeService interesovanjeService;

    // samo jmbg
    @GetMapping(value = "/jmbg/{jmbg}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public Interesovanje getInteresovanjeByJmbg(@PathVariable String jmbg) {
        return interesovanjeService.getInteresovanjeByJmbg(jmbg);
    }
}
