internal class Boleto
{

    #region Atributos de la clase
    private string idRegistro;
    private string fechaHoraCompra;
    private string fechaHoraSalida;
    private string fechaHoraLlegada;
    private string idPasajero;
    private string nombrePasajero;
    private string apellidoPasajero;
    private string tipoIdentificación;
    private string numeroTelefono;
    private string lugar;
    private string direccion;
    private string idTren;
    private string nombreContacto;
    private string apellidoContacto;
    private string telefonoContacto;
    private string idVagonCarga;
    private string idEquipaje;
    private int pesoEquipaje;
    private int valorPasaje;
    private TipoBoletoEnum tipoDeBoleta;
    #endregion


    #region Getters y Setters
    public string IdRegistro { get => idRegistro; set => idRegistro = value; }
    public string FechaHoraCompra { get => fechaHoraCompra; set => fechaHoraCompra = value; }
    public string FechaHoraSalida { get => fechaHoraSalida; set => fechaHoraSalida = value; }
    public string FechaHoraLlegada { get => fechaHoraLlegada; set => fechaHoraLlegada = value; }
    public string IdPasajero { get => idPasajero; set => idPasajero = value; }
    public string NombrePasajero { get => nombrePasajero; set => nombrePasajero = value; }
    public string ApellidoPasajero { get => apellidoPasajero; set => apellidoPasajero = value; }
    public string TipoIdentificacion { get => tipoIdentificación; set => tipoIdentificación = value; }
    public string NumeroTelefono { get => numeroTelefono; set => numeroTelefono = value; }
    public string Lugar { get => lugar; set => lugar = value; }
    public string Direccion { get => direccion; set => direccion = value; }
    public string IdTren { get => idTren; set => idTren = value; }
    public string NombreContacto { get => nombreContacto; set => nombreContacto = value; }
    public string ApellidoContacto { get => apellidoContacto; set => apellidoContacto = value; }
    public string TelefonoContacto { get => telefonoContacto; set => telefonoContacto = value; }
    public string IdVagonCarga { get => idVagonCarga; set => idVagonCarga = value; }
    public string IdEquipaje { get => idEquipaje; set => idEquipaje = value; }
    public int PesoEquipaje { get => pesoEquipaje; set => pesoEquipaje = value; }
    public int ValorPasaje { get => valorPasaje; set => valorPasaje = value; }
    public TipoBoletoEnum TipoDeBoleta { get => tipoDeBoleta; set => tipoDeBoleta = value; }
    #endregion


    #region Constructor
    public Boleto(string nombrePasajero, string apellidoPasajero, string tipoIdentificacion, string numeroTelefono, string lugar, string direccion, string nombreContacto, string apellidoContacto, string telefonoContacto, int pesoEquipaje, TipoBoletoEnum tipoDeBoleta)
    {

        IdRegistro = idRegistro;
        FechaHoraCompra = fechaHoraCompra;
        FechaHoraSalida = fechaHoraSalida;
        FechaHoraLlegada = fechaHoraLlegada;
        IdPasajero = idPasajero;
        NombrePasajero = nombrePasajero;
        ApellidoPasajero = apellidoPasajero;
        TipoIdentificacion = tipoIdentificación;
        NumeroTelefono = numeroTelefono;
        Lugar = lugar;
        Direccion = direccion;
        IdTren = idTren;
        NombreContacto = nombreContacto;
        ApellidoContacto = apellidoContacto;
        TelefonoContacto = telefonoContacto;
        IdVagonCarga = idVagonCarga;
        IdEquipaje = idEquipaje;
        PesoEquipaje = pesoEquipaje;
        ValorPasaje = valorPasaje;
        TipoDeBoleta = tipoDeBoleta;

    }
    #endregion
}