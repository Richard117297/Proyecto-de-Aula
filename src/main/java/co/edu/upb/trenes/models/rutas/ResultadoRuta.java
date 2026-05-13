package co.edu.upb.trenes.models.rutas;

import java.util.ArrayList;
import java.util.List;

public class ResultadoRuta {
    private List<String> estaciones = new ArrayList<>();
    private int distanciaTotalKm;

    public ResultadoRuta() {
    }

    public ResultadoRuta(List<String> estaciones, int distanciaTotalKm) {
        this.estaciones = estaciones;
        this.distanciaTotalKm = distanciaTotalKm;
    }

    public String resumen() {
        return String.join(" -> ", estaciones) + " (" + distanciaTotalKm + " km)";
    }

    public List<String> getEstaciones() { return estaciones; }
    public void setEstaciones(List<String> estaciones) { this.estaciones = estaciones; }
    public int getDistanciaTotalKm() { return distanciaTotalKm; }
    public void setDistanciaTotalKm(int distanciaTotalKm) { this.distanciaTotalKm = distanciaTotalKm; }
}
