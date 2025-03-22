internal class ListaEnlazadaSimple_Boletos
{

    private Nodo_De_Boleto cabeza;

    public Nodo_De_Boleto Cabeza { get => cabeza; set => cabeza = value; }

    public ListaEnlazadaSimple_Boletos()
    {
        cabeza = null;
    }


    public void AddBoleto(Boleto boleto)
    {

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


        Nodo_De_Boleto actual = cabeza;

        while (actual != null)
        {

            Console.WriteLine($"Boleto con IdRegistro :  {actual.Boleto.IdRegistro} ");
            Console.WriteLine($" Fecha hora de compra :  {actual.Boleto.FechaHoraCompra}");
            Console.WriteLine($" Fecha hora de salida :  {actual.Boleto.FechaHoraSalida}");
            Console.WriteLine($" Fecha hora de llegada :  {actual.Boleto.FechaHoraLlegada}");
            Console.WriteLine($" Id del pasajero :  {actual.Boleto.IdPasajero}");
            Console.WriteLine($" Nombre del pasajero :  {actual.Boleto.NombrePasajero}");
            Console.WriteLine($" Apellido del pasajero :  {actual.Boleto.ApellidoPasajero}");
            Console.WriteLine($" Tipo de identificacion :  {actual.Boleto.TipoIdentificacion}");
            Console.WriteLine($" Numero de telefono :  {actual.Boleto.NumeroTelefono}");
            Console.WriteLine($" Lugar :  {actual.Boleto.Lugar}");
            Console.WriteLine($" Direccion :  {actual.Boleto.Direccion}");
            Console.WriteLine($" Id del tren :  {actual.Boleto.IdTren}");
            Console.WriteLine($" Nombre de contacto :  {actual.Boleto.NombreContacto}");
            Console.WriteLine($" Apellido de contacto :  {actual.Boleto.ApellidoContacto}");
            Console.WriteLine($" Telefono de contacto :  {actual.Boleto.TelefonoContacto}");
            Console.WriteLine($" Id de vagon de carga :  {actual.Boleto.IdVagonCarga}");
            Console.WriteLine($" Id del equipaje :  {actual.Boleto.IdEquipaje}");
            Console.WriteLine($" Peso del equipaje :  {actual.Boleto.PesoEquipaje}");
            Console.WriteLine($" Valor del pasaje :  {actual.Boleto.ValorPasaje}");
            Console.WriteLine($" El tipo de boleta :  {actual.Boleto.TipoDeBoleta}");
            Console.WriteLine("-----------------------------------------------------------------------");
            Console.WriteLine();

            actual = actual.Siguiente;

        }
    }

    public void BuscarBoleto(Boleto idRegistro)
    {

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
                Console.WriteLine($" Fecha hora de compra :  {actual.Boleto.FechaHoraCompra}");
                Console.WriteLine($" Fecha hora de salida :  {actual.Boleto.FechaHoraSalida}");
                Console.WriteLine($" Fecha hora de llegada :  {actual.Boleto.FechaHoraLlegada}");
                Console.WriteLine($" Id del pasajero :  {actual.Boleto.IdPasajero}");
                Console.WriteLine($" Nombre del pasajero :  {actual.Boleto.NombrePasajero}");
                Console.WriteLine($" Apellido del pasajero :  {actual.Boleto.ApellidoPasajero}");
                Console.WriteLine($" Tipo de identificacion :  {actual.Boleto.TipoIdentificacion}");
                Console.WriteLine($" Numero de telefono :  {actual.Boleto.NumeroTelefono}");
                Console.WriteLine($" Lugar :  {actual.Boleto.Lugar}");
                Console.WriteLine($" Direccion :  {actual.Boleto.Direccion}");
                Console.WriteLine($" Id del tren :  {actual.Boleto.IdTren}");
                Console.WriteLine($" Nombre de contacto :  {actual.Boleto.NombreContacto}");
                Console.WriteLine($" Apellido de contacto :  {actual.Boleto.ApellidoContacto}");
                Console.WriteLine($" Telefono de contacto :  {actual.Boleto.TelefonoContacto}");
                Console.WriteLine($" Id de vagon de carga :  {actual.Boleto.IdVagonCarga}");
                Console.WriteLine($" Id del equipaje :  {actual.Boleto.IdEquipaje}");
                Console.WriteLine($" Peso del equipaje :  {actual.Boleto.PesoEquipaje}");
                Console.WriteLine($" Valor del pasaje :  {actual.Boleto.ValorPasaje}");
                Console.WriteLine($" El tipo de boleta :  {actual.Boleto.TipoDeBoleta}");
                Console.WriteLine("-----------------------------------------------------------------------");
                Console.WriteLine();

                return;
            }
            actual = actual.Siguiente;
        }

        Console.WriteLine("No se encontró el boleto.");
    }

    public bool validarBoleto(Boleto idRegistro)
    {
        if (cabeza == null)
        {

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


    public int CalcularValorDelBoleto()
    {

        int precio = 0;



        return precio;
    }


}