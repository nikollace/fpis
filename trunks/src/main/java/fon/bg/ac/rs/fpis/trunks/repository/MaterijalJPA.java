package fon.bg.ac.rs.fpis.trunks.repository;

import fon.bg.ac.rs.fpis.trunks.model.Materijal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaterijalJPA extends JpaRepository<Materijal, Long> {

    @Override
    Optional<Materijal> findById(Long aLong);

    @Override
    List<Materijal> findAll();
}
