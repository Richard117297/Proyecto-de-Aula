package co.edu.upb.trenes.repositories.impl;

import co.edu.upb.trenes.models.boletos.Tarifa;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;
import java.util.Optional;

public class TarifaJsonRepository extends AbstractJsonRepository<Tarifa> {
    public TarifaJsonRepository() {
        super("tarifas.json", new TypeReference<List<Tarifa>>() {}, Tarifa::getId);
    }

    public Optional<Tarifa> findByCategoria(String categoria) {
        return findAll().stream()
                .filter(tarifa -> tarifa.getCategoria() != null && tarifa.getCategoria().name().equals(categoria))
                .findFirst();
    }
}
