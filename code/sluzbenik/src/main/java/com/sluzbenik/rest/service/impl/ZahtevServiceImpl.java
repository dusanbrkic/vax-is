package com.sluzbenik.rest.service.impl;

import com.sluzbenik.rest.service.ZahtevService;
import org.springframework.stereotype.Service;

@Service
public class ZahtevServiceImpl implements ZahtevService {
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
    public void getZahtevBrPasosa(String brPasosa) {

    }

    @Override
    public void getZahtevJmbg(String jmbg) {

    }

    @Override
    public void getAllZahtevi() {

    }
}
