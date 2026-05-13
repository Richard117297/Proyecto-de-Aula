package co.edu.upb.trenes.repositories.impl;

import co.edu.upb.trenes.models.rutas.ConexionEstacion;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class ConexionEstacionJsonRepository extends AbstractJsonRepository<ConexionEstacion> {
    public ConexionEstacionJsonRepository() {
        super("conexiones-estaciones.json", new TypeReference<List<ConexionEstacion>>() {}, ConexionEstacion::getId);
    }
}
