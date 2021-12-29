package fon.bg.ac.rs.fpis.trunks.repository;

import fon.bg.ac.rs.fpis.trunks.exceptions.RadnikNotFoundException;
import fon.bg.ac.rs.fpis.trunks.model.Pozicija;
import fon.bg.ac.rs.fpis.trunks.model.Radnik;
import fon.bg.ac.rs.fpis.trunks.model.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RadnikRepository implements AbstractRepository<Radnik, Long> {

    public static final Logger logger = LoggerFactory.getLogger(RadnikRepository.class);

    private final JdbcTemplate jdbcTemplate;

    RowMapper<Radnik> rowMapper = (rs, rowNum) -> {
        Radnik radnik = new Radnik();
        radnik.setSifra(rs.getLong("sifra"));
        radnik.setJmbg(rs.getString("jmbg"));
        radnik.setImePrezime(rs.getString("ime_prezime"));
        radnik.setKoeficijent(rs.getDouble("koeficijent"));
        radnik.setPozicija(Pozicija.valueOf(rs.getString("pozicija")));
        radnik.setStatus(Status.valueOf(rs.getString("status")));
        radnik.setDatumZaposlenja(rs.getTimestamp("datum_zaposlenja"));
        return radnik;
    };

    @Autowired
    public RadnikRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Boolean insert(Radnik radnik) {
        logger.info("Creating Radnik...");
        String sql = "insert into radnik(jmbg, ime_prezime, koeficijent, pozicija, status, datum_zaposlenja) values (?, ?, ?, ?, ?, ?)";
        int inserted = jdbcTemplate.update(sql,
                radnik.getJmbg(),
                radnik.getImePrezime(),
                radnik.getKoeficijent(),
                String.valueOf(radnik.getPozicija()),
                String.valueOf(radnik.getStatus()),
                radnik.getDatumZaposlenja());
        if(inserted == 1) {
            logger.info("Inserted Radnik: {}", radnik.getJmbg());
            return true;
        } else {
            logger.error("Radnik can not be inserted.");
            return false;
        }
    }

    @Override
    public Optional<Radnik> get(Long id) {
        logger.info("Getting Radnik...");
        String sql = "select * from radnik where sifra = ?";
        Radnik radnik;
        try {
            radnik = jdbcTemplate.queryForObject(sql, rowMapper, new Object[]{id});
        } catch (DataAccessException ex) {
            logger.error("Radnik with id: {} not found.", id);
            return null;
        }

        return Optional.ofNullable(radnik);
    }

    @Override
    public Boolean update(Radnik radnik, Long id) {
        logger.info("Updating Radnik...");
        String sql = "update radnik set jmbg=?, ime_prezime=?, koeficijent=?, pozicija=?, status=? , datum_zaposlenja=? where sifra=?";
        int updated = jdbcTemplate.update(
                sql,
                radnik.getJmbg(),
                radnik.getImePrezime(),
                radnik.getKoeficijent(),
                String.valueOf(radnik.getPozicija()),
                String.valueOf(radnik.getStatus()),
                radnik.getDatumZaposlenja(),
                id);
        if(updated == 1) {
            logger.info("Updated Radnik: {}", radnik.getJmbg());
            return true;
        } else {
            logger.error("Radnik can not be updated.");
            return false;
        }
    }
}
