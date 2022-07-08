package com.sluzbenik.rest.controller;

import com.sluzbenik.rest.service.VakcinarnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proj.xml.sluzb.vakcinarnik.Vakcinarnik;

@RequestMapping("api/vakcinarnik")
@RestController
// службеник може да види за сваки тип вакцине колика је доступна количина;
// сваки пут када се нека вакцина да грађанину, смањи се доступна количина те
// вакцине у систему;
// када дође нова количина вакцина од произвођача, службеник ажурира
// количину вакцина.
public class VakcinarnikController {

    @Autowired
    private VakcinarnikService vakcinarnikService;


    @GetMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public Vakcinarnik getVakcinarnik(){
        return vakcinarnikService.getVakcinarnik();
    }

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public void setVakcinarnik(Vakcinarnik vakcinarnik){
        vakcinarnikService.setVakcinarnik(vakcinarnik);
    }
}
