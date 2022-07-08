package com.vax.rest.controller;

import com.vax.rest.service.PotvrdaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proj.xml.gradj.potvrda.Potvrda;

@RestController
@RequestMapping("api/potvrda")
public class PotvrdaController {
    @Autowired
    private PotvrdaService potvrdaService;

    // samo jmbg
    @GetMapping(value = "/jmbg/{jmbg}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public Potvrda getPotvrdaByJmbg(@PathVariable String jmbg) {
        return potvrdaService.getPotvrdaByJmbg(jmbg);
    }
}
