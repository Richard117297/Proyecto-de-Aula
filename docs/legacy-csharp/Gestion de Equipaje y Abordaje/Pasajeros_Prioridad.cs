public class Pasajeros_Prioridad 
{

    private Pasajero pasajero;
    private Pasajeros_Prioridad siguiente;
    private int prioridad;
    private Boleto tipoBoleto;

    public Pasajero Pasajero { get => pasajero; set => pasajero = value; }
    public Pasajeros_Prioridad Siguiente { get => siguiente; set => siguiente = value; }
    public int Prioridad { get => prioridad; set => prioridad = value; }
    public Boleto TipoBoleto { get => tipoBoleto; set => tipoBoleto = value; }

    public Pasajeros_Prioridad(Pasajero pasajero, int prioridad, Boleto tipoBoleto) {
           
        Pasajero = pasajero;
        Prioridad = prioridad;
        TipoBoleto = tipoBoleto;
        Siguiente = null;
    }
}