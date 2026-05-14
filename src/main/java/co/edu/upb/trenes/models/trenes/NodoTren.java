package co.edu.upb.trenes.models.trenes;

public class NodoTren {
    private Tren tren;
    private NodoTren siguiente;

    public NodoTren(Tren tren) {
        this.tren = tren;
    }

    public Tren getTren() { return tren; }
    public NodoTren getSiguiente() { return siguiente; }
    public void setSiguiente(NodoTren siguiente) { this.siguiente = siguiente; }
}
