internal class ListaEnlazadaSimple_Trenes
{

    private NodoTren cabeza;

    public NodoTren Cabeza { get => cabeza; set => cabeza = value; }

    public ListaEnlazadaSimple_Trenes()
    {

        Cabeza = null;

    }


    public void agregarTren(Tren tren)
    {

        NodoTren nuevoTren = new NodoTren(tren);

        if (cabeza == null)
        {

            cabeza = nuevoTren;
            return;
        }

        NodoTren actual = cabeza;

        while (actual.Siguiente != null)
        {

            actual = actual.Siguiente;

        }
        actual.Siguiente = nuevoTren;

    }


    public void buscarTren(Tren idTren)
    {

        if (cabeza == null)
        {

            Console.WriteLine("La lista de trenes esta vacia.");
            return;

        }

        NodoTren actual = cabeza;

        while (actual != null)
        {

            if (actual.Tren.IdTren.Equals(idTren))
            {

                Console.WriteLine($"El tren ha sido encontrado:");
                Console.WriteLine($" Nombre : {actual.Tren.Nombre} ");
                Console.WriteLine($" Id del tren :  {actual.Tren.IdTren} ");
                Console.WriteLine($" Kilometraje :  {actual.Tren.Kilometraje}  ");
                Console.WriteLine($" Capacidad de carga :  {actual.Tren.CapacidadVagones}  ");
                Console.WriteLine($" Tipo del tren :  {actual.Tren.TipoTren}  ");
                return;
            }
            actual = actual.Siguiente;
        }

        Console.WriteLine("No se encontro el tren con el ID solicitado.");
    }


    public void mostrarTrenes()
    {

        if (cabeza == null)
        {

            Console.WriteLine("La lista de trenes esta vacia.");
            return;
        }

        NodoTren actual = cabeza;

        while (actual != null)
        {

            Console.WriteLine($" Información del tren :");
            Console.WriteLine($" Nombre : {actual.Tren.Nombre} ");
            Console.WriteLine($" Id del tren :  {actual.Tren.IdTren} ");
            Console.WriteLine($" Kilometraje :  {actual.Tren.Kilometraje}  ");
            Console.WriteLine($" Capacidad de carga :  {actual.Tren.CapacidadVagones}  ");
            Console.WriteLine($" Tipo del tren :  {actual.Tren.TipoTren}  ");
            Console.WriteLine("-----------------------------------------------------------");
            Console.WriteLine("\n");

            actual = actual.Siguiente;

        }
    }


    public void DarDeBajaAlTren(string idTren)
    {


        if (cabeza == null)
        {

            return;
        }

        if (cabeza.Tren.IdTren.Equals(idTren))
        {

            cabeza = cabeza.Siguiente;
            return;
        }

        NodoTren actual = cabeza;

        while (actual.Siguiente != null && actual.Siguiente.Tren.IdTren != idTren)
        {

            actual = actual.Siguiente;
        }

        if (actual.Siguiente != null)
        {
            actual.Siguiente = actual.Siguiente.Siguiente;
        }

    }

}