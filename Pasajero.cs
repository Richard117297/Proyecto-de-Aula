internal class Pasajero {

      private string nombre;
        private string apellido;
        private string id;
        private int telefono;
        private string usuarioPasajero;
        private string contrasenaPasajero;
        private string documento;
        private bool txEnProgreso;
        private Pasajero usuarioTx;

        public string Nombre { get => nombre; set => nombre = value; }
        public string Apellido { get => apellido; set => apellido = value; }
        public string Id { get => id; set => id = value; }
        public int Telefono { get => telefono; set => telefono = value; }
        public string UsuarioPasajero { get => usuarioPasajero; set => usuarioPasajero = value; }
        public string ContrasenaPasajero { get => contrasenaPasajero; set => contrasenaPasajero = value; }
        public string Documento { get => documento; set => documento = value; }
        public bool TxEnProgreso { get => txEnProgreso; set => txEnProgreso = value; }
        public Pasajero UsuarioTx { get => usuarioTx; set => usuarioTx = value; }

        public Pasajero(string nombre, string apellido, string id, int telefono, string usuarioPasajero, string contrasenaPasajero, string documento)
        {
            this.nombre = nombre;
            this.apellido = apellido;
            this.id = id;
            this.telefono = telefono;
            this.usuarioPasajero = usuarioPasajero;
            this.contrasenaPasajero = contrasenaPasajero;
            this.documento = documento;
            this.txEnProgreso = false;
            this.usuarioTx = null;
        }

      
        public bool IniciarSesion(string usuario_pasajero, string contrasena_pasajero)
        {
            if (this.usuarioPasajero == usuario_pasajero && this.contrasenaPasajero == contrasena_pasajero)
            {
                this.usuarioTx = this;
                this.txEnProgreso = true;
                return true;
            }
            return false;
        }
    }









