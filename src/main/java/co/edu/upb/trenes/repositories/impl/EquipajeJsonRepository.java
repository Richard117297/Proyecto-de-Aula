package co.edu.upb.trenes.repositories.impl;

import co.edu.upb.trenes.models.equipaje.Equipaje;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class EquipajeJsonRepository extends AbstractJsonRepository<Equipaje> {
    public EquipajeJsonRepository() {
        super("equipajes.json", new TypeReference<List<Equipaje>>() {}, Equipaje::getId);
    }
}
