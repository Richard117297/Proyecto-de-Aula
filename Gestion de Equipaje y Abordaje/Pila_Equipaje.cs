public class Pila_Equipaje
{

    private Nodo_Equipaje tope; 
    private int contador;
    private List<Equipaje> listaEquipajes;

    public Nodo_Equipaje Tope { get => tope; set => tope = value; } 
    public int Contador { get => contador; set => contador = value; }

        
    public Pila_Equipaje()
    {
        listaEquipajes = new List<Equipaje>();
    }

    public void AgregarEquipaje(Equipaje equipaje)
    {
        listaEquipajes.Add(equipaje);
    }

    public Equipaje BuscarEquipajePorId(string idEquipaje)
    {
        return listaEquipajes.Find(e => e.IdEquipaje == idEquipaje);
    }

    public List<Equipaje> ObtenerTodosEquipajes()
    {
        return listaEquipajes;
    }

    public void EliminarEquipajePorId(string idEquipaje)
    {
        var equipajeAEliminar = listaEquipajes.FirstOrDefault(e => e.IdEquipaje == idEquipaje);
        if (equipajeAEliminar != null)
        {
            listaEquipajes.Remove(equipajeAEliminar);
        }
    }
}
