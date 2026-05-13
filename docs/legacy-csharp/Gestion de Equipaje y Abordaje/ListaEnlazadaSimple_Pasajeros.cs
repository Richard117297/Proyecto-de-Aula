public class ListaEnlazadaSimple_Pasajeros
{
    private NodoPasajero cabeza;
    public NodoPasajero Cabeza { get => cabeza; set => cabeza = value; }

    public ListaEnlazadaSimple_Pasajeros()
    {
        Cabeza = null;
    }

    public void AddPasajero(Pasajero pasajero)
    {
    if (pasajero != null)
        {
        NodoPasajero nuevoNodo = new NodoPasajero(pasajero);
            if (cabeza == null)
            {
                cabeza = nuevoNodo;
            }
            else
            {
                NodoPasajero actual = cabeza;
                while (actual.Siguiente != null)
                {
                    actual = actual.Siguiente;
                }
                actual.Siguiente = nuevoNodo;
            }
        }
    }

    public Pasajero BuscarPasajeroPorId(string idPasajero)
    {
        if (string.IsNullOrEmpty(idPasajero)) return null;

        NodoPasajero actual = cabeza;
        while (actual != null)
        {
            if (actual.Pasajero != null && !string.IsNullOrEmpty(actual.Pasajero.Id) && actual.Pasajero.Id.Equals(idPasajero, StringComparison.OrdinalIgnoreCase))
            {
                return actual.Pasajero;
            }
            actual = actual.Siguiente;
        }
        return null;
    }
}
