package fon.bg.ac.rs.fpis.trunks.service;

import fon.bg.ac.rs.fpis.trunks.dto.NarudzbenicaDto;
import fon.bg.ac.rs.fpis.trunks.exceptions.RadnikNotFoundException;
import fon.bg.ac.rs.fpis.trunks.model.Dobavljac;
import fon.bg.ac.rs.fpis.trunks.model.Materijal;
import fon.bg.ac.rs.fpis.trunks.model.Narudzbenica;
import fon.bg.ac.rs.fpis.trunks.model.StavkaNarudzbenice;
import fon.bg.ac.rs.fpis.trunks.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class NarudzbenicaService {

    public static final Logger logger = LoggerFactory.getLogger(NarudzbenicaService.class);

    private final NarudzbenicaJPA narudzbenicaJPA;
    private final DobavljacJPA dobavljacJPA;
    private final MaterijalJPA materijalJPA;
    private final AdresaDobavljacaJPA adresaDobavljacaJPA;
    private final StavkaJPA stavkaJPA;
    private final ConversionService conversionService;

    @Autowired
    public NarudzbenicaService(NarudzbenicaJPA narudzbenicaJPA,
                               DobavljacJPA dobavljacJPA,
                               MaterijalJPA materijalJPA,
                               AdresaDobavljacaJPA adresaDobavljacaJPA,
                               StavkaJPA stavkaJPA, ConversionService conversionService) {
        this.narudzbenicaJPA = narudzbenicaJPA;
        this.dobavljacJPA = dobavljacJPA;
        this.materijalJPA = materijalJPA;
        this.adresaDobavljacaJPA = adresaDobavljacaJPA;
        this.stavkaJPA = stavkaJPA;
        this.conversionService = conversionService;
    }

    public Dobavljac vratiDobavljaca(String pib) {
        logger.info("Getting dobavljac...");
        Dobavljac dobavljac = dobavljacJPA.findById(pib).get();
        logger.info("Dobavljac: {}", dobavljac);
        return dobavljac;
    }

    public Narudzbenica vratiNarudzbenicu(Long sifra) {
        logger.info("Getting narudzbenica...");

        Narudzbenica narudzbenica = narudzbenicaJPA.findById(sifra).get();

//        logger.info("Narudzbenica: {}", narudzbenica);
        return narudzbenica;
    }

    public List<Materijal> vratiMaterijale() {
        logger.info("Getting materijali...");
        List<Materijal> materijali = materijalJPA.findAll();
        logger.info("Materijali: {}", materijali);
        return materijali;
    }

    public List<Long> dajMax() {
        logger.info("Getting max...");
        List<Long> max = narudzbenicaJPA.dajMax();
        logger.info("Max: {}", max);
        return max;
    }

    @Transactional
    public Boolean sacuvajNarudzbenicu(NarudzbenicaDto narudzbenicaDto) {
        System.out.println(narudzbenicaDto);
        logger.info("Inserting narudzbenica...");
        try {

            narudzbenicaJPA.save(new Narudzbenica(
                    narudzbenicaDto.getSifra(),
                    narudzbenicaDto.getDobavljac(),
                    narudzbenicaDto.getDatum(),
                    narudzbenicaDto.getNapomena(),
                    null
            ));

            if (narudzbenicaDto.getStavke() != null && narudzbenicaDto.getStavke().size() > 0) {
                stavkaJPA.saveAll(narudzbenicaDto.getStavke());
            }

            logger.info("Successfully inserted Narudzbenica.");
            return true;
        } catch (Exception e) {
            logger.error("Error inserting Narudzbenica.");
            return false;
        }
    }

    @Transactional
    public Boolean izmeniNarudzbenicu(NarudzbenicaDto narudzbenicaDto, Long id) {
        logger.info("Updating narudzbenica...");

        System.out.println(narudzbenicaDto);

        Narudzbenica toUpdate = narudzbenicaJPA.findById(id).get();
        if (toUpdate != null) {

            if(listaZaBrisanje != null && listaZaBrisanje.size() > 0) {
                System.out.println("lista nije null i i sadrzi nesto");
                System.out.println(listaZaBrisanje);
                for (Brisanje brisanje : listaZaBrisanje) {
                    for (int j = 0; j < narudzbenicaDto.getStavke().size(); j++) {
                        if (brisanje.getRbr() == narudzbenicaDto.getStavke().get(j).getRedni_broj()) {
                            stavkaJPA.delete(narudzbenicaDto.getStavke().get(j));
                            narudzbenicaDto.getStavke().remove(j);
                            break;
                        }
                    }
                }
            }

            toUpdate.setDobavljac(narudzbenicaDto.getDobavljac());
            toUpdate.setNapomena(narudzbenicaDto.getNapomena());
            toUpdate.setDatum(narudzbenicaDto.getDatum());
            toUpdate.setStavke(narudzbenicaDto.getStavke());

            try {
                narudzbenicaJPA.save(toUpdate);
                listaZaBrisanje = new ArrayList<>();
                logger.info("Successfully updated Narudzbenica.");
                return true;
            } catch (Exception e) {
                logger.error("Error updating Narudzbenica.");
                return false;
            }
        } else {
            listaZaBrisanje = new ArrayList<>();
            logger.error("Narudzbenica with id: {} doesn't exist.", id);
            throw new RadnikNotFoundException(String.format("Narudzbenica with id: %s doesn't exist.", id));
        }
    }

    public void obrisiStavku(ArrayList<Long> rbr, Long sifra) {
        listaZaBrisanje = new ArrayList<>();
        for (Long rb : rbr) {
            listaZaBrisanje.add(new Brisanje(rb, sifra));
        }
    }

    private List<Brisanje> listaZaBrisanje;

    private class Brisanje {
        private Long rbr;
        private Long sifraNar;

        public Brisanje(Long rbr, Long sifraNar) {
            this.rbr = rbr;
            this.sifraNar = sifraNar;
        }

        public Long getRbr() {
            return rbr;
        }

        public Long getSifraNar() {
            return sifraNar;
        }

        @Override
        public String toString() {
            return "Brisanje{" +
                    "rbr=" + rbr +
                    ", sifraNar=" + sifraNar +
                    '}';
        }
    }
}
