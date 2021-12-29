package fon.bg.ac.rs.fpis.trunks.repository;

import java.util.Optional;

public interface AbstractRepository<T, K> {
    Boolean insert(T t);
    Optional<T> get(K id);
    Boolean update(T t, K k);
}
