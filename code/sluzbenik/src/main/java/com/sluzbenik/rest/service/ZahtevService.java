package com.sluzbenik.rest.service;

public interface ZahtevService {
    public void odbijZahtevJmbg(String jmbg);
    public void prihvatiZahtevJmbg(String jmbg);

    public void odbijZahtevBrPasosa(String brPasosa);

    public void prihvatiZahtevBrPasosa(String brPasosa);

    public void getZahtevBrPasosa(String brPasosa);

    public void getZahtevJmbg(String jmbg);

    public void getAllZahtevi();
}
