package fon.bg.ac.rs.fpis.trunks.service;

import fon.bg.ac.rs.fpis.trunks.dto.RadnikDto;
import fon.bg.ac.rs.fpis.trunks.model.Radnik;
import fon.bg.ac.rs.fpis.trunks.repository.RadnikJPA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RadnikService {

    public static final Logger logger = LoggerFactory.getLogger(RadnikService.class);

    private final RadnikJPA rRepo;

    @Autowired
    public RadnikService(RadnikJPA rRepo) {
        this.rRepo = rRepo;
    }

    @Transactional
    public Integer insert(RadnikDto radnikDto) {
        logger.info("Inserting Radnik...");
        logger.info("Radnik inserted.");
        return rRepo.insert(
                radnikDto.getJmbg(),
                radnikDto.getImePrezime(),
                radnikDto.getKoeficijent(),
                radnikDto.getPozicija().name(),
                radnikDto.getStatus().name(),
                radnikDto.getDatumZaposlenja());
    }

    @Transactional
    public Integer update(Long id, RadnikDto radnikDto) {
        logger.info("Updating Radnik...");
        logger.info("Radnik updated.");
        return rRepo.update(
                radnikDto.getJmbg(),
                radnikDto.getImePrezime(),
                radnikDto.getKoeficijent(),
                radnikDto.getPozicija().name(),
                radnikDto.getStatus().name(),
                radnikDto.getDatumZaposlenja(),
                id);
    }

    public Radnik get(Long id) {
        logger.info("Getting Radnik...");
        Optional<Radnik> radnik = rRepo.findById(id);
        if(radnik == null) return null;
        logger.info("Radnik: {}", radnik);
        return radnik.get();
    }
}
