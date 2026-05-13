package co.edu.upb.trenes.models.config;

public class ConfiguracionSistema {
    private double valorBasePorKm = 320.0;
    private double kilometrajeMaximoOperacion = 100000.0;

    public ConfiguracionSistema() {
    }

    public double getValorBasePorKm() { return valorBasePorKm; }
    public void setValorBasePorKm(double valorBasePorKm) { this.valorBasePorKm = valorBasePorKm; }
    public double getKilometrajeMaximoOperacion() { return kilometrajeMaximoOperacion; }
    public void setKilometrajeMaximoOperacion(double kilometrajeMaximoOperacion) { this.kilometrajeMaximoOperacion = kilometrajeMaximoOperacion; }
}
