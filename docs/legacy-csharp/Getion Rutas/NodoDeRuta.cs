public class NodoDeRuta {

    private Ruta ruta;
    private NodoDeRuta siguiente;


    public Ruta Ruta { get => ruta; set => ruta = value; }
    public NodoDeRuta Siguiente { get => siguiente; set => siguiente = value; }


    public NodoDeRuta( Ruta ruta ) {

        Ruta = ruta;
        siguiente = null;

    }
}