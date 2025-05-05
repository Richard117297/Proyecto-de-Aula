public class Equipaje 
{

    private string idEquipaje; 
    private Boleto boleto;
    private int peso;
    private int cantidad;

    public string IdEquipaje { get => idEquipaje; set => idEquipaje = value; }
    public Boleto Boleto { get => boleto; set => boleto = value; }
    public int Peso { get => peso; set => peso = value; }
    public int Cantidad { get => cantidad; set => cantidad = value; }

    public Equipaje(string idEquipaje, Boleto boleto, int peso, int cantidad)
    {
        IdEquipaje = idEquipaje;
        Boleto = boleto;
        Peso = peso;
        Cantidad = cantidad;
    }

    public bool ControlEquipaje(string idEquipaje, int peso, int cantidad)
    {
        const int pesoMaximoMaleta = 80;
        const int maxMaletasPorPasajero = 2;

        if (idEquipaje != Boleto.IdEquipaje)
        {
            return false;
        }

        if (peso <= 0 || peso > pesoMaximoMaleta || cantidad <= 0 || cantidad > maxMaletasPorPasajero)
        {
            return false;
        }

        Peso = peso;
        Cantidad = cantidad;
        return true;
    }

    public string GetInfoCompleta()
    {
        return $"ID Equipaje: {IdEquipaje}\n" +
               $"Peso: {Peso} kg\n" +
               $"Cantidad: {Cantidad} maleta(s)\n" +
               $"Boleto Asociado: {Boleto?.IdRegistro ?? "N/A"}";
    }

    private static string rutaArchivoEquipajes = @"Base de datos JSON\Equipajes.json";

    public static void GuardarDatos(string rutaArchivo, List<Equipaje> equipajes)
    {
        JsonHelper.GuardarDatos(rutaArchivo, equipajes);
    }

    public static List<Equipaje> CargarDatos(string rutaArchivo)
    {
        return JsonHelper.CargarDatos<Equipaje>(rutaArchivo);
    }
}



}