package com.sluzbenik.rest.service;

import proj.xml.gradj.obrazac.Obrazac;

public interface ObrazacService {
    public Obrazac getObrazacByJmbg(String jmbg);

    public Obrazac getAllObrasci();
}
