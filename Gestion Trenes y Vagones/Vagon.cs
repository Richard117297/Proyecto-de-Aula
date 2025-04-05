internal class Vagon
{

    private string idDelVagon;
    private int capacidadDeEquipaje;
    private int pesoLim;
    private int capacidadDePasajeros;
    private string tipoVagon;


    public string IdDelVagon { get => idDelVagon; set => idDelVagon = value; }
    public int CapacidadDeEquipaje { get => capacidadDeEquipaje; set => capacidadDeEquipaje = value; }
    public int PesoLim { get => pesoLim; set => pesoLim = value; }
    public int CapacidadDePasajeros { get => capacidadDePasajeros; set => capacidadDePasajeros = value; }
    public string TipoVagon { get => tipoVagon; set => tipoVagon = value; }


    public Vagon(string idDelVagon, int capacidadDeEquipaje, int pesoLim, int capacidadDePasajeros)
    {

        IdDelVagon = idDelVagon;
        CapacidadDeEquipaje = capacidadDeEquipaje;
        PesoLim = pesoLim;
        CapacidadDeEquipaje = capacidadDePasajeros;

    }


    public int calcularLugaresPorTipoBoleto(string tipoBoleto)
    {

        TipoBoletoEnum tipoEnum;

        switch (tipoBoleto.ToLower())
        {


            case "premium":
                tipoEnum = TipoBoletoEnum.Premium;
                break;

            case "ejecutivo":
                tipoEnum = TipoBoletoEnum.Ejecutivo;
                break;

            case "estandar":
                tipoEnum = TipoBoletoEnum.Estandar;
                break;

            default:
                throw new ArgumentException("Tipo de boleto no válido");
        }

        switch (tipoEnum)
        {

            case TipoBoletoEnum.Premium:
                return 4;

            case TipoBoletoEnum.Ejecutivo:
                return 8;

            case TipoBoletoEnum.Estandar:
                return 22;

            default:
                throw new ArgumentException("Tipo de boleto no válido");
        }
    }


    public void SeleccioneTipoVagon(int totalPasajeros, int totalCargaKg, Tren tipoTren)
    {
        int maxVagones = tipoTren.TipoTren.ToLower() == "mercedes-benz" ? 28 :
                         tipoTren.TipoTren.ToLower() == "arnold" ? 32 :
                         throw new ArgumentException("Tipo de tren no válido");

        int capacidadPasajerosPorVagon = this.CapacidadDePasajeros > 0 ? this.CapacidadDePasajeros : 40;

        int vagonesPasajeros = (int)Math.Ceiling((double)totalPasajeros / capacidadPasajerosPorVagon);

        int vagonesCarga = (int)Math.Ceiling(vagonesPasajeros / 2.0);

        int totalVagones = vagonesPasajeros + vagonesCarga;

        if (totalVagones > maxVagones)
        {

            Console.WriteLine($"No se pueden asignar {totalVagones} vagones. El tren tipo {tipoTren.TipoTren} solo permite hasta {maxVagones} vagones.");
            return;
        }

        Console.WriteLine($"\n Tipo tren: {tipoTren.TipoTren}");
        Console.WriteLine($"- Vagones de pasajeros requeridos: {vagonesPasajeros}");
        Console.WriteLine($"- Vagones de carga requeridos: {vagonesCarga}");
        Console.WriteLine($"- Total de vagones: {totalVagones}\n");


        for (int i = 1; i <= totalVagones; i++)
        {
            string tipoVagon = i <= vagonesPasajeros ? "Pasajeros" : "Carga";

            Console.WriteLine($"Vagón #{i}: {tipoVagon}");
        }
    }



}