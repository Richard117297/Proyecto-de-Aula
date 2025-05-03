public class Boleto {

    private string idRegistro;
    private string fechaHoraCompra;
    private string fechaHoraSalida;
    private string fechaHoraLlegada;
    private string idPasajero;
    private string nombrePasajero;
    private string apellidoPasajero;
    private string tipoIdentificación;
    private string numeroTelefono;
    private string estacionOrigen;
    private string estacionFinal;
    private string idTren;
    private string nombreContacto;
    private string apellidoContacto;
    private string telefonoContacto;
    private string idVagonCarga;
    private string idEquipaje;
    private int pesoEquipaje;
    private int cantidadEquipaje;
    private double valorPasaje; 
    private TipoBoletoEnum tipoDeBoleta;
    private int numeroAsiento; 


    public string IdRegistro { get => idRegistro; set => idRegistro = value; }
    public string FechaHoraCompra { get => fechaHoraCompra; set => fechaHoraCompra = value; }
    public string FechaHoraSalida { get => fechaHoraSalida; set => fechaHoraSalida = value; }
    public string FechaHoraLlegada { get => fechaHoraLlegada; set => fechaHoraLlegada = value; }
    public string IdPasajero { get => idPasajero; set => idPasajero = value; }
    public string NombrePasajero { get => nombrePasajero; set => nombrePasajero = value; }
    public string ApellidoPasajero { get => apellidoPasajero; set => apellidoPasajero = value; }
    public string TipoIdentificacion { get => tipoIdentificación; set => tipoIdentificación = value; }
    public string NumeroTelefono { get => numeroTelefono; set => numeroTelefono = value; }
    public string EstacionOrigen { get => estacionOrigen; set => estacionOrigen = value; }
    public string EstacionFinal { get => estacionFinal; set => estacionFinal = value; }
    public string IdTren { get => idTren; set => idTren = value; }
    public string NombreContacto { get => nombreContacto; set => nombreContacto = value; }
    public string ApellidoContacto { get => apellidoContacto; set => apellidoContacto = value; }
    public string TelefonoContacto { get => telefonoContacto; set => telefonoContacto = value; }
    public string IdVagonCarga { get => idVagonCarga; set => idVagonCarga = value; }
    public string IdEquipaje { get => idEquipaje; set => idEquipaje = value; }
    public int PesoEquipaje { get => pesoEquipaje; set => pesoEquipaje = value; }
    public int CantidadEquipaje { get => cantidadEquipaje; set => cantidadEquipaje = value; }
    public double ValorPasaje { get => valorPasaje; set => valorPasaje = value; } 
    public TipoBoletoEnum TipoDeBoleta { get => tipoDeBoleta; set => tipoDeBoleta = value; }
    public int NumeroAsiento { get => numeroAsiento; set => numeroAsiento = value; } 

    public bool EsValidado { get; set; } = false;
    public bool EsRegistrado { get; set; } = false;

    public Boleto()
    {
        IdRegistro = Guid.NewGuid().ToString();
        FechaHoraCompra = "";
        FechaHoraSalida = "";
        FechaHoraLlegada = "";
        EsValidado = false;
        EsRegistrado = false;
        TipoDeBoleta = TipoBoletoEnum.Desconocido;
        NumeroAsiento = 0; 
    }


    public Boleto(string nombrePasajero, string apellidoPasajero, string tipoIdentificacion, string numeroTelefono, string estacionOrigen, string estacionFinal, string nombreContacto, string apellidoContacto, string telefonoContacto, int pesoEquipaje, int cantidadEquipaje , TipoBoletoEnum tipoDeBoleta)
    {
        IdRegistro = Guid.NewGuid().ToString(); 
        NombrePasajero = nombrePasajero;
        ApellidoPasajero = apellidoPasajero;
        TipoIdentificacion = tipoIdentificacion;
        NumeroTelefono = numeroTelefono;
        EstacionOrigen = estacionOrigen;
        EstacionFinal = estacionFinal;
        NombreContacto = nombreContacto;
        ApellidoContacto = apellidoContacto;
        TelefonoContacto = telefonoContacto;
        PesoEquipaje = pesoEquipaje;
        CantidadEquipaje = cantidadEquipaje;
        TipoDeBoleta = tipoDeBoleta;

        FechaHoraCompra = "";
        FechaHoraSalida = "";
        FechaHoraLlegada = "";
        IdPasajero = ""; 
        IdTren = "";
        IdVagonCarga = "";
        IdEquipaje = "";
        ValorPasaje = 0;
        NumeroAsiento = 0;
        EsValidado = false;
        EsRegistrado = false;
    }

    public void MarcarComoRegistrado()
    {
        this.EsRegistrado = true;
    }

    public string GetInfoCompleta()
    {
        StringBuilder sb = new StringBuilder();
        sb.AppendLine($"ID de Registro: {IdRegistro ?? "N/A"}");
        sb.AppendLine($"Validado por Empleado: {(EsValidado ? "Sí" : "No")}");
        sb.AppendLine($"Registrado por Admin: {(EsRegistrado ? "Sí" : "No")}");
        sb.AppendLine($"--- Información del Boleto ---");
        sb.AppendLine($"Tipo de Boleto: {TipoDeBoleta}");
        sb.AppendLine($"Valor del Pasaje: {ValorPasaje:C2}"); 
        sb.AppendLine($"Fecha/Hora Compra: {FechaHoraCompra ?? "N/A"}");
        sb.AppendLine($"Fecha/Hora Salida: {FechaHoraSalida ?? "N/A"}");
        sb.AppendLine($"Fecha/Hora Llegada: {FechaHoraLlegada ?? "N/A"}");
        sb.AppendLine($"Estación Origen: {EstacionOrigen ?? "N/A"}");
        sb.AppendLine($"Estación Destino: {EstacionFinal ?? "N/A"}");
        sb.AppendLine($"ID Tren: {IdTren ?? "N/A"}");
        sb.AppendLine($"ID Vagón: {IdVagonCarga ?? "N/A"}"); 
        sb.AppendLine($"Número Asiento: {(NumeroAsiento > 0 ? NumeroAsiento.ToString() : "N/A")}"); 
        sb.AppendLine($"--- Información del Pasajero ---");
        sb.AppendLine($"ID Pasajero: {IdPasajero ?? "N/A"}");
        sb.AppendLine($"Nombre: {NombrePasajero ?? "N/A"}");
        sb.AppendLine($"Apellido: {ApellidoPasajero ?? "N/A"}");
        sb.AppendLine($"Tipo Identificación: {TipoIdentificacion ?? "N/A"}");
        sb.AppendLine($"Teléfono: {NumeroTelefono ?? "N/A"}");
        sb.AppendLine($"--- Información de Equipaje ---");
        sb.AppendLine($"ID Equipaje: {IdEquipaje ?? "N/A"}");
        sb.AppendLine($"Peso Equipaje: {PesoEquipaje} kg");
        sb.AppendLine($"Cantidad Equipaje: {CantidadEquipaje} maleta(s)");
        sb.AppendLine($"--- Contacto de Emergencia ---");
        sb.AppendLine($"Nombre Contacto: {NombreContacto ?? "N/A"}");
        sb.AppendLine($"Apellido Contacto: {ApellidoContacto ?? "N/A"}");
        sb.AppendLine($"Teléfono Contacto: {TelefonoContacto ?? "N/A"}");

        return sb.ToString();
    }

    public static void GuardarDatos(string rutaArchivo, List<Boleto> boletos)
    {
        JsonHelper.GuardarDatos(rutaArchivo, boletos);
    }

    public static List<Boleto> CargarDatos(string rutaArchivo)
    {
        return JsonHelper.CargarDatos<Boleto>(rutaArchivo);
    }
}