package com.vax.rest.controller;

import com.vax.rest.service.ZahtevService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proj.xml.gradj.obrazac.Obrazac;
import proj.xml.gradj.zahtev.Zahtev;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/zahtev")
public class ZahtevController {
    private final ZahtevService zahtevService;

    @GetMapping(value = "/all", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public List<Zahtev> getAllZahtevi() {
        return zahtevService.retrieveAll();
    }
}
