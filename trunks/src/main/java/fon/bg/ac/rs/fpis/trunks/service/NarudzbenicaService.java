package fon.bg.ac.rs.fpis.trunks.service;

import fon.bg.ac.rs.fpis.trunks.dto.NarudzbenicaDto;
import fon.bg.ac.rs.fpis.trunks.model.Dobavljac;
import fon.bg.ac.rs.fpis.trunks.model.Materijal;
import fon.bg.ac.rs.fpis.trunks.model.Narudzbenica;
import fon.bg.ac.rs.fpis.trunks.model.StavkaNarudzbenice;
import fon.bg.ac.rs.fpis.trunks.repository.NarudzbenicaRepository;
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

    private final NarudzbenicaRepository narudzbenicaRepository;
    private final ConversionService conversionService;

    @Autowired
    public NarudzbenicaService(NarudzbenicaRepository narudzbenicaRepository, ConversionService conversionService) {
        this.narudzbenicaRepository = narudzbenicaRepository;
        this.conversionService = conversionService;
    }

    public Dobavljac vratiDobavljaca(String pib) {
        logger.info("Getting dobavljac...");
        Dobavljac dobavljac = narudzbenicaRepository.vratiDobavljaca(pib).get();
        logger.info("Dobavljac: {}", dobavljac);
        return dobavljac;
    }

    public Narudzbenica vratiNarudzbenicu(Long sifra) {
        logger.info("Getting narudzbenica...");
        Narudzbenica narudzbenica = narudzbenicaRepository.get(sifra).get();
        logger.info("Narudzbenica: {}", narudzbenica);
        return narudzbenica;
    }

    public List<Materijal> vratiMaterijale() {
        logger.info("Getting materijali...");
        List<Materijal> materijali = narudzbenicaRepository.vratiMaterijale();
        logger.info("Materijali: {}", materijali);
        return materijali;
    }

    public List<StavkaNarudzbenice> vratiStavke(Long id) {
        logger.info("Getting stavke...");
        List<StavkaNarudzbenice> stavke = narudzbenicaRepository.vratiStavke(id);
        logger.info("Stavke: {}", stavke);
        return stavke;
    }

    public List<Long> dajMax() {
        logger.info("Getting max...");
        List<Long> max = narudzbenicaRepository.maxSifra();
        logger.info("Max: {}", max);
        return max;
    }

    @Transactional
    public Boolean sacuvajNarudzbenicu(NarudzbenicaDto narudzbenicaDto) {
        logger.info("Inserting narudzbenica...");
        Boolean uspesno = narudzbenicaRepository.insert(
                new Narudzbenica(
                        narudzbenicaDto.getSifra(),
                        narudzbenicaDto.getDobavljac(),
                        narudzbenicaDto.getDatum(),
                        narudzbenicaDto.getNapomena(),
                        narudzbenicaDto.getStavke())
                );
        logger.info("Successfully saved Narudzbenica.");
        return uspesno;
    }

    @Transactional
    public Boolean izmeniNarudzbenicu(NarudzbenicaDto narudzbenicaDto, Long id) {
        logger.info("Updating narudzbenica...");
        Boolean uspesno = narudzbenicaRepository.update(
                new Narudzbenica(
                        narudzbenicaDto.getSifra(),
                        narudzbenicaDto.getDobavljac(),
                        narudzbenicaDto.getDatum(),
                        narudzbenicaDto.getNapomena(),
                        narudzbenicaDto.getStavke()), id
        );
        logger.info("Successfully updated Narudzbenica.");
        return uspesno;
    }

    public void obrisiStavku(ArrayList<Long> rbr, Long sifra) {
        logger.info("Deleting stavka narudzbenica...");
        narudzbenicaRepository.obrisiStavku(rbr, sifra);
    }
}
