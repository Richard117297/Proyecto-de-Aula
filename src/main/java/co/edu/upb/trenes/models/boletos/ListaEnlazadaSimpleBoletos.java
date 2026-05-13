package co.edu.upb.trenes.models.boletos;

import java.util.ArrayList;
import java.util.List;

public class ListaEnlazadaSimpleBoletos {
    private NodoBoleto cabeza;

    public void agregar(Boleto boleto) {
        NodoBoleto nuevo = new NodoBoleto(boleto);
        if (cabeza == null) {
            cabeza = nuevo;
            return;
        }
        NodoBoleto actual = cabeza;
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }
        actual.setSiguiente(nuevo);
    }

    public List<Boleto> aLista() {
        List<Boleto> boletos = new ArrayList<>();
        NodoBoleto actual = cabeza;
        while (actual != null) {
            boletos.add(actual.getBoleto());
            actual = actual.getSiguiente();
        }
        return boletos;
    }
}
