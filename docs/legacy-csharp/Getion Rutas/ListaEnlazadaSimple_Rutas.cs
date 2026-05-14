 public class ListaEnlazadaSimple_Rutas {

     private NodoDeRuta cabeza;

     public NodoDeRuta Cabeza { get => cabeza; set => cabeza = value; }

     public ListaEnlazadaSimple_Rutas()
     {
         cabeza = null; 
     }


     public void addRuta(Ruta ruta)
     {

         NodoDeRuta nuevaRuta = new NodoDeRuta(ruta);


         if (cabeza == null)
         {
             cabeza = nuevaRuta;
             return; 
         }

         NodoDeRuta actual = cabeza;
         while (actual.Siguiente != null)
         {
             actual = actual.Siguiente;
         }

         actual.Siguiente = nuevaRuta;
     }


     public void mostrarRutas()
     {
         if (cabeza == null)
         {
             Console.WriteLine($"La lista de rutas esta vacia.");
             return;
         }

         Console.WriteLine("--- Rutas Disponibles ---"); 
         NodoDeRuta actual = cabeza;
         while (actual != null)
         {

             Console.WriteLine(actual.Ruta.ToString()); 
             Console.WriteLine("-----------------------------------------------------------------------");



             actual = actual.Siguiente;
         }
         Console.WriteLine("--- Fin Rutas Disponibles ---");
     }


     public Ruta BuscarRuta(string idRuta) 
     {
         if (cabeza == null)
         {
 
             return null; 
         }

         NodoDeRuta actual = cabeza;
         while (actual != null)
         {

             if (actual.Ruta != null && actual.Ruta.IdRuta != null && actual.Ruta.IdRuta.Equals(idRuta, StringComparison.OrdinalIgnoreCase))
             {
                 return actual.Ruta;
             }
             actual = actual.Siguiente;
         }

         return null; 
     }


     public List<Ruta> GetAllRutas()
     {
         List<Ruta> rutas = new List<Ruta>();
         NodoDeRuta actual = cabeza;
         while (actual != null)
         {
             if (actual.Ruta != null)
             {
                 rutas.Add(actual.Ruta);
             }
             actual = actual.Siguiente;
         }
         return rutas;
     }

     public List<Ruta> GetAllRutasRecomendadas()
     {
         List<Ruta> todasRutas = new List<Ruta>();

         NodoDeRuta actual = Cabeza; 

         while (actual != null)
         {

             if (actual.Ruta != null)
             {
                 todasRutas.Add(actual.Ruta);
             }
             actual = actual.Siguiente; 
         }

         return todasRutas; 
     }
 }