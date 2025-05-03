public class Administrador {

    private string usuarioAdmin;
    private string contrasenaAdmin;
    private string idAdmin;
    private bool txEnProgreso;
    private Administrador usuarioTx;

    public string UsuarioAdmin { get => usuarioAdmin; set => usuarioAdmin = value; }
    public string ContrasenaAdmin { get => contrasenaAdmin; set => contrasenaAdmin = value; }
    public string IdAdmin { get => idAdmin; set => idAdmin = value; }
    public bool TxEnProgreso { get => txEnProgreso; set => txEnProgreso = value; }
    public Administrador UsuarioTx { get => usuarioTx; set => usuarioTx = value; }


    public Administrador(string usuarioAdmin, string contrasenaAdmin, string idAdmin)
    {
        UsuarioAdmin = usuarioAdmin;
        ContrasenaAdmin = contrasenaAdmin;
        IdAdmin = idAdmin;
        TxEnProgreso = false;
        UsuarioTx = null;
    }


    public bool IniciarSesion(string usuario_admin, string contrasena_admin)
    {
        if (this.UsuarioAdmin == usuario_admin && this.ContrasenaAdmin == contrasena_admin)
        {
            this.TxEnProgreso = true;
            Console.WriteLine($"Bienvenido, administrador {this.UsuarioAdmin}. Has iniciado sesión correctamente.");
            return true;
        }
        return false;
    }

}











