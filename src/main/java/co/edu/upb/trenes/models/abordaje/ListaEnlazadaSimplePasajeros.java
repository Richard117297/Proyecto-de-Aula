package co.edu.upb.trenes.models.abordaje;

import java.util.ArrayList;
import java.util.List;

public class ListaEnlazadaSimplePasajeros {
    private NodoPasajero cabeza;

    public void agregar(PasajeroPrioridad pasajero) {
        NodoPasajero nodo = new NodoPasajero(pasajero);
        if (cabeza == null) {
            cabeza = nodo;
            return;
        }
        NodoPasajero actual = cabeza;
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }
        actual.setSiguiente(nodo);
    }

    public List<PasajeroPrioridad> aLista() {
        List<PasajeroPrioridad> pasajeros = new ArrayList<>();
        NodoPasajero actual = cabeza;
        while (actual != null) {
            pasajeros.add(actual.getPasajero());
            actual = actual.getSiguiente();
        }
        return pasajeros;
    }
}
