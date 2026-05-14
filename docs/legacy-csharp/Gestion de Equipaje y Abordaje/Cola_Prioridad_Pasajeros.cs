public class Cola_Prioridad_Pasajeros 
{

    private Pasajeros_Prioridad inicio;

    public Pasajeros_Prioridad Inicio { get => inicio; set => inicio = value; }

    public Cola_Prioridad_Pasajeros() {

        inicio = null;
    }


    public int ObtenerPrioridadDesdeEnum(TipoBoletoEnum tipo) {


        switch (tipo)
        {
            case TipoBoletoEnum.Premium:
                return 1;

            case TipoBoletoEnum.Ejecutivo:
                return 2;

            case TipoBoletoEnum.Estandar:
                return 3;

            default:
                return int.MaxValue;
        }
    }

        
    public void Encolar(Pasajero pasajero, Boleto boleto) {

        int prioridad = ObtenerPrioridadDesdeEnum(boleto.TipoDeBoleta);

        Pasajeros_Prioridad nuevo = new Pasajeros_Prioridad(pasajero, prioridad, boleto);

        if (inicio == null || prioridad < inicio.Prioridad) {

            nuevo.Siguiente = inicio;
            inicio = nuevo;

        }
        else {

            Pasajeros_Prioridad actual = inicio;
            while (actual.Siguiente != null && actual.Siguiente.Prioridad <= prioridad)
            {
                actual = actual.Siguiente;
            }
            nuevo.Siguiente = actual.Siguiente;
            actual.Siguiente = nuevo;

        }
    }


    public void Desencolar_Abordaje() {

        if (inicio != null) {

            Pasajero p = inicio.Pasajero;
            Boleto b = inicio.TipoBoleto;

            Console.WriteLine($" Abordando: {b.NombrePasajero} {b.ApellidoPasajero} - Tipo: {b.TipoDeBoleta} - Prioridad: {ObtenerPrioridadDesdeEnum(b.TipoDeBoleta)}");

            inicio = inicio.Siguiente;
        }

        else  {

            Console.WriteLine(" No hay pasajeros en la cola.");
        }
    }


    public void MostrarCola() {

        if (inicio == null) {

            Console.WriteLine(" La cola está vacía.");
            return;
        }

        Console.WriteLine(" Lista de pasajeros en cola (ordenados por prioridad):");
        Pasajeros_Prioridad actual = inicio;

        while (actual != null){

            Boleto b = actual.TipoBoleto;
            Console.WriteLine($" {b.NombrePasajero} {b.ApellidoPasajero} - Tipo: {b.TipoDeBoleta} - Prioridad: {actual.Prioridad}");
            actual = actual.Siguiente;

        }
    }


    public void Peek() {


        if (inicio != null) {

            Boleto b = inicio.TipoBoleto;
            Console.WriteLine($" Próximo en abordar: {b.NombrePasajero} {b.ApellidoPasajero} - Tipo: {b.TipoDeBoleta}");

        }
        else {

            Console.WriteLine(" La cola está vacía.");
        }
    }


    public void BuscarPasajero(Pasajero buscado){

        Pasajeros_Prioridad actual = inicio;

        while (actual != null) {

            if (actual.Pasajero.Equals(buscado)){

                Console.WriteLine($" Pasajero encontrado: {actual.TipoBoleto.NombrePasajero} {actual.TipoBoleto.ApellidoPasajero}");
                return;

            }
            actual = actual.Siguiente;
        }
        Console.WriteLine(" Pasajero no encontrado en la cola.");
    }

        
    public int CantidadDePasajeros() {

        int contador = 0;
        Pasajeros_Prioridad actual = inicio;

        while (actual != null) {

            contador++;
            actual = actual.Siguiente;
        }
        return contador;
    }
}
