package fon.bg.ac.rs.fpis.trunks.repository;

import fon.bg.ac.rs.fpis.trunks.model.StavkaNarudzbenice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StavkaJPA extends JpaRepository<StavkaNarudzbenice, Long> {
}
