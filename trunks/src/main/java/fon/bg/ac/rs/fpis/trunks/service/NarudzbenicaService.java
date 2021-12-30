package fon.bg.ac.rs.fpis.trunks.service;

import fon.bg.ac.rs.fpis.trunks.model.Dobavljac;
import fon.bg.ac.rs.fpis.trunks.model.Materijal;
import fon.bg.ac.rs.fpis.trunks.model.Narudzbenica;
import fon.bg.ac.rs.fpis.trunks.model.StavkaNarudzbenice;
import fon.bg.ac.rs.fpis.trunks.repository.NarudzbenicaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NarudzbenicaService {

    public static final Logger logger = LoggerFactory.getLogger(NarudzbenicaService.class);

    private final NarudzbenicaRepository narudzbenicaRepository;

    @Autowired
    public NarudzbenicaService(NarudzbenicaRepository narudzbenicaRepository) {
        this.narudzbenicaRepository = narudzbenicaRepository;
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

    public List<StavkaNarudzbenice> vratiStavke() {
        logger.info("Getting stavke...");
        List<StavkaNarudzbenice> stavke = narudzbenicaRepository.vratiStavke();
        logger.info("Stavke: {}", stavke);
        return stavke;
    }
}
