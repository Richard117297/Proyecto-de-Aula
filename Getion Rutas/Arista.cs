public class Arista {

    private int destino;
    private int distancia;
    

    public int Destino { get => destino; set => destino = value; }
    public int Distancia { get => distancia; set => distancia = value; }
    

    public Arista(int destino, int distancia) {

        Destino = destino;
        Distancia = distancia;
        
    }

    public string toString() {

        return "Arista [Destino: " + destino + ", Distancia: " + distancia + " km]";
    }


}