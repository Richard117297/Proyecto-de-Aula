package co.edu.upb.trenes.repositories.impl;

import co.edu.upb.trenes.models.trenes.Tren;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class TrenJsonRepository extends AbstractJsonRepository<Tren> {
    public TrenJsonRepository() {
        super("trenes.json", new TypeReference<List<Tren>>() {}, Tren::getId);
    }
}
