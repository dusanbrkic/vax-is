package com.sluzbenik.rest.controller;

import com.sluzbenik.rest.service.ZahtevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/zahtev-sertifikata")
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
    public void odbijZahtev(@PathVariable String jmbg){
        zahtevSertifikataService.odbijZahtev(jmbg);
    }

    @PatchMapping(value = "/prihvati{jmbg}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public void prihvatiZahtev(@PathVariable String jmbg){
        zahtevSertifikataService.prihvatiZahtev(jmbg);
    }
}
