package co.edu.upb.trenes.models.trenes;

public class NodoVagon {
    private Vagon vagon;
    private NodoVagon siguiente;

    public NodoVagon(Vagon vagon) {
        this.vagon = vagon;
    }

    public Vagon getVagon() { return vagon; }
    public NodoVagon getSiguiente() { return siguiente; }
    public void setSiguiente(NodoVagon siguiente) { this.siguiente = siguiente; }
}
