package fon.bg.ac.rs.fpis.trunks.repository;

import fon.bg.ac.rs.fpis.trunks.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class NarudzbenicaRepository implements AbstractRepository<Narudzbenica, Long> {

    public static final Logger logger = LoggerFactory.getLogger(NarudzbenicaRepository.class);

    private final JdbcTemplate jdbcTemplate;

    RowMapper<Narudzbenica> rowMapperNarudzbenica = (rs, rowNum) -> {
        Narudzbenica narudzbenica = new Narudzbenica();
        narudzbenica.setSifra(rs.getLong("sifra"));
        narudzbenica.setDatum(rs.getTimestamp("datum"));
        narudzbenica.setNapomena(rs.getString("napomena"));

        Optional<Dobavljac> dobavljac = vratiDobavljaca(rs.getString("dobavljac"));
        narudzbenica.setDobavljac(dobavljac.get());

        return narudzbenica;
    };

    @Autowired
    public NarudzbenicaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Boolean insert(Narudzbenica narudzbenica) {
        return null;
    }

    @Override
    public Optional<Narudzbenica> get(Long id) {
        logger.info("Getting Narudzbenica...");
        String sql = "select * from narudzbenica where sifra = ?";
        Narudzbenica narudzbenica;
        try {
            narudzbenica = jdbcTemplate.queryForObject(sql, rowMapperNarudzbenica, new Object[]{id});
        } catch (DataAccessException ex) {
            logger.error("Narudzbenica with id: {} not found.", id);
            return null;
        }

        return Optional.ofNullable(narudzbenica);
    }

    @Override
    public Boolean update(Narudzbenica narudzbenica, Long aLong) {
        return null;
    }

    RowMapper<Dobavljac> rowMapperDobavljac = (rs, rowNum) -> {
        Dobavljac dobavljac = new Dobavljac();
        dobavljac.setNaziv(rs.getString("naziv"));
        dobavljac.setPib(rs.getString("pib"));
        dobavljac.setTelefon(rs.getString("telefon"));

        AdresaDobavljaca adresaDobavljaca = vratiAdresu(
                rs.getString("ulica"),
                rs.getString("broj"),
                rs.getString("grad"))
                .get();
        dobavljac.setAdresaDobavljaca(adresaDobavljaca);

        return dobavljac;
    };

    public Optional<Dobavljac> vratiDobavljaca(String pib) {
        logger.info("Getting Dobavljac...");
        String sql = "select * from dobavljac where pib = ?";
        Dobavljac dobavljac;
        try {
            dobavljac = jdbcTemplate.queryForObject(sql, rowMapperDobavljac, new Object[]{pib});
        } catch (DataAccessException ex) {
            logger.error("Radnik with id: {} not found.", pib);
            return null;
        }

        return Optional.ofNullable(dobavljac);
    }

    RowMapper<AdresaDobavljaca> rowMapperAdresa = (rs, rowNum) -> {
        AdresaDobavljaca adresaDobavljaca = new AdresaDobavljaca();
        adresaDobavljaca.setUlica(rs.getString("ulica"));
        adresaDobavljaca.setBroj(rs.getString("broj"));
        adresaDobavljaca.setGrad(rs.getString("grad"));
        adresaDobavljaca.setZemlja(rs.getString("zemlja"));
        return adresaDobavljaca;
    };

    public Optional<AdresaDobavljaca> vratiAdresu(String ulica, String broj, String grad) {
        logger.info("Getting Dobavljac...");
        String sql = "select * from adresa where ulica = ? and broj = ? and grad = ?";
        AdresaDobavljaca adresaDobavljaca;
        try {
            adresaDobavljaca = jdbcTemplate.queryForObject(sql, rowMapperAdresa, new Object[]{ulica, broj, grad});
        } catch (DataAccessException ex) {
            logger.error("adresaDobavljaca with ulica, broj, grad: {}, {}, {} not found.", ulica, broj, grad);
            return null;
        }

        return Optional.ofNullable(adresaDobavljaca);
    }

    RowMapper<Materijal> rowMapperMaterijal = (rs, rowNum) -> {
        Materijal materijal = new Materijal();
        materijal.setSifra(rs.getLong("sifra"));
        materijal.setNaziv(rs.getString("naziv"));
        return materijal;
    };

    public List<Materijal> vratiMaterijale() {
        logger.info("Getting materijali...");
        String sql = "select * from materijal order by sifra";
        return jdbcTemplate.query(sql, rowMapperMaterijal);
    }

    public Optional<Materijal> vratiMaterijal(Long sifra) {
        logger.info("Getting Materijal...");
        String sql = "select * from materijal where sifra = ?";
        Materijal materijal;
        try {
            materijal = jdbcTemplate.queryForObject(sql, rowMapperMaterijal, new Object[]{sifra});
        } catch (DataAccessException ex) {
            logger.error("materijal with sifra: {} not found.", sifra);
            return null;
        }

        return Optional.ofNullable(materijal);
    }

    RowMapper<StavkaNarudzbenice> rowMapperStavke = (rs, rowNum) -> {
        StavkaNarudzbenice stavkaNarudzbenice = new StavkaNarudzbenice();
        stavkaNarudzbenice.setRedniBroj(rs.getLong("redni_broj"));
        stavkaNarudzbenice.setJm(rs.getString("jm"));
        stavkaNarudzbenice.setKolicina(rs.getInt("kolicina"));

        Materijal materijal = vratiMaterijal(rs.getLong("materijal")).get();
        Narudzbenica narudzbenica = get(rs.getLong("narudzbenica")).get();

        stavkaNarudzbenice.setMaterijal(materijal);
        stavkaNarudzbenice.setNarudzbenica(narudzbenica);

        return stavkaNarudzbenice;
    };

    public List<StavkaNarudzbenice> vratiStavke() {
        logger.info("Getting stavke...");
        String sql = "select * from stavka_narudzbenice order by redni_broj";
        return jdbcTemplate.query(sql, rowMapperStavke);
    }
}
