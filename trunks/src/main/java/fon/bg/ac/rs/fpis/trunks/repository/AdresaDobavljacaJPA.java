package fon.bg.ac.rs.fpis.trunks.repository;

import fon.bg.ac.rs.fpis.trunks.model.AdresaDobavljaca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdresaDobavljacaJPA extends JpaRepository<AdresaDobavljaca, String> {

    @Query(value = "select * from adresa where ulica = :ulica and broj = :broj and grad = :grad", nativeQuery = true)
    Optional<AdresaDobavljaca> findById(@Param("ulica") String ulica, @Param("broj") String broj, @Param("grad") String grad);
}
