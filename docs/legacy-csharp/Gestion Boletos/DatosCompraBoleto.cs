public class DatosCompraBoleto
{
    private string estacionPartida;
    private string estacionLlegada;
    private string horarioSalida;
    private TipoBoletoEnum tipoBoleto;
    private string idTrenSeleccionado;
    private double precioCalculado;
    private string rutaPreseleccionadaTexto;
    private string idUsuarioLoggeado;


    public string EstacionPartida { get => estacionPartida; set => estacionPartida = value; }
    public string EstacionLlegada { get => estacionLlegada; set => estacionLlegada = value; }
    public string HorarioSalida { get => horarioSalida; set => horarioSalida = value; }
    public TipoBoletoEnum TipoBoleto { get => tipoBoleto; set => tipoBoleto = value; }
    public string IdTrenSeleccionado { get => idTrenSeleccionado; set => idTrenSeleccionado = value; }
    public double PrecioCalculado { get => precioCalculado; set => precioCalculado = value; }
    public string RutaPreseleccionadaTexto { get => rutaPreseleccionadaTexto; set => rutaPreseleccionadaTexto = value; }
    public string IdUsuarioLoggeado { get => idUsuarioLoggeado; set => idUsuarioLoggeado = value; }



}