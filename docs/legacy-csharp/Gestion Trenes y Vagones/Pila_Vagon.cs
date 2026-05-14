internal class Pila_Vagon
{

        private NodoVagon cabeza; 
        private int contador;

        public NodoVagon Cabeza { get => cabeza; set => cabeza = value; } 
        public int Contador { get => contador; set => contador = value; }

        public Pila_Vagon()
        {
            cabeza = null; 
            Contador = 0;
        }

        public void AddVagon(Vagon vagon)
        {
            if (vagon == null)
            {
                Console.WriteLine("No se puede añadir un vagón nulo a la lista.");
                return;
            }

            NodoVagon nuevoNodo = new NodoVagon(vagon);

            if (cabeza == null)
            {
                cabeza = nuevoNodo;
                contador++;
                return;
            }

            NodoVagon actual = cabeza;
            while (actual.Siguiente != null)
            {
                actual = actual.Siguiente;
            }
            actual.Siguiente = nuevoNodo;
            contador++;
            Console.WriteLine($"Vagón {vagon.IdDelVagon} añadido a la lista.");
        }


        public void push(Vagon vagon)
        {
            NodoVagon nuevoVagon = new NodoVagon(vagon);
            nuevoVagon.Siguiente = cabeza; 
            cabeza = nuevoVagon; 
            contador++;
            Console.WriteLine($"EL DATO {vagon.IdDelVagon} YA SE GUARDO.");
        }

        public Vagon peek() 
        {
            if (cabeza == null)
            {
                Console.WriteLine("La lista esta vacia");
                return null;
            }
            return cabeza.Vagon;
        }

        public Vagon pop() 
        {
            if (cabeza == null)
            {
                Console.WriteLine("La lista esta vacia");
                return null;
            }
            Vagon datoEliminado = cabeza.Vagon;
            cabeza = cabeza.Siguiente;
            contador--;
            Console.WriteLine($"El dato {datoEliminado.IdDelVagon} eliminado.");
            return datoEliminado;
        }


        public List<Vagon> GetAllVagones()
        {
            List<Vagon> lista = new List<Vagon>();
            NodoVagon actual = cabeza; 
            while (actual != null)
            {
                if (actual.Vagon != null) 
                {
                    lista.Add(actual.Vagon);
                }
                actual = actual.Siguiente;
            }
            return lista;
        }

        public Vagon BuscarVagonPorId(string idVagon)
        {
            if (string.IsNullOrEmpty(idVagon)) return null;

            NodoVagon actual = cabeza;
            while (actual != null)
            {
                if (actual.Vagon != null && actual.Vagon.IdDelVagon != null && actual.Vagon.IdDelVagon.Equals(idVagon, StringComparison.OrdinalIgnoreCase))
                {
                    return actual.Vagon;
                }
                actual = actual.Siguiente;
            }
            return null; 
        }

    }
