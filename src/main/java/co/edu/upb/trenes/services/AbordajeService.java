package co.edu.upb.trenes.services;

import co.edu.upb.trenes.models.abordaje.ColaPrioridadPasajeros;
import co.edu.upb.trenes.models.abordaje.PasajeroPrioridad;

import java.util.List;

public class AbordajeService {
    public List<PasajeroPrioridad> ordenarAbordaje(List<PasajeroPrioridad> pasajeros) {
        ColaPrioridadPasajeros cola = new ColaPrioridadPasajeros();
        pasajeros.forEach(cola::encolar);
        return cola.aListaOrdenada();
    }
}
