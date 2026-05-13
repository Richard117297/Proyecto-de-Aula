internal class Empleado {
 {
        private string usuario_Empleado;
        private string contraseña_Empleado;
        private string id_Empleado;
        private bool txEnProgreso;
        private Empleado usuarioTx;

        public string Usuario_Empleado { get => usuario_Empleado; set => usuario_Empleado = value; }
        public string Contraseña_Empleado { get => contraseña_Empleado; set => contraseña_Empleado = value; }
        public string Id_Empleado { get => id_Empleado; set => id_Empleado = value; }
        public bool TxEnProgreso { get => txEnProgreso; set => txEnProgreso = value; }
        public Empleado UsuarioTx { get => usuarioTx; set => usuarioTx = value; }

        public Empleado(string usuario_Empleado, string contraseña_Empleado, string id_Empleado)
        {
            this.usuario_Empleado = usuario_Empleado;
            this.contraseña_Empleado = contraseña_Empleado;
            this.id_Empleado = id_Empleado;
            this.txEnProgreso = false;
            this.usuarioTx = null;
        }

        public bool IniciarSesion(string usuario_Empleado, string contraseña_Empleado)
        {
            if (this.usuario_Empleado == usuario_Empleado && this.contraseña_Empleado == contraseña_Empleado)
            {
                Console.WriteLine($"Bienvenido Empleado con ID: {this.id_Empleado}. Has iniciado sesión correctamente.");
                this.txEnProgreso = true;
                this.usuarioTx = this;
                return true;
            }
            return false;
        }





    }
}

