package com.sluzbenik.rest.service.impl;

import com.sluzbenik.rest.service.PotvrdaService;
import org.springframework.stereotype.Service;
import proj.xml.gradj.potvrda.Potvrda;

@Service
public class PotvrdaServiceImpl implements PotvrdaService {
    @Override
    public Potvrda getPotvrdaByJmbg(String jmbg) {
        return null;
    }

    @Override
    public Potvrda[] getAllPotvrde() {
        return null;
    }
}
