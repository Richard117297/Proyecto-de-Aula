package co.edu.upb.trenes.models.trenes;

public class PilaVagones {
    private NodoVagon cima;

    public void apilar(Vagon vagon) {
        NodoVagon nodo = new NodoVagon(vagon);
        nodo.setSiguiente(cima);
        cima = nodo;
    }

    public Vagon desapilar() {
        if (cima == null) {
            return null;
        }
        Vagon vagon = cima.getVagon();
        cima = cima.getSiguiente();
        return vagon;
    }
}
