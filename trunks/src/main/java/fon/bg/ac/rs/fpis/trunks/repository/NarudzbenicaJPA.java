package fon.bg.ac.rs.fpis.trunks.repository;

import fon.bg.ac.rs.fpis.trunks.model.Narudzbenica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NarudzbenicaJPA extends JpaRepository<Narudzbenica, Long> {

    @Query(value = "select max(sifra) from narudzbenica", nativeQuery = true)
    List<Long> dajMax();
}
