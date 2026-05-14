public class GrafoEstacion {

    private List<NodoDeEstacion_Grafo> estaciones;
    private List<Estacion> estaciones_2;
    private int[,] matrizAdyacencia;
    private ListaEnlazadaSimple_Rutas listaRutas = new ListaEnlazadaSimple_Rutas();

    public List<NodoDeEstacion_Grafo> Estaciones { get => estaciones; set => estaciones = value; }
    public int[,] MatrizAdyacencia { get => matrizAdyacencia; set => matrizAdyacencia = value; }
    public ListaEnlazadaSimple_Rutas ListaRutas { get => listaRutas; set => listaRutas = value; }
    public List<Estacion> Estaciones_2 { get => estaciones_2; set => estaciones_2 = value; }

    public GrafoEstacion(int cantidadDeEstaciones)
    {
        estaciones = new List<NodoDeEstacion_Grafo>(cantidadDeEstaciones);
        matrizAdyacencia = new int[cantidadDeEstaciones, cantidadDeEstaciones];

        for (int i = 0; i < cantidadDeEstaciones; i++)
        {
            for (int j = 0; j < cantidadDeEstaciones; j++)
            {
                matrizAdyacencia[i, j] = int.MaxValue; 
            }
        }
    }

    public void AddEstacion(Estacion estacion)
    {
        if (estacion != null)
        {
            estaciones.Add(new NodoDeEstacion_Grafo(estacion));
        }
    }

    public List<Estacion> GetAllEstaciones()
    {
        return Estaciones_2; 
    }


    public void AddRutaNoDirigida(int origen, int destino, int distancia)
    {
        if (origen >= 0 && origen < estaciones.Count && destino >= 0 && destino < estaciones.Count && distancia >= 0)
        {
            estaciones[origen].AgregarArista(destino, distancia);
            estaciones[destino].AgregarArista(origen, distancia);
            matrizAdyacencia[origen, destino] = distancia;
            matrizAdyacencia[destino, origen] = distancia;

        }
        else
        {
            Console.WriteLine("Error al añadir ruta no dirigida: Índices o distancia inválidos.");
        }
    }

    public void AddRutaDirigida(int origen, int destino, int distancia)
    {
        if (origen >= 0 && origen < estaciones.Count && destino >= 0 && destino < estaciones.Count && distancia >= 0)
        {
            estaciones[origen].AgregarArista(destino, distancia);
            matrizAdyacencia[origen, destino] = distancia;

        }
        else
        {
            Console.WriteLine("Error al añadir ruta dirigida: Índices o distancia inválidos.");
        }
    }

    public void MostrarRuta(int[] previo, int destino)
    {
        if (destino == -1)
        {
            return;
        }

        MostrarRuta(previo, previo[destino]);

        Console.Write($"{estaciones[destino].Estacion.NombreEstacion[0]} ");
    }

    public void MostrarEstaciones()
    {
        Console.WriteLine("--- Estaciones en el Grafo ---");
        for (int i = 0; i < estaciones.Count; i++)
        {
            if (estaciones[i] != null && estaciones[i].Estacion != null && estaciones[i].Estacion.NombreEstacion != null && estaciones[i].Estacion.NombreEstacion.Length > 0)
            {
                Console.WriteLine($"[{i}] {estaciones[i].Estacion.NombreEstacion[0]}");
            }
            else
            {
                Console.WriteLine($"[{i}] Estación inválida");
            }
        }
        Console.WriteLine("------------------------------");
    }

    public int CalcularDistancia(int origen, int destino)
    {
        if (origen >= 0 && origen < estaciones.Count && destino >= 0 && destino < estaciones.Count)
        {
            return matrizAdyacencia[origen, destino];
        }
        return int.MaxValue; 
    }

    public void MostrarMatriz()
    {
        Console.WriteLine("--- Matriz de distancias ---");
        Console.Write("      ");
        for (int j = 0; j < estaciones.Count; j++)
        {
            if (estaciones[j] != null && estaciones[j].Estacion != null && estaciones[j].Estacion.NombreEstacion != null && estaciones[j].Estacion.NombreEstacion.Length > 0)
            {
                Console.Write(estaciones[j].Estacion.NombreEstacion[0].PadLeft(5));
            }
            else
            {
                Console.Write(" ? ".PadLeft(5));
            }
        }
        Console.WriteLine();

        for (int i = 0; i < estaciones.Count; i++)
        {
            if (estaciones[i] != null && estaciones[i].Estacion != null && estaciones[i].Estacion.NombreEstacion != null && estaciones[i].Estacion.NombreEstacion.Length > 0)
            {
                Console.Write(estaciones[i].Estacion.NombreEstacion[0].PadLeft(5) + " ");
            }
            else
            {
                Console.Write(" ? ".PadLeft(5) + " ");
            }


            for (int j = 0; j < estaciones.Count; j++)
            {
                string val = matrizAdyacencia[i, j] == int.MaxValue ? "INF" : matrizAdyacencia[i, j].ToString();
                Console.Write(val.PadLeft(5));
            }
            Console.WriteLine();
        }
        Console.WriteLine("----------------------------");
    }

    public string BuscarRutaDisponible(int origen, int destino)
    {
        if (origen >= 0 && origen < estaciones.Count && destino >= 0 && destino < estaciones.Count)
        {
            int distancia = matrizAdyacencia[origen, destino];
            string nombreOrigen = (estaciones[origen]?.Estacion?.NombreEstacion != null && estaciones[origen].Estacion.NombreEstacion.Length > 0) ? estaciones[origen].Estacion.NombreEstacion[0] : "Origen Desconocido";
            string nombreDestino = (estaciones[destino]?.Estacion?.NombreEstacion != null && estaciones[destino].Estacion.NombreEstacion.Length > 0) ? estaciones[destino].Estacion.NombreEstacion[0] : "Destino Desconocido";

            return distancia == int.MaxValue
                ? $"No hay ruta disponible entre {nombreOrigen} y {nombreDestino}."
                : $"Ruta disponible entre {nombreOrigen} y {nombreDestino}: {distancia} km";
        }
        return "Error al buscar ruta: Índices de estación inválidos.";
    }


    public Estacion BuscarEstacionPorNombre(string nombre)
    {
        if (string.IsNullOrEmpty(nombre))
        {
            return null;
        }

        foreach (var nodoGrafo in estaciones)
        {
            if (nodoGrafo != null && nodoGrafo.Estacion != null && nodoGrafo.Estacion.NombreEstacion != null && nodoGrafo.Estacion.NombreEstacion.Length > 0)
            {
                if (nodoGrafo.Estacion.NombreEstacion[0].Equals(nombre, StringComparison.OrdinalIgnoreCase))
                {
                    return nodoGrafo.Estacion;
                }
            }
        }
        return null; 
    }


    public void ModificarRutasHorarios(string idRuta)
    {
        NodoDeRuta actual = listaRutas.Cabeza;
        while (actual != null)
        {
            if (actual.Ruta.IdRuta == idRuta)
            {
                Console.Write("Nueva fecha de salida: ");
                actual.Ruta.FechaHoraDeSalida = Console.ReadLine();

                Console.Write("Nueva fecha de llegada: ");
                actual.Ruta.FechaHoraDeLlegada = Console.ReadLine();

                Console.WriteLine(" Ruta actualizada correctamente.");
                return;
            }
            actual = actual.Siguiente;
        }
        Console.WriteLine(" Ruta no encontrada.");
    }

    public void CalcularRutasCortas_Dijkstra(int origen)
    {
        int n = estaciones.Count;
        int[] dist = new int[n];
        bool[] visitado = new bool[n];
        int[] previo = new int[n];

        for (int i = 0; i < n; i++)
        {
            dist[i] = int.MaxValue;
            visitado[i] = false;
            previo[i] = -1;
        }

        dist[origen] = 0;

        for (int i = 0; i < n - 1; i++)
        {
            int u = -1;
            for (int j = 0; j < n; j++)
            {
                if (!visitado[j] && (u == -1 || dist[j] < dist[u]))
                {
                    u = j;
                }
            }

            if (dist[u] == int.MaxValue)
                break;

            visitado[u] = true;

            for (int v = 0; v < n; v++)
            {
                if (matrizAdyacencia[u, v] != int.MaxValue && dist[u] + matrizAdyacencia[u, v] < dist[v])
                {
                    dist[v] = dist[u] + matrizAdyacencia[u, v];
                    previo[v] = u;
                }
            }
        }

        Console.WriteLine("Distancias mínimas desde el nodo " + estaciones[origen].Estacion.NombreEstacion[0] + ":");
        for (int i = 0; i < n; i++)
        {
            string nombre = estaciones[i].Estacion.NombreEstacion[0];
            string resultado = dist[i] == int.MaxValue ? "INF" : dist[i].ToString();
            Console.WriteLine($"A {nombre}: {resultado} km");
        }


        for (int i = 0; i < n; i++)
        {
            if (dist[i] != int.MaxValue)
            {
                Console.Write($"Ruta más corta a {estaciones[i].Estacion.NombreEstacion[0]}: ");
                MostrarRuta(previo, i);
                Console.WriteLine($" - Distancia: {dist[i]} km");
            }
        }
    }

    public string SolicitarRuta(string idRuta)
    {
        NodoDeRuta actual = listaRutas.Cabeza;
        while (actual != null)
        {
            if (actual.Ruta.IdRuta == idRuta)
            {
                return $"Ruta encontrada:\nSalida: {actual.Ruta.FechaHoraDeSalida} - Estación: {actual.Ruta.EstacionDePartida.NombreEstacion[0]}\nLlegada: {actual.Ruta.FechaHoraDeLlegada} - Estación: {actual.Ruta.EstacionDeLlegada.NombreEstacion[0]}";
            }
            actual = actual.Siguiente;
        }
        return " Ruta no encontrada.";
    }

    public void CambiarRuta()
    {
        Console.Write("ID de la ruta que desea cambiar: ");
        string idRuta = Console.ReadLine();

        NodoDeRuta actual = listaRutas.Cabeza;
        while (actual != null)
        {
            if (actual.Ruta.IdRuta == idRuta)
            {
                Console.Write("Nueva estación de partida (nombre): ");
                string nuevaSalida = Console.ReadLine();

                Console.Write("Nueva estación de llegada (nombre): ");
                string nuevaLlegada = Console.ReadLine();

                actual.Ruta.EstacionDePartida = new Estacion(new string[] { nuevaSalida });
                actual.Ruta.EstacionDeLlegada = new Estacion(new string[] { nuevaLlegada });

                Console.WriteLine(" Ruta cambiada correctamente.");
                return;
            }
            actual = actual.Siguiente;
        }
        Console.WriteLine(" Ruta no encontrada.");
    }

}