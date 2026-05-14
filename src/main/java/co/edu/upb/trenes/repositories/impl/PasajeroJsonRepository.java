package co.edu.upb.trenes.repositories.impl;

import co.edu.upb.trenes.models.usuarios.Pasajero;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class PasajeroJsonRepository extends AbstractJsonRepository<Pasajero> {
    public PasajeroJsonRepository() {
        super("pasajeros.json", new TypeReference<List<Pasajero>>() {}, Pasajero::getId);
    }
}
