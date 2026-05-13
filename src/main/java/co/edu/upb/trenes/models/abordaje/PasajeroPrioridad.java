package co.edu.upb.trenes.models.abordaje;

import co.edu.upb.trenes.models.boletos.TipoBoleto;

public class PasajeroPrioridad {
    private String pasajeroId;
    private String nombre;
    private TipoBoleto tipoBoleto;
    private int numeroVagon;

    public PasajeroPrioridad() {
    }

    public PasajeroPrioridad(String pasajeroId, String nombre, TipoBoleto tipoBoleto, int numeroVagon) {
        this.pasajeroId = pasajeroId;
        this.nombre = nombre;
        this.tipoBoleto = tipoBoleto;
        this.numeroVagon = numeroVagon;
    }

    public int prioridad() {
        int categoria = switch (tipoBoleto) {
            case PREMIUM -> 0;
            case EJECUTIVO -> 1;
            case ESTANDAR -> 2;
        };
        return categoria * 1000 - numeroVagon;
    }

    public String getPasajeroId() { return pasajeroId; }
    public void setPasajeroId(String pasajeroId) { this.pasajeroId = pasajeroId; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public TipoBoleto getTipoBoleto() { return tipoBoleto; }
    public void setTipoBoleto(TipoBoleto tipoBoleto) { this.tipoBoleto = tipoBoleto; }
    public int getNumeroVagon() { return numeroVagon; }
    public void setNumeroVagon(int numeroVagon) { this.numeroVagon = numeroVagon; }
}
