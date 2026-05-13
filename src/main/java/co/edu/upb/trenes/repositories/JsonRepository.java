package co.edu.upb.trenes.repositories;

import java.util.List;
import java.util.Optional;

public interface JsonRepository<T, ID> {
    List<T> findAll();
    Optional<T> findById(ID id);
    T save(T entity);
    T update(T entity);
    boolean deleteById(ID id);
}
