public class Nodo_Equipaje 
{

    private Equipaje equipaje;
    private Nodo_Equipaje siguiente;

    public Equipaje Equipaje { get => equipaje; set => equipaje = value; }
    public Nodo_Equipaje Siguiente { get => siguiente; set => siguiente = value; }

    public Nodo_Equipaje(Equipaje equipaje) {

        Equipaje = equipaje;
        Siguiente = null;
    }
}