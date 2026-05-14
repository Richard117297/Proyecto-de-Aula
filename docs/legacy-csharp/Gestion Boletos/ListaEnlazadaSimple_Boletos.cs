 public class ListaEnlazadaSimple_Boletos {

     private Nodo_De_Boleto cabeza;
     private GrafoEstacion valor; 

     public Nodo_De_Boleto Cabeza { get => cabeza; set => cabeza = value; }
     public GrafoEstacion Valor { get => valor; set => valor = value; }

     public ListaEnlazadaSimple_Boletos()
     {
         cabeza = null;
         Valor = null;
     }

     public void AddBoleto(Boleto boleto)
     {
         if (boleto == null)
         {
             Console.WriteLine("No se puede añadir un boleto nulo a la lista.");
             return;
         }

         Nodo_De_Boleto nuevoBoleto = new Nodo_De_Boleto(boleto);

         if (cabeza == null)
         {
             cabeza = nuevoBoleto;
             return;
         }

         Nodo_De_Boleto actual = cabeza;
         while (actual.Siguiente != null)
         {
             actual = actual.Siguiente;
         }
         actual.Siguiente = nuevoBoleto;
     }

     public void MostrarListaDeLosBoletos()
     {
         if (cabeza == null)
         {
             Console.WriteLine("La lista de boletos esta vacia.");
             return;
         }

         Console.WriteLine("--- Lista de Boletos ---");
         Nodo_De_Boleto actual = cabeza;
         while (actual != null)
         {
             if (actual.Boleto != null)
             {
                 Console.WriteLine(actual.Boleto.GetInfoCompleta());
                 Console.WriteLine("-----------------------------------------------------------------------");
             }
             actual = actual.Siguiente;
         }
         Console.WriteLine("--- Fin de la Lista de Boletos ---");
     }

     public void EliminarBoleto(string idRegistro)
     {
         if (string.IsNullOrEmpty(idRegistro))
         {
             return;
         }

         if (cabeza == null)
         {
             return;
         }


         if (cabeza.Boleto != null && cabeza.Boleto.IdRegistro != null && cabeza.Boleto.IdRegistro.Equals(idRegistro, StringComparison.OrdinalIgnoreCase))
         {
             cabeza = cabeza.Siguiente; 
             return;
         }


         Nodo_De_Boleto actual = cabeza;
         while (actual.Siguiente != null && (actual.Siguiente.Boleto == null || actual.Siguiente.Boleto.IdRegistro == null || !actual.Siguiente.Boleto.IdRegistro.Equals(idRegistro, StringComparison.OrdinalIgnoreCase)))
         {
             actual = actual.Siguiente;
         }

         if (actual.Siguiente != null)
         {
             actual.Siguiente = actual.Siguiente.Siguiente;

         }

     }

     public bool ValidarBoletoPorId(string idRegistro)
     {
         Nodo_De_Boleto actual = Cabeza;

         while (actual != null)
         {

             if (actual.Boleto != null && actual.Boleto.IdRegistro == idRegistro)
             {

                 if (!actual.Boleto.EsValidado)
                 {
                     actual.Boleto.EsValidado = true; 
                                                      
                     return true; 
                 }
                 else
                 {
                     return false; 
                 }
             }
             actual = actual.Siguiente;
         }

         return false;
     }




     public Boleto BuscarBoletoPorId(string idRegistro)
     {
         if (string.IsNullOrEmpty(idRegistro))
         {
             Console.WriteLine("El ID de registro no puede ser nulo o vacío para buscar.");
             return null;
         }

         Nodo_De_Boleto actual = cabeza;
         while (actual != null)
         {
             if (actual.Boleto != null && actual.Boleto.IdRegistro != null && actual.Boleto.IdRegistro.Equals(idRegistro, StringComparison.OrdinalIgnoreCase))
             {
                 return actual.Boleto;
             }
             actual = actual.Siguiente;
         }
         Console.WriteLine($"No se encontró el boleto con id: {idRegistro}");
         return null;
     }

     public Boleto BuscarBoletoPorIdYUsuario(string idRegistro, string idUsuario)
     {

         Nodo_De_Boleto actual = Cabeza;

         while (actual != null)
         {
             if (actual.Boleto != null &&
                 actual.Boleto.IdRegistro == idRegistro &&
                 actual.Boleto.IdPasajero == idUsuario) 
             {
                 return actual.Boleto; 
             }
             actual = actual.Siguiente;
         }

         return null; 
     }

     public List<Boleto> GetBoletosPendientesDeRegistro()
     {
         List<Boleto> pendientes = new List<Boleto>();
         Nodo_De_Boleto actual = cabeza;
         while (actual != null)
         {
             if (actual.Boleto != null && actual.Boleto.EsValidado && !actual.Boleto.EsRegistrado)
             {
                 pendientes.Add(actual.Boleto);
             }
             actual = actual.Siguiente;
         }
         return pendientes;
     }

     public bool MarcarBoletoComoRegistrado(string idRegistro)
     {
         Boleto boletoARegistrar = BuscarBoletoPorId(idRegistro);

         if (boletoARegistrar != null)
         {
             boletoARegistrar.MarcarComoRegistrado(); ;
             Console.WriteLine($"El boleto con id : {idRegistro}, ha sido registrado por el admin.");
             return true;
         }
         else
         {
             Console.WriteLine($"No se encontró el boleto con id : {idRegistro} para registrar.");
             return false;
         }
     }




     public Dictionary<TipoBoletoEnum, int> ContarBoletosPorTipo()
     {
         Dictionary<TipoBoletoEnum, int> conteo = new Dictionary<TipoBoletoEnum, int>()
         {
             { TipoBoletoEnum.Premium, 0 },
             { TipoBoletoEnum.Ejecutivo, 0 },
             { TipoBoletoEnum.Estandar, 0 },
             { TipoBoletoEnum.Desconocido, 0 }
         };

         Nodo_De_Boleto actual = cabeza;
         while (actual != null)
         {
             if (actual.Boleto != null)
             {
                 if (Enum.IsDefined(typeof(TipoBoletoEnum), actual.Boleto.TipoDeBoleta))
                 {
                     conteo[actual.Boleto.TipoDeBoleta]++;
                 }
                 else
                 {
                     conteo[TipoBoletoEnum.Desconocido]++;
                 }
             }
             actual = actual.Siguiente;
         }

         return conteo;
     }

     public double CalcularPrecioBoleto(int origen, int destino)
     {
         if (Valor == null || Valor.MatrizAdyacencia == null || Valor.Estaciones == null || origen < 0 || destino < 0 || origen >= Valor.Estaciones.Count || destino >= Valor.Estaciones.Count)
         {
             Console.WriteLine("Error: Grafo o índices de estación inválidos para calcular precio.");
             return 0;
         }

         int distancia = Valor.MatrizAdyacencia[origen, destino];
         if (distancia == int.MaxValue)
         {
             Console.WriteLine($"No hay ruta directa disponible entre {Valor.Estaciones[origen].Estacion.NombreEstacion[0]} y {Valor.Estaciones[destino].Estacion.NombreEstacion[0]}.");
             return 0;
         }

         double precioBasePorKm = 5.0;
         double precio = precioBasePorKm * distancia;

         string nombreOrigen = (Valor.Estaciones[origen]?.Estacion?.NombreEstacion != null && Valor.Estaciones[origen].Estacion.NombreEstacion.Length > 0) ? Valor.Estaciones[origen].Estacion.NombreEstacion[0] : "Origen Desconocido";
         string nombreDestino = (Valor.Estaciones[destino]?.Estacion?.NombreEstacion != null && Valor.Estaciones[destino].Estacion.NombreEstacion.Length > 0) ? Valor.Estaciones[destino].Estacion.NombreEstacion[0] : "Destino Desconocido";

         Console.WriteLine($"La distancia entre {nombreOrigen} y {nombreDestino} es: {distancia} km.");
         Console.WriteLine($"El precio calculado para esta ruta es: ${precio}");

         return precio;
     }

     public List<Boleto> GetAllBoletos()
     {
         List<Boleto> todosBoletos = new List<Boleto>();
         Nodo_De_Boleto actual = cabeza;
         while (actual != null)
         {
             if (actual.Boleto != null)
             {
                 todosBoletos.Add(actual.Boleto);
             }
             actual = actual.Siguiente;
         }
         return todosBoletos;
     }

     public int Count
     {
         get
         {
             int count = 0;
             Nodo_De_Boleto actual = cabeza;
             while (actual != null)
             {
                 count++;
                 actual = actual.Siguiente;
             }
             return count;
         }
     }

     public bool IsEmpty
     {
         get { return cabeza == null; }
     }

     


 }