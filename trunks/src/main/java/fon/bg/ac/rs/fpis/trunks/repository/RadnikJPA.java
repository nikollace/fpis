package fon.bg.ac.rs.fpis.trunks.repository;

import fon.bg.ac.rs.fpis.trunks.model.Pozicija;
import fon.bg.ac.rs.fpis.trunks.model.Radnik;
import fon.bg.ac.rs.fpis.trunks.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Optional;

@Repository
public interface RadnikJPA extends JpaRepository<Radnik, Long> {

    @Modifying
    @Query(value = "insert into radnik(jmbg, ime_prezime, koeficijent, pozicija, status, datum_zaposlenja) " +
            "values (:jmbg, :ime_prezime, :koeficijent, :pozicija, :status, :datum_zaposlenja)", nativeQuery = true)
    Integer insert(
            @Param("jmbg") String jmbg,
            @Param("ime_prezime") String ime_prezime,
            @Param("koeficijent") Double koeficijent,
            @Param("pozicija") String pozicija,
            @Param("status") String status,
            @Param("datum_zaposlenja") Timestamp datum_zaposlenja
            );

    @Override
    Optional<Radnik> findById(Long aLong);

    @Modifying
    @Query(value = "update radnik set jmbg= :jmbg, ime_prezime= :ime_prezime, koeficijent= :koeficijent, pozicija= :pozicija, status= :status, datum_zaposlenja= :datum_zaposlenja where sifra= :sifra", nativeQuery = true)
    Integer update(
            @Param("jmbg") String jmbg,
            @Param("ime_prezime") String ime_prezime,
            @Param("koeficijent") Double koeficijent,
            @Param("pozicija") String pozicija,
            @Param("status") String status,
            @Param("datum_zaposlenja") Timestamp datum_zaposlenja,
            @Param("sifra") Long sifra
            );
}
