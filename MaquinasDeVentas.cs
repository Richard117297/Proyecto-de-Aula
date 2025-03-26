internal class MaquinasDeVentas {

private ListaEnlazadaSimple_Boletos listaBoletos;

public MaquinasDeVentas()
{

    listaBoletos = new ListaEnlazadaSimple_Boletos();
}

public void VenderBoleto()
{


    Console.WriteLine("Ingrese los datos del pasajero:");
    Console.Write("Nombre: ");
    string nombrePasajero = Console.ReadLine();
    Console.Write("Apellido: ");
    string apellidoPasajero = Console.ReadLine();
    Console.Write("ID: ");
    string idPasajero = Console.ReadLine();
    Console.Write("Teléfono: ");
    string numeroTelefono = Console.ReadLine();
    Console.Write("Tipo de identificación: ");
    string tipoIdentificacion = Console.ReadLine();

    Console.WriteLine("Ingrese los datos del contacto de emergencia:");
    Console.Write("Nombre: ");
    string nombreContacto = Console.ReadLine();
    Console.Write("Apellido: ");
    string apellidoContacto = Console.ReadLine();
    Console.Write("Teléfono: ");
    string telefonoContacto = Console.ReadLine();

    Console.WriteLine("Ingrese los datos del boleto:");
    Console.Write("ID de registro del boleto: ");
    string idRegistro = Console.ReadLine();
    Console.Write("Fecha y hora de compra: ");
    string fechaHoraCompra = Console.ReadLine();
    Console.Write("Hora de salida: ");
    string fechaHoraSalida = Console.ReadLine();
    Console.Write("Hora de llegada: ");
    string fechaHoraLlegada = Console.ReadLine();
    Console.Write("Lugar de salida: ");
    string lugar = Console.ReadLine();
    Console.Write("Dirección: ");
    string direccion = Console.ReadLine();
    Console.Write("ID del tren: ");
    string idTren = Console.ReadLine();
    Console.Write("ID del vagón de carga: ");
    string idVagonCarga = Console.ReadLine();
    Console.Write("ID del equipaje: ");
    string idEquipaje = Console.ReadLine();
    Console.Write("Peso del equipaje (kg): ");
    int pesoEquipaje = int.Parse(Console.ReadLine());
    Console.Write("Valor del pasaje: ");
    int valorPasaje = int.Parse(Console.ReadLine());
    Console.Write("Tipo de boleto: ");
    TipoBoleto tipoBoleto = (TipoBoleto)Enum.Parse(typeof(TipoBoleto), Console.ReadLine(), true);


    Boleto boleto = new Boleto(idRegistro, fechaHoraCompra, fechaHoraSalida, fechaHoraLlegada, idPasajero, nombrePasajero, apellidoPasajero, tipoIdentificacion, numeroTelefono, lugar, direccion, idTren, nombreContacto, apellidoContacto, telefonoContacto, idVagonCarga, idEquipaje, pesoEquipaje, valorPasaje, tipoBoleto);

    listaBoletos.AddBoleto(boleto);
    Console.WriteLine("Boleto vendido exitosamente.");
}

public void EliminarBoleto()
{
    Console.Write("Ingrese el ID de registro del boleto que desea eliminar: ");
    string idRegistro = Console.ReadLine();
    listaBoletos.EliminarBoleto(idRegistro);
    Console.WriteLine("Boleto eliminado si existía");
}

public void MostrarBoletos()
{
    Console.WriteLine("--- Boletos Registrados ---");
    listaBoletos.MostrarListaDeLosBoletos();
    Console.WriteLine();
}


}