package com.sluzbenik.rest.service;

import proj.xml.gradj.zahtev.Zahtev;

import java.util.List;

public interface ZahtevService {
    public void odbijZahtevJmbg(String jmbg);
    public void prihvatiZahtevJmbg(String jmbg);

    public void odbijZahtevBrPasosa(String brPasosa);

    public void prihvatiZahtevBrPasosa(String brPasosa);

    public Zahtev getZahtevBrPasosa(String brPasosa);

    public Zahtev getZahtevJmbg(String jmbg);

    public List<Zahtev> getAllZahtevi();
}
