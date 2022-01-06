package fon.bg.ac.rs.fpis.trunks.repository;

import fon.bg.ac.rs.fpis.trunks.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class NarudzbenicaRepository implements AbstractRepository<Narudzbenica, Long> {

    public static final Logger logger = LoggerFactory.getLogger(NarudzbenicaRepository.class);

    private final JdbcTemplate jdbcTemplate;

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

    private List<Brisanje> listaZaBrisanje;

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
        listaZaBrisanje = new ArrayList<>();
    }

    @Override
    public Boolean insert(Narudzbenica narudzbenica) {
        logger.info("Creating Narudzbenica from request: {}", narudzbenica);
        String sql = "insert into narudzbenica(sifra, dobavljac, datum, napomena) values (?, ?, ?, ?)";

        int inserted = jdbcTemplate.update(sql,
                narudzbenica.getSifra(),
                narudzbenica.getDobavljac().getPib(),
                narudzbenica.getDatum(),
                narudzbenica.getNapomena());
        if (inserted == 1) {
            logger.info("Inserted Narudzbenica: {}", narudzbenica.getSifra());
        } else {
            logger.error("Narudzbenica can not be inserted.");
        }

        String stavkaSql = "insert into stavka_narudzbenice(redni_broj, narudzbenica, kolicina, jm, materijal) values (?, ?, ?, ?, ?)";
        int brojac = 0;
        while (brojac < narudzbenica.getStavke().size()) {
            logger.info("Inserting stavka narudzbenice...");
            int uspesno = jdbcTemplate.update(stavkaSql,
                    narudzbenica.getStavke().get(brojac).getRedni_broj(),
                    narudzbenica.getSifra(),
                    narudzbenica.getStavke().get(brojac).getKolicina(),
                    narudzbenica.getStavke().get(brojac).getJm(),
                    Long.valueOf(narudzbenica.getStavke().get(brojac).getMaterijal().getSifra()));
            if (uspesno == 1) {
                logger.info("Inserted Stavka: {}", narudzbenica.getStavke().get(brojac).getRedni_broj());
            } else {
                logger.error("Stavka can not be inserted.");
                return false;
            }
            brojac++;
        }

        return true;
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

        logger.info("Updating Narudzbenica...");
        String sql = "update narudzbenica set sifra=?, datum=?, napomena=? where sifra=?";
        int updated = jdbcTemplate.update(
                sql,
                narudzbenica.getSifra(),
                narudzbenica.getDatum(),
                narudzbenica.getNapomena(),
                aLong);
        if (updated == 1) {
            logger.info("Updated Narudzbenica: {}", narudzbenica.getSifra());
        } else {
            logger.error("Narudzbenica can not be updated.");
        }

        System.out.println(listaZaBrisanje);

        Map<String, List<StavkaNarudzbenice>> infos = new HashMap<>();
        List<StavkaNarudzbenice> listaDodaj = new ArrayList<>();
        List<StavkaNarudzbenice> listaIzmeni = new ArrayList<>();

        for (int i = 0; i < narudzbenica.getStavke().size(); i++) {

            StavkaNarudzbenice stavkaNarudzbenice1 = vratiStavku(narudzbenica.getStavke().get(i).getRedni_broj(),
                    narudzbenica.getSifra());

            if (stavkaNarudzbenice1 == null) {
                listaDodaj.add(narudzbenica.getStavke().get(i));
                infos.put("dodaj", listaDodaj);
            } else {
                listaIzmeni.add(narudzbenica.getStavke().get(i));
                infos.put("izmeni", listaIzmeni);
            }
        }
        int run = 0;
        while (run < infos.size()) {
            List<StavkaNarudzbenice> stavkeNarudzbenice = infos.get("izmeni");
            logger.info("Stavke za izmeni: {}", stavkeNarudzbenice);

            String stavkaSql = "UPDATE stavka_narudzbenice SET kolicina=?, jm=?, materijal=? WHERE redni_broj=? AND narudzbenica=?";
            logger.info("Updating Stavka Narudzbenice...");

            int brojac = 0;
            while (stavkeNarudzbenice != null && brojac < stavkeNarudzbenice.size()) {
                logger.info("Updating stavka narudzbenice...");
                int uspesno = jdbcTemplate.update(stavkaSql,
                        stavkeNarudzbenice.get(brojac).getKolicina(),
                        stavkeNarudzbenice.get(brojac).getJm(),
                        Long.valueOf(stavkeNarudzbenice.get(brojac).getMaterijal().getSifra()),
                        stavkeNarudzbenice.get(brojac).getRedni_broj(), aLong);
                if (uspesno == 1) {
                    logger.info("Updated Stavka: {}", stavkeNarudzbenice.get(brojac).getRedni_broj());
                } else {
                    logger.error("Stavka can not be updated.");
                    return false;
                }
                brojac++;
                run++;
            }

            List<StavkaNarudzbenice> stavkeNarudzbeniceInsert = infos.get("dodaj");
            logger.info("Stavke za dodaj: {}", stavkeNarudzbeniceInsert);

            String insertStavkaSql = "INSERT INTO stavka_narudzbenice(redni_broj, narudzbenica, kolicina, jm, materijal) VALUES (?, ?, ?, ?, ?)";
            brojac = 0;
            while (stavkeNarudzbeniceInsert != null && brojac < stavkeNarudzbeniceInsert.size()) {
                logger.info("Inserting stavka narudzbenice...");

                int uspesno = jdbcTemplate.update(insertStavkaSql,
                        stavkeNarudzbeniceInsert.get(brojac).getRedni_broj(),
                        narudzbenica.getSifra(),
                        stavkeNarudzbeniceInsert.get(brojac).getKolicina(),
                        stavkeNarudzbeniceInsert.get(brojac).getJm(),
                        Long.valueOf(stavkeNarudzbeniceInsert.get(brojac).getMaterijal().getSifra()));
                if (uspesno == 1) {
                    logger.info("Inserted Stavka: {}", stavkeNarudzbeniceInsert.get(brojac).getRedni_broj());
                } else {
                    logger.error("Stavka can not be inserted.");
                    return false;
                }
                brojac++;
                run++;
            }
        }

        for(int i = 0; i < listaZaBrisanje.size(); i++) {
            logger.info("Deleting stavka narudzbenice...");
            String brisanjeSql = "delete from stavka_narudzbenice where redni_broj=? and narudzbenica=?";
            int deleted = jdbcTemplate.update(
                    brisanjeSql,
                    listaZaBrisanje.get(i).getRbr(),
                    listaZaBrisanje.get(i).getSifraNar());
            if (deleted == 1) {
                logger.info("Deleted stavka narudzbenice with RBR: {} and sifra narudzbenice: {}",
                        listaZaBrisanje.get(i).getRbr(),
                        listaZaBrisanje.get(i).getSifraNar());
            } else {
                logger.error("stavka narudzbenice can not be deleted.");
            }
        }
        return true;
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
        stavkaNarudzbenice.setRedni_broj(rs.getLong("redni_broj"));
        stavkaNarudzbenice.setJm(rs.getString("jm"));
        stavkaNarudzbenice.setKolicina(rs.getInt("kolicina"));

        Materijal materijal = vratiMaterijal(rs.getLong("materijal")).get();
        Narudzbenica narudzbenica = get(rs.getLong("narudzbenica")).get();

        stavkaNarudzbenice.setMaterijal(materijal);
        stavkaNarudzbenice.setNarudzbenica(narudzbenica);

        return stavkaNarudzbenice;
    };

    public List<StavkaNarudzbenice> vratiStavke(Long id) {
        logger.info("Getting stavke...");
        String sql = "select * from stavka_narudzbenice where narudzbenica = ? order by redni_broj";
        return jdbcTemplate.query(sql, rowMapperStavke, new Object[]{id});
    }

    public StavkaNarudzbenice vratiStavku(Long rbr, Long sifraNar) {
        logger.info("Getting stavka...");
        String sql = "select * from stavka_narudzbenice where redni_broj=? and narudzbenica=?";
        StavkaNarudzbenice stavkaNarudzbenice;
        try {
            stavkaNarudzbenice = jdbcTemplate.queryForObject(sql, rowMapperStavke, new Object[]{rbr, sifraNar});
            logger.info("Stavka: {}", stavkaNarudzbenice);
        } catch (DataAccessException ex) {
            logger.error("Stavka with rbr: {} not found.", rbr);
            return null;
        }

        return stavkaNarudzbenice;
    }

    RowMapper<Long> rowMapperMax = (rs, rowNum) -> {
        Long max = rs.getLong("max");
        return max;
    };

    public List<Long> maxSifra() {
        logger.info("Getting maksimalnu sifru...");
        String sql = "select max(sifra) from narudzbenica";
        return jdbcTemplate.query(sql, rowMapperMax);
    }

    public void obrisiStavku(ArrayList<Long> rbr, Long sifra) {
        listaZaBrisanje = new ArrayList<>();
        for (Long rb: rbr) {
            listaZaBrisanje.add(new Brisanje(rb, sifra));
        }
    }
}
