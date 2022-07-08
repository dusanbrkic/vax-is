package com.sluzbenik.rest.controller;

import com.sluzbenik.rest.service.ObrazacService;
import com.sluzbenik.rest.service.PotvrdaService;
import com.sluzbenik.rest.service.SertifikatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proj.xml.gradj.digitalni_sertifikat.DigitalniSertifikat;
import proj.xml.gradj.obrazac.Obrazac;
import proj.xml.gradj.potvrda.Potvrda;

@RequestMapping("api/dokument")
@RestController
//4. претражује архиву докумената која припада грађанину (сагласност, потврда,
//        сертификат)
//        ● претрагу докумената по текстуалном садржају (тј. основну претрагу)
//        имплементирати кроз засебну форму на којој је корисницима омогућен унос
//        фраза или кључних речи по којима се врши претрага над свим елементима
//        документа који садрже дате појмове;
//        ● имплементирати претрагу докумената по метаподацима (тј. напредна
//        претрага);
//        ● претрагу докумената по више метаподатака реализовати употребом логичких
//        оператора;
//        ● приликом прегледања докумената омогућити линк на референциране
//        документе, а у форми за претрагу омогућити проналажење свих докумената
//        који референцирају дати документ;
//        ● приликом моделовања документа као XML документа, посебно обратити
//        пажњу на поља која представљају метаподатке и обратити пажњу на могуће
//        референце на друге документе, а на основу којих ће бити имплементирана
//        напредна претрага;
//        ● сваки документ који се пронађе може да се извезе у XHTML и PDF форматима.
public class DokumentController {
    @Autowired
    private SertifikatService sertifikatService;
    @Autowired
    private PotvrdaService potvrdaService;
    @Autowired
    private ObrazacService obrazacService;

    @GetMapping(value = "/sertifikat/{jmbg}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public DigitalniSertifikat getSertifikatByJmbg(@PathVariable String jmbg) {
        return sertifikatService.getSertifikatByJmbg(jmbg);
    }
    @GetMapping(value = "/potvrda/{jmbg}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public Potvrda getPotvrdaByJmbg(@PathVariable String jmbg) {
        return potvrdaService.getPotvrdaByJmbg(jmbg);
    }
    @GetMapping(value = "/obrazac/{jmbg}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public Obrazac getObrazacByJmbg(@PathVariable String jmbg) {
        return obrazacService.getObrazacByJmbg(jmbg);
    }

    @GetMapping(value = "/sertifikat/all", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public DigitalniSertifikat[] getAllSertifikati() {
        return sertifikatService.getAllSertifikati();
    }
    @GetMapping(value = "/potvrda/all", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public Potvrda[] getAllPotvrde() {
        return potvrdaService.getAllPotvrde();
    }
    @GetMapping(value = "/obrazac/all", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public Obrazac[] getAllObrasci() {
        return obrazacService.getAllObrasci();
    }
}
