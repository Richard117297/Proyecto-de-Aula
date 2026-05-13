package co.edu.upb.trenes.models.trenes;

import java.util.ArrayList;
import java.util.List;

public class ListaEnlazadaSimpleTrenes {
    private NodoTren cabeza;

    public void agregar(Tren tren) {
        NodoTren nodo = new NodoTren(tren);
        if (cabeza == null) {
            cabeza = nodo;
            return;
        }
        NodoTren actual = cabeza;
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }
        actual.setSiguiente(nodo);
    }

    public List<Tren> aLista() {
        List<Tren> trenes = new ArrayList<>();
        NodoTren actual = cabeza;
        while (actual != null) {
            trenes.add(actual.getTren());
            actual = actual.getSiguiente();
        }
        return trenes;
    }
}
