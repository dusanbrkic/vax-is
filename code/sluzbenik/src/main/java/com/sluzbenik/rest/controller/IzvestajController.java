package com.sluzbenik.rest.controller;

import com.sluzbenik.rest.dto.Daterange;
import com.sluzbenik.rest.service.IzvestajService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import proj.xml.sluzb.izvestaj.Izvestaj;

import java.time.LocalDateTime;

@RequestMapping("api/izvestaj")
@RestController
// Sluzbenik izdaje izvestaj o imunizaciji
// поменути извештај за изабрани временски интервал садржи податке о броју
// примљених докумената о интересовању, броју издатих дигиталних зелених
// сертификата, броју датих вакцина, броју датих вакцина сваког произвођача,
// броју примљених нових вакцина итд.
public class IzvestajController {

    @Autowired
    private IzvestajService izvestajService;

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public Izvestaj generisiIzvestaj(@RequestBody Daterange dr){
        return izvestajService.generisiIzvestaj(dr.fromTimestamp(), dr.toTimestamp());
    }
}
