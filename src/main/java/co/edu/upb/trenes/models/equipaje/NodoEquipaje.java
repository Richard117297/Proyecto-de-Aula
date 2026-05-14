package co.edu.upb.trenes.models.equipaje;

public class NodoEquipaje {
    private Equipaje equipaje;
    private NodoEquipaje siguiente;

    public NodoEquipaje(Equipaje equipaje) {
        this.equipaje = equipaje;
    }

    public Equipaje getEquipaje() { return equipaje; }
    public NodoEquipaje getSiguiente() { return siguiente; }
    public void setSiguiente(NodoEquipaje siguiente) { this.siguiente = siguiente; }
}
