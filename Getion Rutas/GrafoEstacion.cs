internal class GrafoEstacion {

    private NodoDeEstacion_Grafo estacion;
    private int cantidadDeEstaciones;

    public NodoDeEstacion_Grafo Estacion { get => estacion; set => estacion = value; }

    public int CantidadDeEstaciones { get => cantidadDeEstaciones; set => cantidadDeEstaciones = value; }

    public GrafoEstacion(int cantidadDeEstaciones) {

        Estacion = null;
        CantidadDeEstaciones = cantidadDeEstaciones;
    }

    public void CalcularDistanciaDeEstaciones()
    {
        // Implementación del calculo de distancia
    }

    public void AddEstacion(NodoDeEstacion_Grafo estacion)
    {
        this.estacion = estacion;
    }

    public void MostrarEstaciones()
    {
        // Implementacion para mostrar estaciones
    }

    public void AddRuta(NodoDeEstacion_Grafo estacion)
    {
        // Implementacion para agregar una ruta
    }

    public string BuscarRutaDisponible(string idRuta)
    {
        // Implementacion para buscar ruta disponible
        return "Ruta encontrada"; // Retorno de ejemplo
    }

    public void ModificarRutasHorarios(string idRuta)
    {
        // Implementación para modificar rutas y horarios
    }

    public void ConsultarRutasCortas()
    {
        // Implementacion para consultar rutas cortas
    }

    public string SolicitarRuta(string idRuta)
    {
        // Implementacion para solicitar una ruta
        return "Ruta solicitada"; // Retorno de ejemplo
    }      

    public void CambiarRuta()
    {
        // Implementacion para cambiar ruta
    }
    
}