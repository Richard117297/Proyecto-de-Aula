package co.edu.upb.trenes.models.usuarios;

public class Empleado extends Usuario {
    private String cargo;

    public Empleado() {
        setRol(RolUsuario.EMPLEADO);
    }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
}
