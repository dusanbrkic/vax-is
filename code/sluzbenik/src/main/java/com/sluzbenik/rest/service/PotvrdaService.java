package com.sluzbenik.rest.service;

import proj.xml.gradj.potvrda.Potvrda;

public interface PotvrdaService {
    public Potvrda getPotvrdaByJmbg(String jmbg);

    public Potvrda getAllPotvrde();
}
