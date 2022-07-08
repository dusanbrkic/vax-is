package com.vax.rest.controller;

import com.vax.rest.service.DSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proj.xml.gradj.digitalni_sertifikat.DigitalniSertifikat;
import proj.xml.gradj.obrazac.Obrazac;

@RestController
@RequestMapping("api/sertifikat")
public class SertifikatController {
    // br pasosa i jmbg
    @Autowired
    private DSService sertifikatService;

    @GetMapping(value = "/jmbg/{jmbg}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public DigitalniSertifikat getSertifikatByJmbg(@PathVariable String jmbg) {
        return sertifikatService.getSertifikatByJmbg(jmbg);
    }

    @GetMapping(value = "/brojPasosa/{brojPasosa}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public DigitalniSertifikat getSertifikatByBrojPasosa(@PathVariable String brojPasosa) {
        return sertifikatService.getSertifikatByBrojPasosa(brojPasosa);
    }
}
