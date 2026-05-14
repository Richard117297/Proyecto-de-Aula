package co.edu.upb.trenes.models.rutas;

import java.time.LocalDateTime;

public class Ruta {
    private String id;
    private String origenId;
    private String destinoId;
    private String trenId;
    private LocalDateTime salida;
    private LocalDateTime llegada;
    private boolean iniciada;

    public Ruta() {
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getOrigenId() { return origenId; }
    public void setOrigenId(String origenId) { this.origenId = origenId; }
    public String getDestinoId() { return destinoId; }
    public void setDestinoId(String destinoId) { this.destinoId = destinoId; }
    public String getTrenId() { return trenId; }
    public void setTrenId(String trenId) { this.trenId = trenId; }
    public LocalDateTime getSalida() { return salida; }
    public void setSalida(LocalDateTime salida) { this.salida = salida; }
    public LocalDateTime getLlegada() { return llegada; }
    public void setLlegada(LocalDateTime llegada) { this.llegada = llegada; }
    public boolean isIniciada() { return iniciada; }
    public void setIniciada(boolean iniciada) { this.iniciada = iniciada; }
}
