internal class Tren
{
    private string nombre;
    private string idTren;
    private int kilometraje;
    private int capacidadVagones;
    private string tipoTren;

    public string Nombre { get => nombre; set => nombre = value; }
    public string IdTren { get => idTren; set => idTren = value; }
    public int Kilometraje { get => kilometraje; set => kilometraje = value; }
    public int CapacidadVagones { get => capacidadVagones; set => capacidadVagones = value; }
    public string TipoTren { get => tipoTren; set => tipoTren = value; }


    public Tren(string nombre, string idTren, int capacidadDeCarga, int kilometraje)
    {

        Nombre = nombre;
        IdTren = idTren;
        Kilometraje = kilometraje;

    }


    public void AsignarTipoTren(string tipo)
    {


        if (tipo.ToLower() == "arnold")
        {

            TipoTren = "Arnold";
            CapacidadVagones = 32;

        }

        else if (tipo.ToLower() == "mercedez-benz" || tipo.ToLower() == "mercedes-benz")
        {

            TipoTren = "Mercedez-Benz";
            CapacidadVagones = 28;

        }

        else
        {

            throw new ArgumentException("Tipo de tren no válido. Usa 'Arnold' o 'Mercedez-Benz'.");

        }
    }

    public void CalcularCantidadVagones()
    {

        Console.WriteLine($"Este tren es tipo {TipoTren} y tiene {CapacidadVagones} vagones.");

    }

    public void calcularCantidadPasajeros()
    {

        //Por hacer

    }

    public void calcularKilometrajeUsado()
    {
        //Por hacer
    }

}