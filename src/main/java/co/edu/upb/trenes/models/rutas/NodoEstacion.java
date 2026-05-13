package co.edu.upb.trenes.models.rutas;

import java.util.HashMap;
import java.util.Map;

public class NodoEstacion {
    private Estacion estacion;
    private Map<String, Integer> distancias = new HashMap<>();

    public NodoEstacion(Estacion estacion) {
        this.estacion = estacion;
    }

    public Estacion getEstacion() { return estacion; }
    public Map<String, Integer> getDistancias() { return distancias; }
}
