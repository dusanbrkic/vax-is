package com.sluzbenik.rest.service.impl;

import com.sluzbenik.rest.service.ZahtevService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import proj.xml.gradj.zahtev.Zahtev;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ZahtevServiceImpl implements ZahtevService {
    private final RestTemplate restTemplate;
    @Override
    public void odbijZahtevJmbg(String jmbg) {
        //TODO Mail da je odbijen
    }

    @Override
    public void prihvatiZahtevJmbg(String jmbg) {
        //TODO Mail da je prihvacen plus snapshot sertifikata
    }

    @Override
    public void odbijZahtevBrPasosa(String brPasosa) {
        //TODO Mail da je odbijen
    }

    @Override
    public void prihvatiZahtevBrPasosa(String brPasosa) {
        //TODO Mail da je prihvacen plus snapshot sertifikata
    }

    @Override
    public Zahtev getZahtevBrPasosa(String brPasosa) {
        return null;
    }

    @Override
    public Zahtev getZahtevJmbg(String jmbg) {
        return null;
    }

    @Override
    public List<Zahtev> getAllZahtevi() {
        return Arrays.asList(restTemplate.getForEntity("https://localhost:8081/api/zahtev/all", Zahtev[].class).getBody());
    }
}
