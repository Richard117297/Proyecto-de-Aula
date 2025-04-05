internal class Pila_Vagon
{

    private NodoVagon tope;
    private int contador;

    public NodoVagon Tope { get => tope; set => tope = value; }
    public int Contador { get => contador; set => contador = value; }

    public Pila_Vagon()
    {

        Tope = null;
        Contador = 0;
    }


    public void push(Vagon vagon)
    {

        NodoVagon nuevoVagon = new NodoVagon(vagon);

        nuevoVagon.Siguiente = tope;
        tope = nuevoVagon;
        contador++;

        Console.WriteLine("EL DATO YA SE GUARDO.");
    }

    public string peek()
    {

        if (tope == null)
        {

            Console.WriteLine("La lista esta vacia");
        }

        return $"El primer dato de la lista es : {tope.Vagon.IdDelVagon} \n , {tope.Vagon.CapacidadDeEquipaje} \n , {tope.Vagon.CapacidadDePasajeros} \n , {tope.Vagon.PesoLim} \n , {tope.Vagon.TipoVagon} .";
    }


    public string pop()
    {

        if (tope == null)
        {

            Console.WriteLine("La lista esta vacia");

        }

        string datoEliminado = tope.Vagon.IdDelVagon;
        tope = tope.Siguiente;
        contador--;

        return $"El dato eliminado es : {datoEliminado} .";
    }

}