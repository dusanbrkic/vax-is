package com.sluzbenik.rest.service;

import proj.xml.gradj.digitalni_sertifikat.DigitalniSertifikat;

public interface SertifikatService {
    public DigitalniSertifikat getSertifikatByJmbg(String jmbg);

    public DigitalniSertifikat[] getAllSertifikati();
}
