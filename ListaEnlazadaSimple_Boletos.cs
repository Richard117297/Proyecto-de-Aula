 internal class ListaEnlazadaSimple_Boletos {

     private Nodo_De_Boleto cabeza;

     public Nodo_De_Boleto Cabeza { get =>  cabeza; set => cabeza = value; }

     public ListaEnlazadaSimple_Boletos() {
         cabeza = null;
     }


     public void AddBoleto(Boleto boleto) {

         Nodo_De_Boleto nuevoBoleto = new Nodo_De_Boleto(boleto);

         if (cabeza == null) {
         
             cabeza = nuevoBoleto;
             return;
         
         }

             Nodo_De_Boleto actual = cabeza;

             while (actual.Siguiente != null) {
             
                 actual = actual.Siguiente;
             
             }
             actual.Siguiente = nuevoBoleto;
         
     }


     public void MostrarListaDeLosBoletos() {

         if (cabeza == null) {

             Console.WriteLine("La lista de boletos esta vacia.");
             return;
         }


             Nodo_De_Boleto actual = cabeza;

             while (actual != null) {

                 Console.WriteLine($"Boleto :  {actual.Boleto} ");
                 actual = actual.Siguiente;
             
             }
            
     }

     public void BuscarBoleto (Boleto idRegistro) {

         if (cabeza == null)
         {
             Console.WriteLine("La lista de boletos está vacía.");
             return;
         }

         Nodo_De_Boleto actual = cabeza; 

         while (actual != null)
         {
             if (actual.Boleto.IdRegistro.Equals(idRegistro)) 
             {
                 Console.WriteLine($"El boleto encontrado es: {actual.Boleto}");
                 return;
             }
             actual = actual.Siguiente;
         }

         Console.WriteLine("No se encontró el boleto.");
     }

     public bool validarBoleto(Boleto idRegistro)
     {
         if (cabeza == null) {

             Console.WriteLine("La lista de boletos esta vacia.");
             return false; 
         }

         Nodo_De_Boleto actual = cabeza;

         while (actual != null)
         {
             if (actual.Boleto.IdRegistro.Equals(idRegistro)) 
             {
                 Console.WriteLine($"El boleto con id : {actual.Boleto.IdRegistro}, ha sido validado.  ");
                 return true; 
             }
             actual = actual.Siguiente;
         }
         return false;
     }


     public int CalcularValorDelBoleto() {

         int precio = 0;



         return precio;
     }


 }