package com.sluzbenik.rest.service.impl;

import com.sluzbenik.rest.service.SertifikatService;
import org.springframework.stereotype.Service;
import proj.xml.gradj.digitalni_sertifikat.DigitalniSertifikat;

@Service
public class SertifikatServiceImpl implements SertifikatService {
    @Override
    public DigitalniSertifikat getSertifikatByJmbg(String jmbg) {
        return null;
    }

    @Override
    public DigitalniSertifikat[] getAllSertifikati() {
        return new DigitalniSertifikat[0];
    }
}
