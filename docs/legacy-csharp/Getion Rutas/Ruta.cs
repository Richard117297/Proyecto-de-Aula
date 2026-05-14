public class Ruta
{


    private Estacion estacionDePartida;
    private Estacion estacionDeLlegada;
    private int distanciaKm; 
    private string fechaHoraDeSalida;
    private string fechaHoraDeLlegada; 
    private string idRuta;

    public Estacion EstacionDePartida { get => estacionDePartida; set => estacionDePartida = value; }
    public Estacion EstacionDeLlegada { get => estacionDeLlegada; set => estacionDeLlegada = value; }
    public int DistanciaKm { get => distanciaKm; set => distanciaKm = value; } 
    public string FechaHoraDeSalida { get => fechaHoraDeSalida; set => fechaHoraDeSalida = value; } 
    public string FechaHoraDeLlegada { get => fechaHoraDeLlegada; set => fechaHoraDeLlegada = value; }
    public string IdRuta { get => idRuta; set => idRuta = value; }


    
    public Ruta(Estacion estacionPartida, Estacion estacionLlegada, int distanciaKm, string fechaHoraDeSalida, string idRuta)
    {
        this.EstacionDePartida = estacionPartida;
        this.EstacionDeLlegada = estacionLlegada;
        this.DistanciaKm = distanciaKm;
        this.FechaHoraDeSalida = fechaHoraDeSalida;
        this.FechaHoraDeLlegada = fechaHoraDeLlegada; 
        this.IdRuta = idRuta;
        this.fechaHoraDeLlegada = "Hora de Llegada Pendiente";
    }

    public Ruta()
    {

    }

    public Ruta(string fechaHoraDeSalida, string fechaHoraDeLlegada, string idRuta)
    {
        FechaHoraDeSalida = fechaHoraDeSalida;
        FechaHoraDeLlegada = fechaHoraDeLlegada;
        IdRuta = idRuta;
        this.estacionDePartida = null;
        this.estacionDeLlegada = null;
        this.distanciaKm = 0;
    }

    public override string ToString()
    {

        string origen = EstacionDePartida != null && EstacionDePartida.NombreEstacion != null && EstacionDePartida.NombreEstacion.Length > 0 ? EstacionDePartida.NombreEstacion[0] : "Origen Desconocido";
        string destino = EstacionDeLlegada != null && EstacionDeLlegada.NombreEstacion != null && EstacionDeLlegada.NombreEstacion.Length > 0 ? EstacionDeLlegada.NombreEstacion[0] : "Destino Desconocido";

        string id = IdRuta ?? "ID Desconocido"; 

        return $"{origen} - {destino} (ID: {id}, Distancia: {DistanciaKm} km, Horario: {FechaHoraDeSalida})";
    }

    public string GetInfoCompleta()
    {
        StringBuilder sb = new StringBuilder();
        sb.AppendLine($"ID de Ruta: {IdRuta}");
        sb.AppendLine($"Estación de Partida: {EstacionDePartida}");
        sb.AppendLine($"Estación de Llegada: {EstacionDeLlegada}");
        sb.AppendLine($"Distancia: {DistanciaKm} km");
        sb.AppendLine($"Fecha/Hora de Salida: {FechaHoraDeSalida}");
        sb.AppendLine($"Fecha/Hora de Llegada: {FechaHoraDeLlegada}");
        return sb.ToString();
    }

    public static void GuardarDatos(string rutaArchivo, List<Ruta> rutas)
    {
        JsonHelper.GuardarDatos(rutaArchivo, rutas);
    }

    public static List<Ruta> CargarDatos(string rutaArchivo)
    {
        return JsonHelper.CargarDatos<Ruta>(rutaArchivo);
    }
}