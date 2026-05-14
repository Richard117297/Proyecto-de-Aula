package co.edu.upb.trenes.models.abordaje;

public class NodoPasajero {
    private PasajeroPrioridad pasajero;
    private NodoPasajero siguiente;

    public NodoPasajero(PasajeroPrioridad pasajero) {
        this.pasajero = pasajero;
    }

    public PasajeroPrioridad getPasajero() { return pasajero; }
    public NodoPasajero getSiguiente() { return siguiente; }
    public void setSiguiente(NodoPasajero siguiente) { this.siguiente = siguiente; }
}
