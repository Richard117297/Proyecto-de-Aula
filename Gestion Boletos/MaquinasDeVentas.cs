internal class MaquinasDeVentas {

private string idMaquinaDeVenta;
private string ubicacion;
private ListaEnlazadaSimple_Boletos listaBoletos;
private TipoBoleto EscojerTipo;

public string IdMaquinaDeVenta { get => idMaquinaDeVenta; set => idMaquinaDeVenta = value; }
public string Ubicacion { get => ubicacion; set => ubicacion = value; }

public MaquinasDeVentas(string idMaquinaDeVenta, string ubicacion)
{
    listaBoletos = new ListaEnlazadaSimple_Boletos();
    IdMaquinaDeVenta = idMaquinaDeVenta;
    Ubicacion = ubicacion;
}

public void VenderBoleto()
{
    Console.WriteLine("Ingrese los datos del pasajero:");
    Console.Write("Nombre: ");
    string nombrePasajero = Console.ReadLine();
    Console.Write("Apellido: ");
    string apellidoPasajero = Console.ReadLine();
    Console.Write("Tipo de identificación: ");
    string tipoIdentificacion = Console.ReadLine();
    Console.Write("Teléfono: ");
    string numeroTelefono = Console.ReadLine();

    Console.WriteLine("Ingrese los datos del contacto de emergencia:");
    Console.Write("Nombre: ");
    string nombreContacto = Console.ReadLine();
    Console.Write("Apellido: ");
    string apellidoContacto = Console.ReadLine();
    Console.Write("Teléfono: ");
    string telefonoContacto = Console.ReadLine();

    Console.WriteLine("Ingrese los datos del boleto:");
    Console.Write("Lugar de salida: ");
    string lugar = Console.ReadLine();
    Console.Write("Dirección: ");
    string direccion = Console.ReadLine();

    int pesoEquipaje;
    while (true)
    {
        Console.Write("Peso del equipaje con su respectiva limitación (kg): ");
        if (int.TryParse(Console.ReadLine(), out pesoEquipaje) && pesoEquipaje >= 0) {

            break;
        }
        Console.WriteLine("Hay un error digistaste un caracter o un valor negativo: Ingrese un valor numérico válido para el peso del equipaje.");
    }



    Console.Write("Tipo de boleto (Premium, Ejecutivo, Estandar): ");
    TipoBoletoEnum tipoBoleto = EscojerTipo.SeleccionarTipoBoleto(Console.ReadLine());

    
    Boleto boleto = new Boleto(nombrePasajero, apellidoPasajero, tipoIdentificacion, numeroTelefono, lugar, direccion, nombreContacto, apellidoContacto, telefonoContacto, pesoEquipaje, tipoBoleto);


    if (listaBoletos == null) {

        listaBoletos = new ListaEnlazadaSimple_Boletos();
    }

    listaBoletos.AddBoleto(boleto);
    
    Console.Clear();
    Console.WriteLine("Boleto vendido exitosamente.\n");

    Console.WriteLine($"ID de Registro: {boleto.IdRegistro}");
    Console.WriteLine($"Fecha de Compra: {boleto.FechaHoraCompra}");
    Console.WriteLine($"Hora de Salida: {boleto.FechaHoraSalida}");
    Console.WriteLine($"Hora de Llegada: {boleto.FechaHoraLlegada}");
    Console.WriteLine($"ID del pasajero: {boleto.IdPasajero}");
    Console.WriteLine($"ID del Tren: {boleto.IdTren}");
    Console.WriteLine($"ID del vagon de carga: {boleto.IdVagonCarga}");
    Console.WriteLine($"ID del Equipaje: {boleto.IdEquipaje}");
    Console.WriteLine($"Nombre del pasajero: {boleto.NombrePasajero}");
    Console.WriteLine($"Apellido del pasajero: {boleto.ApellidoPasajero}");
    Console.WriteLine($"Tipo de identificación: {boleto.TipoIdentificacion}");
    Console.WriteLine($"Numero de telefono: {boleto.NumeroTelefono}");
    Console.WriteLine($"Lugar de salida: {boleto.Lugar}");
    Console.WriteLine($"Dirección: {boleto.Direccion}");
    Console.WriteLine($"Nombre del contacto: {boleto.NombreContacto}");
    Console.WriteLine($"Apellido del contacto: {boleto.ApellidoContacto}");
    Console.WriteLine($"Telefono del contacto: {boleto.TelefonoContacto}");
    Console.WriteLine($"Peso del equipaje: {boleto.PesoEquipaje}");
    Console.WriteLine($"El valor del pasaje: {boleto.ValorPasaje} ");
    Console.WriteLine($"Tipo del boleto: {boleto.TipoDeBoleta}");


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

}