internal class Empleado {
 {
    private string UsuarioEmpleado;
    private string ContrasenaEmpleado;
    private string IdEmpleado;
    private bool TxEnProgreso;
    private Empleado UsuarioTx;
    private string Nombre;
    private string Apellido;

    public Empleado(string usuarioEmpleado, string contrasenaEmpleado, string idEmpleado, string nombre, string apellido)
    {
        this.UsuarioEmpleado = usuarioEmpleado;
        this.ContrasenaEmpleado = contrasenaEmpleado;
        this.IdEmpleado = idEmpleado;
        this.Nombre = nombre;
        this.Apellido = apellido;
        this.TxEnProgreso = false;
        this.UsuarioTx = null;
    }

    public string GetUsuarioEmpleado()
    {
        return UsuarioEmpleado;
    }

    public void SetUsuarioEmpleado(string usuarioEmpleado)
    {
        this.UsuarioEmpleado = usuarioEmpleado;
    }

    public string GetContrasenaEmpleado()
    {
        return ContrasenaEmpleado;
    }

    public void SetContrasenaEmpleado(string contrasenaEmpleado)
    {
        this.ContrasenaEmpleado = contrasenaEmpleado;
    }

    public string GetIdEmpleado()
    {
        return IdEmpleado;
    }

    public void SetIdEmpleado(string idEmpleado)
    {
        this.IdEmpleado = idEmpleado;
    }

    public bool IniciarSesion(string usuarioEmpleado, string contrasenaEmpleado)
    {
        if (this.UsuarioEmpleado == usuarioEmpleado && this.ContrasenaEmpleado == contrasenaEmpleado)
        {
            this.UsuarioTx = this;
            this.TxEnProgreso = true;
            Console.WriteLine($"Bienvenido, {this.Nombre}, {this.Apellido}. Has iniciado sesión correctamente.");
            return true;
        }
        return false;
    }
 }
}

