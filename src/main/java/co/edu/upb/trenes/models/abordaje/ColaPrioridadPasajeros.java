package co.edu.upb.trenes.models.abordaje;

import java.util.ArrayList;
import java.util.List;

public class ColaPrioridadPasajeros {
    private NodoPasajero frente;

    public void encolar(PasajeroPrioridad pasajero) {
        NodoPasajero nuevo = new NodoPasajero(pasajero);
        if (frente == null || pasajero.prioridad() < frente.getPasajero().prioridad()) {
            nuevo.setSiguiente(frente);
            frente = nuevo;
            return;
        }
        NodoPasajero actual = frente;
        while (actual.getSiguiente() != null
                && actual.getSiguiente().getPasajero().prioridad() <= pasajero.prioridad()) {
            actual = actual.getSiguiente();
        }
        nuevo.setSiguiente(actual.getSiguiente());
        actual.setSiguiente(nuevo);
    }

    public List<PasajeroPrioridad> aListaOrdenada() {
        List<PasajeroPrioridad> pasajeros = new ArrayList<>();
        NodoPasajero actual = frente;
        while (actual != null) {
            pasajeros.add(actual.getPasajero());
            actual = actual.getSiguiente();
        }
        return pasajeros;
    }
}
