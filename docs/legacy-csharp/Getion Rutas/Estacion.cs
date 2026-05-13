public class Estacion {


    private string[] nombreEstacion;
    private Tren tren;
    private MaquinasDeVentas maquinaDeVenta;


    public string[] NombreEstacion { get => nombreEstacion; set => nombreEstacion = value; }
    [JsonIgnore]
    public MaquinasDeVentas MaquinaDeVenta { get => maquinaDeVenta; set => maquinaDeVenta = value; }
    [JsonIgnore]
    public Tren Tren { get => tren; set => tren = value; }
    public string NombreSimple { get; set; }

    public Estacion()
    {
        this.nombreEstacion = new string[] { "Estación Desconocida" };
        this.tren = null;
        this.maquinaDeVenta = null;
    }


    public Estacion(string[] nombreEstacion)
    {
        this.nombreEstacion = nombreEstacion;
        this.tren = null;
        this.maquinaDeVenta = null;
    }

    public Estacion(string nombre)
    {
        this.nombreEstacion = new string[] { nombre };
        this.tren = null;
        this.maquinaDeVenta = null;
    }

    public string GetInfoCompleta()
    {
        StringBuilder sb = new StringBuilder();
        sb.AppendLine($"Nombre de la Estación: {NombreSimple}");
        sb.AppendLine($"Tren Asignado: {(Tren != null ? Tren.IdTren : "Ninguno")}");
        sb.AppendLine($"Máquina de Venta: {(MaquinaDeVenta != null ? "Disponible" : "No Disponible")}");
        return sb.ToString();
    }


    public string MostrarOrdenDeAbordaje(string idTren, Cola_Prioridad_Pasajeros colaPrioridad)
    {
        if (colaPrioridad == null || colaPrioridad.CantidadDePasajeros() == 0)
        {
            return "No hay pasajeros en cola para abordar.";
        }

        StringBuilder orden = new StringBuilder();
        orden.AppendLine($"=== ORDEN DE ABORDAJE (Tren ID: {idTren}) ===");

        
        while (colaPrioridad.CantidadDePasajeros() > 0)
        {
            Pasajeros_Prioridad siguiente = colaPrioridad.Inicio;
            Boleto boleto = siguiente.TipoBoleto;

            if (boleto.IdTren == idTren)
            {
                orden.AppendLine($"- {boleto.NombrePasajero} {boleto.ApellidoPasajero} (Prioridad: {siguiente.Prioridad})");
                colaPrioridad.Desencolar_Abordaje();

                
                 Tren.AgregarBoletoAlTren(boleto);
            }
            else
            {
                colaPrioridad.Desencolar_Abordaje(); 
            }
        }

        return orden.ToString();
    }

    
    public void CantidadDePasajerosPorEstacion()
    {
        if (MaquinaDeVenta == null)
        {
            Console.WriteLine(" No hay máquina de venta en esta estación.");
            return;
        }

        int cantidad = MaquinaDeVenta.CalcularComprasPorEstacion();
        Console.WriteLine($" Estación: {MaquinaDeVenta.Ubicacion.NombreEstacion[0]}");
        Console.WriteLine($" Total de pasajeros que compraron boletos en esta estación: {cantidad}");
    }

    
    public void CantidadDePasajerosPorTipoBoleto()
    {
        if (MaquinaDeVenta == null)
        {
            Console.WriteLine(" No hay máquina de venta en esta estación.");
            return;
        }

        Dictionary<TipoBoletoEnum, int> conteo = MaquinaDeVenta.CalcularComprasPorTipoBoleto();

        Console.WriteLine($" Estación: {MaquinaDeVenta.Ubicacion.NombreEstacion[0]}");
        Console.WriteLine(" Cantidad de pasajeros por tipo de boleto:");

        foreach (var par in conteo)
        {
            Console.WriteLine($" {par.Key}: {par.Value}");
        }
    }
}













}