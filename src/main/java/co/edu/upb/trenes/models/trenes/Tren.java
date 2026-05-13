package co.edu.upb.trenes.models.trenes;

public class Tren {
    private String id;
    private String nombre;
    private TipoTren tipoTren;
    private boolean activo = true;
    private EstadoTren estado = EstadoTren.ACTIVO;
    private double kilometraje;
    private String rutaAsignadaId;
    private int vagonesPasajeros;
    private int vagonesCarga;

    public Tren() {
    }

    public Tren(String id, String nombre, TipoTren tipoTren) {
        this.id = id;
        this.nombre = nombre;
        this.tipoTren = tipoTren;
    }

    public int totalVagones() {
        return vagonesPasajeros + vagonesCarga;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public TipoTren getTipoTren() { return tipoTren; }
    public void setTipoTren(TipoTren tipoTren) { this.tipoTren = tipoTren; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
    public EstadoTren getEstado() { return estado; }
    public void setEstado(EstadoTren estado) { this.estado = estado; }
    public double getKilometraje() { return kilometraje; }
    public void setKilometraje(double kilometraje) { this.kilometraje = kilometraje; }
    public String getRutaAsignadaId() { return rutaAsignadaId; }
    public void setRutaAsignadaId(String rutaAsignadaId) { this.rutaAsignadaId = rutaAsignadaId; }
    public int getVagonesPasajeros() { return vagonesPasajeros; }
    public void setVagonesPasajeros(int vagonesPasajeros) { this.vagonesPasajeros = vagonesPasajeros; }
    public int getVagonesCarga() { return vagonesCarga; }
    public void setVagonesCarga(int vagonesCarga) { this.vagonesCarga = vagonesCarga; }
}
