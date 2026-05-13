 public class MaquinasDeVentas {

     private string idMaquinaDeVenta;
     private Estacion ubicacion;
     private ListaEnlazadaSimple_Boletos listaBoletos;
     private TipoBoleto EscojerTipo = new TipoBoleto(); 
     private ListaEnlazadaSimple_Rutas listaRutas = new ListaEnlazadaSimple_Rutas(); 


     public string IdMaquinaDeVenta { get => idMaquinaDeVenta; set => idMaquinaDeVenta = value; }

     public Estacion Ubicacion { get => ubicacion; set => ubicacion = value; }

     public MaquinasDeVentas(string idMaquinaDeVenta, Estacion ubicacion)
     {
         listaBoletos = new ListaEnlazadaSimple_Boletos();
         IdMaquinaDeVenta = idMaquinaDeVenta;
         Ubicacion = ubicacion;
     }

     public void EliminarBoletoPorId()
     {
         Console.Write("Ingrese el ID de registro del boleto que desea eliminar: ");
         string idRegistro = Console.ReadLine();
         listaBoletos.EliminarBoleto(idRegistro);
         Console.WriteLine("Boleto eliminado si existía.");
     }

     public void MostrarBoletos()
     {
         Console.WriteLine("--- Boletos Registrados ---");
         listaBoletos.MostrarListaDeLosBoletos();
         Console.WriteLine();
     }

     public int CalcularComprasPorEstacion()
     {
         int contador = 0;
         Nodo_De_Boleto actual = listaBoletos.Cabeza;

         while (actual != null)
         {
             if (actual.Boleto.EstacionOrigen.Equals(Ubicacion))
                 contador++;
             actual = actual.Siguiente;
         }

         return contador;
     }

     public Dictionary<TipoBoletoEnum, int> CalcularComprasPorTipoBoleto()
     {

         return listaBoletos.ContarBoletosPorTipo();
     }


     public bool ConsultarRutas_HorariosDisponibles(string fechaHoraSalida, string fechaHoraLlegada)
     {
         NodoDeRuta actual = listaRutas.Cabeza;

         while (actual != null)
         {
             if (actual.Ruta.FechaHoraDeSalida == fechaHoraSalida && actual.Ruta.FechaHoraDeLlegada == fechaHoraLlegada)
             {
                 return true;
             }
             actual = actual.Siguiente;
         }

         return false;
     }

     public void SeleccionarRutaRecomendada(string fechaHoraSalida, string fechaHoraLlegada, string lugar)
     {
         NodoDeRuta actual = listaRutas.Cabeza;

         Console.WriteLine("Rutas recomendadas:");
         while (actual != null)
         {
             if (actual.Ruta.FechaHoraDeSalida == fechaHoraSalida && actual.Ruta.FechaHoraDeLlegada == fechaHoraLlegada)
             {
                 Console.WriteLine($"ID Ruta: {actual.Ruta.IdRuta}, Desde: {actual.Ruta.EstacionDePartida.NombreEstacion[0]} hacia: {actual.Ruta.EstacionDeLlegada.NombreEstacion[0]}");
             }
             actual = actual.Siguiente;
         }
     }

 }