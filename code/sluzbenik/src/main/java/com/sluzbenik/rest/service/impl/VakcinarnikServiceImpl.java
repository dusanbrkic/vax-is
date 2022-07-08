package com.sluzbenik.rest.service.impl;

import com.sluzbenik.rest.repository.VakcinarnikRepository;
import com.sluzbenik.rest.service.VakcinarnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proj.xml.sluzb.vakcinarnik.Vakcinarnik;

@Service
public class VakcinarnikServiceImpl implements VakcinarnikService {
    @Autowired
    private VakcinarnikRepository vakcinarnikRepository;

    @Override
    public Vakcinarnik getVakcinarnik() {
        return vakcinarnikRepository.retrieve();
    }

    @Override
    public void setVakcinarnik(Vakcinarnik vakcinarnik) {
        vakcinarnikRepository.store(vakcinarnik);
    }
}
