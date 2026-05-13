package co.edu.upb.trenes.repositories.impl;

import co.edu.upb.trenes.models.rutas.Ruta;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class RutaJsonRepository extends AbstractJsonRepository<Ruta> {
    public RutaJsonRepository() {
        super("rutas.json", new TypeReference<List<Ruta>>() {}, Ruta::getId);
    }
}
