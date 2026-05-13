package co.edu.upb.trenes.models.trenes;

public class ResultadoCalculoVagones {
    private int vagonesPasajeros;
    private int vagonesCarga;
    private int totalVagones;
    private boolean cumpleCapacidad;
    private String mensaje;

    public ResultadoCalculoVagones() {
    }

    public ResultadoCalculoVagones(int vagonesPasajeros, int vagonesCarga, int totalVagones, boolean cumpleCapacidad, String mensaje) {
        this.vagonesPasajeros = vagonesPasajeros;
        this.vagonesCarga = vagonesCarga;
        this.totalVagones = totalVagones;
        this.cumpleCapacidad = cumpleCapacidad;
        this.mensaje = mensaje;
    }

    public int getVagonesPasajeros() { return vagonesPasajeros; }
    public void setVagonesPasajeros(int vagonesPasajeros) { this.vagonesPasajeros = vagonesPasajeros; }
    public int getVagonesCarga() { return vagonesCarga; }
    public void setVagonesCarga(int vagonesCarga) { this.vagonesCarga = vagonesCarga; }
    public int getTotalVagones() { return totalVagones; }
    public void setTotalVagones(int totalVagones) { this.totalVagones = totalVagones; }
    public boolean isCumpleCapacidad() { return cumpleCapacidad; }
    public void setCumpleCapacidad(boolean cumpleCapacidad) { this.cumpleCapacidad = cumpleCapacidad; }
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
}
