package com.vax.rest;

import com.vax.rest.repository.GradjaninRepository;
import com.vax.rest.repository.InteresovanjeRepository;
import com.vax.rest.repository.ObrazacRepository;
import com.vax.rest.repository.PotvrdaRepository;
import com.vax.rest.repository.ZahtevRepository;
import com.vax.rest.repository.ZdravstveniRadnikRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import proj.xml.gradj.interesovanje.Interesovanje;
import proj.xml.gradj.obrazac.*;
import proj.xml.gradj.potvrda.Potvrda;
import proj.xml.gradj.potvrda.TVakcinacija;
import proj.xml.gradj.tipovi.Gradjanin;
import proj.xml.gradj.tipovi.Pol;
import proj.xml.gradj.tipovi.PunoIme;
import proj.xml.gradj.tipovi.ZdravstveniRadnik;
import proj.xml.gradj.zahtev.Zahtev;

import javax.xml.datatype.DatatypeFactory;
import java.math.BigInteger;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {
    private final ZahtevRepository zahtevRepository;
    private final PotvrdaRepository potvrdaRepository;
    private final ObrazacRepository obrazacRepository;
    private final InteresovanjeRepository interesovanjeRepository;
    private final GradjaninRepository gradjaninRepository;
    private final ZdravstveniRadnikRepository zdravstveniRadnikRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        // zahtev hajduka
        zahtevRepository.store(
                new Zahtev()
                        .withRazlogZaPodnosenjeZahteva("Da mogu u voz do Decentera.")
                        .withDatum(new Zahtev.Datum().withValue(DatatypeFactory.newInstance().newXMLGregorianCalendar("2021-03-12T01:32:11.445Z")))
                        .withAbout("Random about")
                        .withMesto(new Zahtev.Mesto().withValue("Sid, RS"))
                        .withPodnosilacZahteva(
                                new Zahtev.PodnosilacZahteva()
                                        .withDatumRodjenja(DatatypeFactory.newInstance().newXMLGregorianCalendar("1987-01-30T00:00:00.000Z"))
                                        .withJMBG(new Zahtev.PodnosilacZahteva.JMBG()
                                                .withValue("3001987128391")
                                        )
                                        .withPol("Muski")
                                        .withImeIPrezime("Dusan Hajduk")
                        )
        );

        // potvrda za hajduka
        potvrdaRepository.store(
                new Potvrda()
                        .withAbout("Random About")
                        .withJMBG(
                                new Potvrda.JMBG()
                                        .withValue("3001987128391")
                        )
                        .withDatum(new Potvrda.Datum().withValue(DatatypeFactory.newInstance().newXMLGregorianCalendar("2021-02-22T01:32:11.445Z")))
                        .withDatumRodjenja(DatatypeFactory.newInstance().newXMLGregorianCalendar("1987-01-30T00:00:00.000Z"))
                        .withNazivVakcine("Astra Zeneca")
                        .withIme("Dusan")
                        .withPrezime("Hajduk")
                        .withQRCode("Random QR code")
                        .withVakcinacija(
                                new TVakcinacija()
                                        .withDatumDavanja(DatatypeFactory.newInstance().newXMLGregorianCalendar("2021-01-11T01:32:11.445Z"))
                                        .withSerija(BigInteger.valueOf(12345))
                        )
                        .withZdravstvenaUstanova(
                                new Potvrda.ZdravstvenaUstanova()
                                        .withValue("Dom Zdravlja Sid")
                        )
                        .withZdravstvenaUstanovaKojaVakcinise("Budalasta ustanova vakcinaska sator u sidskoj skoli")
                        .withQRCode("Random QR Code")
        );

        // obrazac za hajduka
        obrazacRepository.store(
                new Obrazac()
                        .withAbout("Random About")
                        .withEvidencijaOVakcinaciji(
                                new Obrazac.EvidencijaOVakcinaciji()
                                        .withKontradikcije(
                                                new Kontradikcije()
                                                        .withDatum(DatatypeFactory.newInstance().newXMLGregorianCalendar("2021-01-11T01:32:11.445Z"))
                                                        .withDijagnoza("Maidenless")
                                                        .withTrajneKontradikcije("Ne moze voz da stigne")
                                        )
                                        .withLekar(
                                                new Lekar()
                                                        .withIme("Luka")
                                                        .withPrezime("Stanisavljevic")
                                                        .withBrojTelefona("0652348291")
                                                        .withFaksmil("Random Faksmil")
                                        )
                                        .withZdravstvenaUstanova(
                                                new Obrazac.EvidencijaOVakcinaciji.ZdravstvenaUstanova()
                                                        .withValue("Koca Popovic")
                                        )
                                        .withVakcinacije(
                                                new proj.xml.gradj.obrazac.TVakcinacija()
                                                        .withDatum(DatatypeFactory.newInstance().newXMLGregorianCalendar("2021-01-11T01:32:11.445Z"))
                                                        .withNazivVakcine("Pfizer-BioNtech")
                                                        .withEkstremitet("Noga")
                                                        .withNacinDavanjaVakcine("Rukom")
                                                        .withProizvodjac("Biontech")
                                                        .withNezeljeneReakcije("Nista")
                                                        .withSerijaVakicne("12345")
                                                ,
                                                new proj.xml.gradj.obrazac.TVakcinacija()
                                                        .withDatum(DatatypeFactory.newInstance().newXMLGregorianCalendar("2021-06-11T01:32:11.445Z"))
                                                        .withNazivVakcine("Pfizer-BioNtech")
                                                        .withEkstremitet("Noga")
                                                        .withNacinDavanjaVakcine("Rukom")
                                                        .withProizvodjac("Biontech")
                                                        .withNezeljeneReakcije("Nista")
                                                        .withSerijaVakicne("12345")
                                        )
                                        .withVakcinacijskiPunkt("punkt 12345")
                        )
                        .withID(BigInteger.valueOf(12324567))
                        .withPodaciOPacijentu(
                                new Obrazac.PodaciOPacijentu()
                                        .withIme("Dusan")
                                        .withPrezime("Hajduk")
                                        .withDatum(DatatypeFactory.newInstance().newXMLGregorianCalendar("2021-06-11T01:32:11.445Z"))
                                        .withDatumRodjenja(DatatypeFactory.newInstance().newXMLGregorianCalendar("1987-01-30T00:00:00.000Z"))
                                        .withPol("Muski")
                                        .withBrojFiksni("123146")
                                        .withBrojMobilni("0656738428")
                                        .withDrzavljanstvo(new Drzavljanstvo().withSrpskiDrzavljanin(
                                                        new Drzavljanstvo.SrpskiDrzavljanin()
                                                                .withJMBG(
                                                                        new Drzavljanstvo.SrpskiDrzavljanin.JMBG()
                                                                                .withValue("3001987128391")
                                                                )
                                                )
                                        )
                                        .withEmail("dusanbrk@gmail.com")
                                        .withImeRoditelja("Pegaz")
                                        .withImunoloskiLek("Decenter")
                                        .withLokacija(
                                                new Lokacija()
                                                        .withAdresa(new Lokacija.Adresa()
                                                                .withUlica("Hajducke Brace")
                                                                .withBroj(BigInteger.valueOf(12))
                                                        )
                                                        .withMestoNaselje("Sid")
                                                        .withOpstinaGrad("Sid")
                                        )
                                        .withMestoRodjenja("Sid")
                                        .withRadniStatus("Zaposlen")
                                        .withSaglasnost("Saglasan")
                                        .withSocZastita(
                                                new SocZastita()
                                                        .withKorisnikSocZast("Hajducija")
                                                        .withNazivSedista("BG")
                                                        .withOpstinaSedista("BG")
                                        )
                                        .withZanimanje("Inzenjer - programer")
                        )
        );

        // interesovanje hajducije
        interesovanjeRepository.store(
                new Interesovanje()
                        .withIme("Dusan")
                        .withPrezime("Hajduk")
                        .withDatum(new Interesovanje.Datum().withValue(DatatypeFactory.newInstance().newXMLGregorianCalendar("1987-01-30T00:00:00.000Z")))
                        .withJMBG(new Interesovanje.JMBG().withValue("3001987128391"))
                        .withAbout("Random about")
                        .withDavalacKrvi("Nije")
                        .withAdresaElektronskePoste("dusanbrk@gmail.com")
                        .withBrojFiksnogTelefona("1234556")
                        .withBrojMobilnogTelefona("0678237264")
                        .withID(BigInteger.valueOf(123155632))
                        .withLokacija(
                                new Interesovanje.Lokacija()
                                        .withValue("Sid")
                        )
                        .withOpcijaDrzavljanin("Srpski")
                        .withProizvodjacVakcine("Biontech")
        );
        
        gradjaninRepository.store(
        		new Gradjanin()
        		.withImeIPrezime(new PunoIme()
        				.withIme("Marko")
        				.withPrezime("Markovic"))
        		.withEmail("nadjaimatijaprojekat@gmail.com")
        		.withEnabled(true)
        		.withJMBG("1234567890123")
        		.withLastPasswordReset(DatatypeFactory.newInstance().newXMLGregorianCalendar("2022-01-30T00:00:00.000Z"))
        		.withLozinka("marko")
        		.withOpcijaDrzavljanin("Drzavljanin Republike Srbije")
        		.withPol(Pol.MUSKI));
        
        gradjaninRepository.store(
        		new Gradjanin()
        		.withImeIPrezime(new PunoIme()
        				.withIme("Dusan")
        				.withPrezime("Hajduk"))
        		.withEmail("dusanbrk@gmail.com")
        		.withEnabled(true)
        		.withJMBG("3001987128391")
        		.withLastPasswordReset(DatatypeFactory.newInstance().newXMLGregorianCalendar("2022-01-30T00:00:00.000Z"))
        		.withLozinka("hajduk")
        		.withOpcijaDrzavljanin("Drzavljanin Republike Srbije")
        		.withPol(Pol.MUSKI));
        
        zdravstveniRadnikRepository.store(
        		new ZdravstveniRadnik()
        		.withImeIPrezime(new PunoIme()
        				.withIme("Zdravko")
        				.withPrezime("Zdravkovic"))
        		.withEmail("nadjaimatijaprojekat@gmail.com")
        		.withEnabled(true)
        		.withJMBG("5234567890123")
        		.withLastPasswordReset(DatatypeFactory.newInstance().newXMLGregorianCalendar("2022-01-30T00:00:00.000Z"))
        		.withLozinka("zdravko")
        		.withPunkt(new BigInteger("1"))
        		.withZdravstvenaUstanova("Dom zdravlja Liman"));
       
    }
}