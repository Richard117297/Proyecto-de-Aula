internal class NodoDeEstacion_Grafo {

public class NodoDeEstacion_Grafo
{
    private NodoDeEstacion inicio;
    private NodoDeEstacion fin;

    public NodoDeEstacion Inicio
    {
        get => inicio;
        set => inicio = value;
    }

    public NodoDeEstacion Fin
    {
        get => fin;
        set => fin = value;
    }

    public NodoDeEstacion_Grafo() { 
        inicio = null;
        fin = null;
    }
}
}