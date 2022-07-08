package com.sluzbenik.rest.service.impl;

import com.sluzbenik.rest.service.ZahtevSertifikataService;
import org.springframework.stereotype.Service;

@Service
public class ZahtevSertifikataServiceImpl implements ZahtevSertifikataService {

    @Override
    public void odbijZahtev(String jmbg) {
        //TODO Mail da je odbijen
    }

    @Override
    public void prihvatiZahtev(String jmbg) {
        //TODO Mail da je prihvacen plus snapshot sertifikata
    }
}
