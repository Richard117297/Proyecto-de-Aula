package co.edu.upb.trenes.models.boletos;

public class Tarifa {
    private String id;
    private TipoBoleto categoria;
    private double valorBasePorKm;
    private double multiplicador;

    public Tarifa() {
    }

    public Tarifa(String id, TipoBoleto categoria, double valorBasePorKm, double multiplicador) {
        this.id = id;
        this.categoria = categoria;
        this.valorBasePorKm = valorBasePorKm;
        this.multiplicador = multiplicador;
    }

    public double calcular(int distanciaKm) {
        return distanciaKm * valorBasePorKm * multiplicador;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public TipoBoleto getCategoria() { return categoria; }
    public void setCategoria(TipoBoleto categoria) { this.categoria = categoria; }
    public double getValorBasePorKm() { return valorBasePorKm; }
    public void setValorBasePorKm(double valorBasePorKm) { this.valorBasePorKm = valorBasePorKm; }
    public double getMultiplicador() { return multiplicador; }
    public void setMultiplicador(double multiplicador) { this.multiplicador = multiplicador; }
}
