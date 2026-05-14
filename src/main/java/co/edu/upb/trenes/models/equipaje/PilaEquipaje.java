package co.edu.upb.trenes.models.equipaje;

public class PilaEquipaje {
    private NodoEquipaje cima;

    public void apilar(Equipaje equipaje) {
        NodoEquipaje nodo = new NodoEquipaje(equipaje);
        nodo.setSiguiente(cima);
        cima = nodo;
    }

    public Equipaje desapilar() {
        if (cima == null) {
            return null;
        }
        Equipaje equipaje = cima.getEquipaje();
        cima = cima.getSiguiente();
        return equipaje;
    }

    public boolean estaVacia() {
        return cima == null;
    }
}
