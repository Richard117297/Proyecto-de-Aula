package co.edu.upb.trenes.services;

import co.edu.upb.trenes.models.rutas.Estacion;
import co.edu.upb.trenes.repositories.impl.EstacionJsonRepository;

import java.util.List;

public class EstacionService {
    private final EstacionJsonRepository estacionRepository = new EstacionJsonRepository();

    public List<Estacion> listar() {
        return estacionRepository.findAll();
    }
}
