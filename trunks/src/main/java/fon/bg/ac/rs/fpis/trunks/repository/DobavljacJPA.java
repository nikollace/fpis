package fon.bg.ac.rs.fpis.trunks.repository;

import fon.bg.ac.rs.fpis.trunks.model.Dobavljac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DobavljacJPA extends JpaRepository<Dobavljac, String> {

    @Override
    Optional<Dobavljac> findById(String s);
}
