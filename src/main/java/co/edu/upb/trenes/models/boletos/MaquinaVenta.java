package co.edu.upb.trenes.models.boletos;

public class MaquinaVenta {
    private String id;
    private String estacionId;
    private boolean activa = true;

    public MaquinaVenta() {
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getEstacionId() { return estacionId; }
    public void setEstacionId(String estacionId) { this.estacionId = estacionId; }
    public boolean isActiva() { return activa; }
    public void setActiva(boolean activa) { this.activa = activa; }
}
