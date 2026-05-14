package co.edu.upb.trenes.models.rutas;

public class ConexionEstacion {
    private String id;
    private String origenId;
    private String destinoId;
    private int distanciaKm;

    public ConexionEstacion() {
    }

    public ConexionEstacion(String id, String origenId, String destinoId, int distanciaKm) {
        this.id = id;
        this.origenId = origenId;
        this.destinoId = destinoId;
        this.distanciaKm = distanciaKm;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getOrigenId() { return origenId; }
    public void setOrigenId(String origenId) { this.origenId = origenId; }
    public String getDestinoId() { return destinoId; }
    public void setDestinoId(String destinoId) { this.destinoId = destinoId; }
    public int getDistanciaKm() { return distanciaKm; }
    public void setDistanciaKm(int distanciaKm) { this.distanciaKm = distanciaKm; }
}
