internal class ListaEnlazadaSimple_Rutas
{

    private NodoDeRuta cabeza;

    public NodoDeRuta Cabeza { get => cabeza; set => cabeza = value; }

    public ListaEnlazadaSimple_Rutas()
    {

        cabeza = null;
    }

    public void addRuta(Ruta ruta)
    {

        NodoDeRuta nuevaRuta = new NodoDeRuta(ruta);

        if (cabeza == null)
        {

            cabeza = nuevaRuta;

        }

        NodoDeRuta actual = cabeza;

        while (actual.Siguiente != null)
        {

            actual = actual.Siguiente;

        }
        actual.Siguiente = nuevaRuta;
    }


    public void mostrarRutas()
    {

        if (cabeza == null)
        {

            Console.WriteLine($"La lista de rutas esta vacia.");
            return;
        }

        NodoDeRuta actual = cabeza;

        while (actual != null)
        {

            Console.WriteLine($"Id de la ruta : {actual.Ruta.IdRuta} ");
            Console.WriteLine($"Fecha hora de salida : {actual.Ruta.FechaHoraDeSalida} ");
            Console.WriteLine($"Estacion de partida : {actual.Ruta.EstacionDePartida} ");
            Console.WriteLine($"Fecha Hora de Llegada : {actual.Ruta.FechaHoraDeLlegada} ");
            Console.WriteLine($"Estacion de llegada :  {actual.Ruta.EstacionDeLlegada} ");
            Console.WriteLine("-----------------------------------------------------------------------");
            Console.WriteLine();

            actual = actual.Siguiente;
        }
    }


    public void BuscarRuta(Ruta idRuta)
    {


        if (cabeza == null)
        {
            Console.WriteLine("La lista de rutas está vacía.");
            return;
        }

        NodoDeRuta actual = cabeza;

        while (actual != null)
        {
            if (actual.Ruta.IdRuta.Equals(idRuta))
            {
                Console.WriteLine($"La ruta econtrada tiene el Id : {actual.Ruta.IdRuta} ");
                Console.WriteLine($"Fecha hora de salida : {actual.Ruta.FechaHoraDeSalida} ");
                Console.WriteLine($"Estacion de partida : {actual.Ruta.EstacionDePartida} ");
                Console.WriteLine($"Fecha Hora de Llegada : {actual.Ruta.FechaHoraDeLlegada} ");
                Console.WriteLine($"Estacion de llegada :  {actual.Ruta.EstacionDeLlegada} ");
                Console.WriteLine("-----------------------------------------------------------------------");
                Console.WriteLine();
                return;
            }
            actual = actual.Siguiente;
        }

        Console.WriteLine("No se encontró el boleto.");


    }



}