package co.edu.upb.trenes.models.usuarios;

public class Pasajero extends Usuario {
    private String documento;
    private String telefono;

    public Pasajero() {
        setRol(RolUsuario.PASAJERO);
    }

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}
