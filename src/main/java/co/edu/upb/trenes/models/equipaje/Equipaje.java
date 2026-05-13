package co.edu.upb.trenes.models.equipaje;

import java.util.UUID;

public class Equipaje {
    private String id;
    private String pasajeroId;
    private String boletoId;
    private double pesoKg;
    private boolean entregado;
    private String vagonCargaId;
    private EstadoEquipaje estado = EstadoEquipaje.REGISTRADO;

    public Equipaje() {
    }

    public static Equipaje nuevo(String pasajeroId, String boletoId, double pesoKg) {
        Equipaje equipaje = new Equipaje();
        equipaje.id = "equipaje-" + UUID.randomUUID();
        equipaje.pasajeroId = pasajeroId;
        equipaje.boletoId = boletoId;
        equipaje.pesoKg = pesoKg;
        return equipaje;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getPasajeroId() { return pasajeroId; }
    public void setPasajeroId(String pasajeroId) { this.pasajeroId = pasajeroId; }
    public String getBoletoId() { return boletoId; }
    public void setBoletoId(String boletoId) { this.boletoId = boletoId; }
    public double getPesoKg() { return pesoKg; }
    public void setPesoKg(double pesoKg) { this.pesoKg = pesoKg; }
    public boolean isEntregado() { return entregado; }
    public void setEntregado(boolean entregado) { this.entregado = entregado; }
    public String getVagonCargaId() { return vagonCargaId; }
    public void setVagonCargaId(String vagonCargaId) { this.vagonCargaId = vagonCargaId; }
    public EstadoEquipaje getEstado() { return estado; }
    public void setEstado(EstadoEquipaje estado) { this.estado = estado; }
}
