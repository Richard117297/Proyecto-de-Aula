public class NodoDeEstacion_Grafo {

    private Estacion estacion;
    private List<Arista> adyacentes;

    public Estacion Estacion { get => estacion; set => estacion = value; }
    public List<Arista> Adyacentes { get => adyacentes; set => adyacentes = value; }

    public NodoDeEstacion_Grafo(Estacion estacion)
    {
        this.estacion = estacion;
        adyacentes = new List<Arista>();
    }

    public void AgregarArista(int destino, int distancia)
    {
        adyacentes.Add(new Arista(destino, distancia));
    }

    public override string ToString()
    {
        return $"Estación: {Estacion.NombreEstacion[0]}, Conexiones: {Adyacentes.Count}";
    }


}