package co.edu.upb.trenes.repositories.impl;

import co.edu.upb.trenes.models.rutas.SolicitudCambioRuta;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class SolicitudCambioRutaJsonRepository extends AbstractJsonRepository<SolicitudCambioRuta> {
    public SolicitudCambioRutaJsonRepository() {
        super("solicitudes-cambio-ruta.json", new TypeReference<List<SolicitudCambioRuta>>() {}, SolicitudCambioRuta::getId);
    }
}
