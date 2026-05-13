package co.edu.upb.trenes.models.boletos;

public class NodoBoleto {
    private Boleto boleto;
    private NodoBoleto siguiente;

    public NodoBoleto(Boleto boleto) {
        this.boleto = boleto;
    }

    public Boleto getBoleto() { return boleto; }
    public NodoBoleto getSiguiente() { return siguiente; }
    public void setSiguiente(NodoBoleto siguiente) { this.siguiente = siguiente; }
}
