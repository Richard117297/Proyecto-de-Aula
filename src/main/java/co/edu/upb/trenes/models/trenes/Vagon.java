package co.edu.upb.trenes.models.trenes;

public class Vagon {
    private String id;
    private String tipo;
    private int capacidadPasajeros;
    private double capacidadCargaKg;

    public Vagon() {
    }

    public Vagon(String id, String tipo, int capacidadPasajeros, double capacidadCargaKg) {
        this.id = id;
        this.tipo = tipo;
        this.capacidadPasajeros = capacidadPasajeros;
        this.capacidadCargaKg = capacidadCargaKg;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public int getCapacidadPasajeros() { return capacidadPasajeros; }
    public void setCapacidadPasajeros(int capacidadPasajeros) { this.capacidadPasajeros = capacidadPasajeros; }
    public double getCapacidadCargaKg() { return capacidadCargaKg; }
    public void setCapacidadCargaKg(double capacidadCargaKg) { this.capacidadCargaKg = capacidadCargaKg; }
}
