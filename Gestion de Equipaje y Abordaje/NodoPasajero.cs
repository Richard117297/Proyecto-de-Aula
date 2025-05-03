public class NodoPasajero
{
    private Pasajero pasajero;
    private NodoPasajero siguiente;

    public Pasajero Pasajero { get => pasajero; set => pasajero = value; }
    public NodoPasajero Siguiente { get => siguiente; set => siguiente = value; }


    public NodoPasajero(Pasajero pasajero)
    {
        Pasajero = pasajero;
        Siguiente = null;
    }
}