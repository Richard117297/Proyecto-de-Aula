package co.edu.upb.trenes.models.rutas;

public class NodoRuta {
    private Ruta ruta;
    private NodoRuta siguiente;

    public NodoRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public Ruta getRuta() { return ruta; }
    public NodoRuta getSiguiente() { return siguiente; }
    public void setSiguiente(NodoRuta siguiente) { this.siguiente = siguiente; }
}
