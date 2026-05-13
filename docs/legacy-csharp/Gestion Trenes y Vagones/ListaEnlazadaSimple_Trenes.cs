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
            if (tren == null)
            {
                Console.WriteLine("No se puede añadir un tren nulo a la lista.");
                return;
            }

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

        public List<Tren> GetAllTrenes()
        {
            List<Tren> lista = new List<Tren>();
            NodoTren actual = cabeza;
            while (actual != null)
            {
                if (actual.Tren != null)
                {
                    lista.Add(actual.Tren);
                }
                actual = actual.Siguiente;
            }
            return lista;
        }

        public Tren BuscarTrenPorId(string idTren)
        {
            if (string.IsNullOrEmpty(idTren))
            {
                Console.WriteLine("El ID de tren no puede ser nulo o vacío para buscar.");
                return null;
            }

            if (cabeza == null)
            {
                Console.WriteLine("La lista de trenes está vacía. No se encontró el tren con el ID solicitado.");
                return null;
            }

            NodoTren actual = cabeza;
            while (actual != null)
            {
                if (actual.Tren != null && !string.IsNullOrEmpty(actual.Tren.IdTren) && actual.Tren.IdTren.Equals(idTren, StringComparison.OrdinalIgnoreCase))
                {
                    
                    return actual.Tren;
                }
                actual = actual.Siguiente;
            }

   
            return null;
        }

        public void mostrarTrenes()
        {
            if (cabeza == null)
            {
                Console.WriteLine("La lista de trenes está vacía.");
                return;
            }

            Console.WriteLine("--- Lista de Trenes ---");
            NodoTren actual = cabeza;
            while (actual != null)
            {
                if (actual.Tren != null)
                {
                    Console.WriteLine($" Información del tren :");
                    Console.WriteLine($" Nombre : {actual.Tren.Nombre} ");
                    Console.WriteLine($" Id del tren :  {actual.Tren.IdTren} ");
                    Console.WriteLine($" Kilometraje :  {actual.Tren.Kilometraje}  ");
                    Console.WriteLine($" Capacidad de vagones (total) :  {actual.Tren.CapacidadVagones}  ");
                    Console.WriteLine($" Tipo del tren :  {actual.Tren.TipoTren}  ");
                    Console.WriteLine($" Vagones Pasajeros: {actual.Tren.CantidadVagonesPasajeros}, Vagones Carga: {actual.Tren.CantidadVagonesCarga}, Pasajeros Max: {actual.Tren.CantidadPasajerosTotal}");
                    Console.WriteLine($" Ruta Asignada: {(actual.Tren.RutaAsignada != null ? actual.Tren.RutaAsignada.ToString() : "N/A")}");
                    Console.WriteLine("-----------------------------------------------------------");
                    Console.WriteLine("\n");
                }
                actual = actual.Siguiente;
            }
            Console.WriteLine("--- Fin de la Lista de Trenes ---");
        }

        public void DarDeBajaAlTren(string idTren)
        {
            if (string.IsNullOrEmpty(idTren))
            {
                Console.WriteLine("El ID de tren no puede ser nulo o vacío para dar de baja.");
                return;
            }

            if (cabeza == null)
            {
                Console.WriteLine("La lista está vacía, no se puede dar de baja.");
                return;
            }

            if (cabeza.Tren != null && !string.IsNullOrEmpty(cabeza.Tren.IdTren) && cabeza.Tren.IdTren.Equals(idTren, StringComparison.OrdinalIgnoreCase))
            {
                cabeza = cabeza.Siguiente;
                Console.WriteLine($"Tren con ID '{idTren}' dado de baja exitosamente.");
                return;
            }

            NodoTren actual = cabeza;

            while (actual.Siguiente != null && (actual.Siguiente.Tren == null || string.IsNullOrEmpty(actual.Siguiente.Tren.IdTren) || !actual.Siguiente.Tren.IdTren.Equals(idTren, StringComparison.OrdinalIgnoreCase)))
            {
                actual = actual.Siguiente;
            }

            if (actual.Siguiente != null && actual.Siguiente.Tren != null && !string.IsNullOrEmpty(actual.Siguiente.Tren.IdTren) && actual.Siguiente.Tren.IdTren.Equals(idTren, StringComparison.OrdinalIgnoreCase))
            {
                actual.Siguiente = actual.Siguiente.Siguiente;
                Console.WriteLine($"Tren con ID '{idTren}' dado de baja exitosamente.");
            }
            else
            {
                Console.WriteLine($"No se encontró un tren con el ID '{idTren}' para dar de baja.");
            }
        }

        public bool EstaVacia()
        {
            return cabeza == null;
        }



    }
}
