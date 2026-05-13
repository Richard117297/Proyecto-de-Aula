package co.edu.upb.trenes.models.rutas;

public class Estacion {
    private String id;
    private String nombre;
    private String ciudad;

    public Estacion() {
    }

    public Estacion(String id, String nombre, String ciudad) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
}
