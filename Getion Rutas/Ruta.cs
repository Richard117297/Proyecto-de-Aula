internal class Ruta
{

    private string fechaHoraDeSalida;
    private Estacion estacionDePartida;
    private string fechaHoraDeLlegada;
    private Estacion estacionDeLlegada;
    private string idRuta;

    public Estacion EstacionDePartida { get => estacionDePartida; set => estacionDePartida = value; }
    public Estacion EstacionDeLlegada { get => estacionDeLlegada; set => estacionDeLlegada = value; }
    public string FechaHoraDeSalida { get => fechaHoraDeSalida; set => idRuta = value; }
    public string FechaHoraDeLlegada { get => fechaHoraDeLlegada; set => fechaHoraDeLlegada = value; }
    public string IdRuta { get => idRuta; set => idRuta = value; }


    public Ruta(string fechaHoraDeSalida, string fechaHoraDeLlegada, string idRuta)
    {

        FechaHoraDeSalida = fechaHoraDeSalida;
        FechaHoraDeLlegada = fechaHoraDeLlegada;
        IdRuta = idRuta;

    }



}