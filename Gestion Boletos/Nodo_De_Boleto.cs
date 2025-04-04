internal class Nodo_De_Boleto
{

    private Boleto boleto;
    private Nodo_De_Boleto siguiente;

    public Boleto Boleto { get => boleto; set => boleto = value; }
    public Nodo_De_Boleto Siguiente { get => siguiente; set => siguiente = value; }

    public Nodo_De_Boleto(Boleto boleto)
    {

        Boleto = boleto;
        siguiente = null;


    }

}