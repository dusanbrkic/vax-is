package com.sluzbenik.rest.controller;

import com.sluzbenik.rest.service.ZahtevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import proj.xml.gradj.zahtev.Zahtev;

import java.util.List;

@RequestMapping("/api/zahtev-sertifikata")
@RestController
// 1. одговори на захтев за издавање дигиталног зеленог сертификат
// ако службеник одбије захтев за издавање сертификата, неопходно је да
// наведе разлог зашто се захтев одбија и систем обавештава грађанина путем
// електронске поште да му је захтев одбијен при чему је у садржај те поруке
// укључен и разлог који је навео службеник;
// ако службеник прихвати захтев за издавање сертификата, сертификат се
// издаје грађанину (постаје доступан путем апликације), а такође систем
// аутоматски шаље електронску пошту грађанину да је захтев одобрен и у
// електронско писмо укључује сертификат у виду докумената у XHTML и PDF
// формату (документи у виду attachment-а или линка за преузимање
// докумената).
public class ZahtevController {
    @Autowired
    private ZahtevService zahtevSertifikataService;

    @PatchMapping(value = "/odbij/{jmbg}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public void odbijZahtevJmbg(@PathVariable String jmbg){
        zahtevSertifikataService.odbijZahtevJmbg(jmbg);
    }

    @PatchMapping(value = "/prihvati/{jmbg}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public void prihvatiZahtevJmbg(@PathVariable String jmbg){
        zahtevSertifikataService.prihvatiZahtevJmbg(jmbg);
    }

    @PatchMapping(value = "/odbij/{brPasosa}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public void odbijZahtevBrPasosa(@PathVariable String brPasosa){
        zahtevSertifikataService.odbijZahtevBrPasosa(brPasosa);
    }

    @PatchMapping(value = "/prihvati/{brPasosa}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public void prihvatiZahtevBrPasosa(@PathVariable String brPasosa){
        zahtevSertifikataService.prihvatiZahtevBrPasosa(brPasosa);
    }

    @GetMapping(value = "/{jmbg}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public Zahtev getZahtevJmbg(@PathVariable String jmbg){
        return zahtevSertifikataService.getZahtevJmbg(jmbg);
    }
    @GetMapping(value = "/{brPasosa}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public Zahtev getZahtevBrPasosa(@PathVariable String brPasosa){
        return zahtevSertifikataService.getZahtevBrPasosa(brPasosa);
    }
    @GetMapping(value = "/all", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public List<Zahtev> getAllZahtevi(){
        return zahtevSertifikataService.getAllZahtevi();
    }
}
