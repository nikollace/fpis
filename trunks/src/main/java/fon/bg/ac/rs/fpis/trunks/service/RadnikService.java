package fon.bg.ac.rs.fpis.trunks.service;

import fon.bg.ac.rs.fpis.trunks.dto.RadnikDto;
import fon.bg.ac.rs.fpis.trunks.model.Radnik;
import fon.bg.ac.rs.fpis.trunks.repository.RadnikRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RadnikService {

    public static final Logger logger = LoggerFactory.getLogger(RadnikService.class);

    private final RadnikRepository radnikRepository;

    @Autowired
    public RadnikService(RadnikRepository radnikRepository) {
        this.radnikRepository = radnikRepository;
    }

    @Transactional
    public Boolean insert(RadnikDto radnikDto) {
        logger.info("Inserting Radnik...");
        Radnik radnik = new Radnik(
                radnikDto.getJmbg(),
                radnikDto.getImePrezime(),
                radnikDto.getKoeficijent(),
                radnikDto.getPozicija(),
                radnikDto.getStatus(),
                radnikDto.getDatumZaposlenja());
        logger.info("Radnik inserted.");
        return radnikRepository.insert(radnik);
    }

    @Transactional
    public Boolean update(Long id, RadnikDto radnikDto) {
        logger.info("Updating Radnik...");
        Radnik radnik = new Radnik(
                radnikDto.getJmbg(),
                radnikDto.getImePrezime(),
                radnikDto.getKoeficijent(),
                radnikDto.getPozicija(),
                radnikDto.getStatus(),
                radnikDto.getDatumZaposlenja());
        logger.info("Radnik updated.");
        return radnikRepository.update(radnik, id);
    }

    public Radnik get(Long id) {
        logger.info("Getting Radnik...");
        Optional<Radnik> radnik = radnikRepository.get(id);
        if(radnik == null) return null;
        logger.info("Radnik: {}", radnik);
        return radnik.get();
    }
}
