internal class Vagon
{
        private string idDelVagon;
        private int capacidadDeEquipajeKg;
        private int pesoLimKg;
        private int capacidadDePasajeros;
        private string tipoVagon; 
        private TipoBoletoEnum tipoBoleto;

        public string IdDelVagon { get => idDelVagon; set => idDelVagon = value; }
        public int CapacidadDeEquipajeKg { get => capacidadDeEquipajeKg; set => capacidadDeEquipajeKg = value; }
        public int PesoLimKg { get => pesoLimKg; set => pesoLimKg = value; }
        public int CapacidadDePasajeros { get => capacidadDePasajeros; set => capacidadDePasajeros = value; }
        public string TipoVagon { get => tipoVagon; set => tipoVagon = value; }
        public TipoBoletoEnum TipoBoleto { get => tipoBoleto; set => tipoBoleto = value; }

        public Vagon(string idDelVagon, int capacidadDeEquipajeKg, int pesoLimKg, int capacidadDePasajeros, TipoBoletoEnum tipoBoletoPasajero = TipoBoletoEnum.Desconocido)
        {
            IdDelVagon = idDelVagon;
            CapacidadDeEquipajeKg = capacidadDeEquipajeKg;
            PesoLimKg = pesoLimKg;
            CapacidadDePasajeros = capacidadDePasajeros;

            TipoVagon = (capacidadDePasajeros > 0) ? "Pasajeros" : "Carga";

            TipoBoleto = (TipoVagon == "Pasajeros") ? tipoBoletoPasajero : TipoBoletoEnum.Desconocido;
        }

        public Vagon() { }

        public static void GuardarDatos(string rutaArchivo, List<Vagon> vagones)
        {
            JsonHelper.GuardarDatos(rutaArchivo, vagones);
        }

        public static List<Vagon> CargarDatos(string rutaArchivo)
        {
            return JsonHelper.CargarDatos<Vagon>(rutaArchivo);
        }

        public string GetInfoCompleta()
        {
            StringBuilder sb = new StringBuilder();
            sb.AppendLine($"ID del Vagón: {IdDelVagon}");
            sb.AppendLine($"Tipo de Vagón: {TipoVagon}");
            sb.AppendLine($"Capacidad de Equipaje (kg): {CapacidadDeEquipajeKg}");
            sb.AppendLine($"Peso Límite (kg): {PesoLimKg}");
            sb.AppendLine($"Capacidad de Pasajeros: {CapacidadDePasajeros}");
            sb.AppendLine($"Tipo de Boleto: {TipoBoleto}");
            return sb.ToString();
        }


        public int GetCapacidadPorTipoBoleto()
        {
            if (TipoVagon != "Pasajeros" || CapacidadDePasajeros <= 0)
            {
                return 0;
            }

            switch (TipoBoleto)
            {
                case TipoBoletoEnum.Premium: return 4;
                case TipoBoletoEnum.Ejecutivo: return 8; 
                case TipoBoletoEnum.Estandar: return 22; 
                default: return 0;
            }
        }

        public void SeleccioneTipoVagon(int totalPasajeros, int totalCargaKg, Tren tipoTren)
        {
            int maxVagones = tipoTren.TipoTren.ToLower() == "mercedes-benz" ? 28 :
                             tipoTren.TipoTren.ToLower() == "arnold" ? 32 :
                             throw new ArgumentException("Tipo de tren no válido");

            int capacidadPasajerosPorVagon = this.CapacidadDePasajeros > 0 ? this.CapacidadDePasajeros : 40;

            int vagonesPasajeros = (int)Math.Ceiling((double)totalPasajeros / capacidadPasajerosPorVagon);

            int vagonesCarga = (int)Math.Ceiling(vagonesPasajeros / 2.0);

            int totalVagones = vagonesPasajeros + vagonesCarga;

            if (totalVagones > maxVagones) {

                Console.WriteLine($"No se pueden asignar {totalVagones} vagones. El tren tipo {tipoTren.TipoTren} solo permite hasta {maxVagones} vagones.");
                return;
            }

            Console.WriteLine($"\n Tipo tren: {tipoTren.TipoTren}");
            Console.WriteLine($"- Vagones de pasajeros requeridos: {vagonesPasajeros}");
            Console.WriteLine($"- Vagones de carga requeridos: {vagonesCarga}");
            Console.WriteLine($"- Total de vagones: {totalVagones}\n");


            for (int i = 1; i <= totalVagones; i++)
            {
                string tipoVagon = i <= vagonesPasajeros ? "Pasajeros" : "Carga";

                Console.WriteLine($"Vagón #{i}: {tipoVagon}");
            }
        }
    }
