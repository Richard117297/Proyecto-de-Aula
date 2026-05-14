package co.edu.upb.trenes.repositories.impl;

import co.edu.upb.trenes.models.rutas.Estacion;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class EstacionJsonRepository extends AbstractJsonRepository<Estacion> {
    public EstacionJsonRepository() {
        super("estaciones.json", new TypeReference<List<Estacion>>() {}, Estacion::getId);
    }
}
