package co.edu.upb.trenes.models.usuarios;

public class Usuario {
    private String id;
    private String usuario;
    private String contrasena;
    private RolUsuario rol;
    private String nombre;
    private boolean activo;

    public Usuario() {
    }

    public Usuario(String id, String usuario, String contrasena, RolUsuario rol, String nombre, boolean activo) {
        this.id = id;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.rol = rol;
        this.nombre = nombre;
        this.activo = activo;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
    public RolUsuario getRol() { return rol; }
    public void setRol(RolUsuario rol) { this.rol = rol; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}
