package co.edu.upb.trenes.models.rutas;

import java.util.ArrayList;
import java.util.List;

public class ListaEnlazadaSimpleRutas {
    private NodoRuta cabeza;

    public void agregar(Ruta ruta) {
        NodoRuta nodo = new NodoRuta(ruta);
        if (cabeza == null) {
            cabeza = nodo;
            return;
        }
        NodoRuta actual = cabeza;
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }
        actual.setSiguiente(nodo);
    }

    public List<Ruta> aLista() {
        List<Ruta> rutas = new ArrayList<>();
        NodoRuta actual = cabeza;
        while (actual != null) {
            rutas.add(actual.getRuta());
            actual = actual.getSiguiente();
        }
        return rutas;
    }
}
