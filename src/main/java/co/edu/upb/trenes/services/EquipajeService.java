package co.edu.upb.trenes.services;

import co.edu.upb.trenes.exceptions.ValidationException;
import co.edu.upb.trenes.models.equipaje.Equipaje;
import co.edu.upb.trenes.models.equipaje.PilaEquipaje;
import co.edu.upb.trenes.repositories.impl.EquipajeJsonRepository;

public class EquipajeService {
    private static final int MAX_MALETAS_POR_PASAJERO = 2;
    private static final double MAX_PESO_KG = 80.0;

    private final EquipajeJsonRepository equipajeRepository;

    public EquipajeService() {
        this(new EquipajeJsonRepository());
    }

    public EquipajeService(EquipajeJsonRepository equipajeRepository) {
        this.equipajeRepository = equipajeRepository;
    }

    public Equipaje registrar(String pasajeroId, String boletoId, double pesoKg) {
        long cantidad = equipajeRepository.findAll().stream()
                .filter(item -> pasajeroId.equals(item.getPasajeroId()))
                .filter(item -> !item.isEntregado())
                .count();
        if (cantidad >= MAX_MALETAS_POR_PASAJERO) {
            throw new ValidationException("Cada pasajero puede registrar maximo 2 maletas.");
        }
        if (pesoKg <= 0 || pesoKg > MAX_PESO_KG) {
            throw new ValidationException("Cada maleta debe pesar maximo 80 kg.");
        }
        PilaEquipaje pila = new PilaEquipaje();
        Equipaje equipaje = Equipaje.nuevo(pasajeroId, boletoId, pesoKg);
        pila.apilar(equipaje);
        return equipajeRepository.save(pila.desapilar());
    }

    public Equipaje entregar(String equipajeId) {
        Equipaje equipaje = equipajeRepository.findById(equipajeId)
                .orElseThrow(() -> new ValidationException("Equipaje no encontrado."));
        equipaje.setEntregado(true);
        return equipajeRepository.update(equipaje);
    }
}
