package com.sluzbenik.rest.service.impl;

import com.sluzbenik.rest.service.ZahtevService;
import org.springframework.stereotype.Service;

@Service
public class ZahtevServiceImpl implements ZahtevService {

    @Override
    public void odbijZahtev(String jmbg) {
        //TODO Mail da je odbijen
    }

    @Override
    public void prihvatiZahtev(String jmbg) {
        //TODO Mail da je prihvacen plus snapshot sertifikata
    }
}
