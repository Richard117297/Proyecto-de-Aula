internal class Tren
{
        private string nombre;
        private string idTren;
        private double kilometraje;
        private int capacidadVagones; 
        private string tipoTren;
        private List<Boleto> boletosAsignados = new List<Boleto>();
        private Ruta rutaAsignada;
        private Pila_Vagon pilaVagones = new Pila_Vagon();


        public int CantidadVagonesCarga { get; private set; }
        public int CantidadVagonesPasajeros { get; private set; }
        public int CantidadPasajerosTotal { get; private set; } 


        public string Nombre { get => nombre; set => nombre = value; }
        public string IdTren { get => idTren; set => idTren = value; }
        public double Kilometraje { get => kilometraje; set => kilometraje = value; }
        public int CapacidadVagones { get => capacidadVagones; private set => capacidadVagones = value; }
        public string TipoTren { get => tipoTren; private set => tipoTren = value; }
        public List<Boleto> BoletosAsignados => boletosAsignados;
        public Ruta RutaAsignada { get => rutaAsignada; set => rutaAsignada = value; }
        public Pila_Vagon PilaVagones { get => pilaVagones; }


        public Tren(string idTren, string tipoTren, double kilometraje, Ruta rutaAsignada, int capacidadTotalVagones, int cantidadVagonesCarga, int cantidadVagonesPasajeros, int cantidadPasajerosTotal)
        {
            IdTren = idTren;
            Kilometraje = kilometraje;
            RutaAsignada = rutaAsignada;
            CapacidadVagones = capacidadTotalVagones;
            TipoTren = tipoTren;


            CantidadVagonesCarga = cantidadVagonesCarga;
            CantidadVagonesPasajeros = cantidadVagonesPasajeros;
            CantidadPasajerosTotal = cantidadPasajerosTotal; 

            pilaVagones = new Pila_Vagon(); 
            Nombre = $"{tipoTren} ({idTren})"; 
        }


        public Tren() { pilaVagones = new Pila_Vagon(); }


        public static void GuardarDatos(string rutaArchivo, List<Tren> trenes)
        {
            JsonHelper.GuardarDatos(rutaArchivo, trenes);
        }

        public static List<Tren> CargarDatos(string rutaArchivo)
        {
            return JsonHelper.CargarDatos<Tren>(rutaArchivo);
        }

        public string GetInfoCompleta()
        {
            StringBuilder sb = new StringBuilder();
            sb.AppendLine($"ID del Tren: {IdTren}");
            sb.AppendLine($"Nombre: {Nombre}");
            sb.AppendLine($"Tipo: {TipoTren}");
            sb.AppendLine($"Kilometraje: {Kilometraje} km");
            sb.AppendLine($"Capacidad de Vagones: {CapacidadVagones}");
            sb.AppendLine($"Cantidad de Vagones de Pasajeros: {CantidadVagonesPasajeros}");
            sb.AppendLine($"Cantidad de Vagones de Carga: {CantidadVagonesCarga}");
            sb.AppendLine($"Cantidad Total de Pasajeros: {CantidadPasajerosTotal}");
            sb.AppendLine($"Ruta Asignada: {(RutaAsignada != null ? RutaAsignada.GetInfoCompleta() : "Sin Ruta Asignada")}");
            return sb.ToString();
        }


        public void AsignarTipoTren(string tipo)
        {
            if (tipo.Equals("arnold", StringComparison.OrdinalIgnoreCase))
            {
                TipoTren = "arnold";
                CapacidadVagones = 32; 
            }
            else if (tipo.Equals("mercedes-benz", StringComparison.OrdinalIgnoreCase) || tipo.Equals("mercedez-benz", StringComparison.OrdinalIgnoreCase))
            {
                TipoTren = "mercedes-benz";
                CapacidadVagones = 28;
            }
            else
            {
                throw new ArgumentException("Tipo de tren no válido. Usa 'Arnold' o 'Mercedes-Benz'.");
            }
        }


        public void CalcularDistribucionVagonesYCapacidadPasajeros() 
        {
            if (TipoTren == null)
            {
                throw new InvalidOperationException("El tipo de tren debe estar asignado antes de calcular la distribución de vagones.");
            }


            if (TipoTren.Equals("arnold", StringComparison.OrdinalIgnoreCase))
            {

                CantidadVagonesCarga = 8;
                CantidadVagonesPasajeros = 24;
            }
            else if (TipoTren.Equals("mercedes-benz", StringComparison.OrdinalIgnoreCase))
            {

                CantidadVagonesCarga = 7;
                CantidadVagonesPasajeros = 21;
            }
            else
            {

                CantidadVagonesCarga = 0;
                CantidadVagonesPasajeros = CapacidadVagones; 
            }

            if (CantidadVagonesCarga + CantidadVagonesPasajeros > CapacidadVagones)
            {
                int diff = (CantidadVagonesCarga + CantidadVagonesPasajeros) - CapacidadVagones;
                CantidadVagonesPasajeros -= diff;
                if (CantidadVagonesPasajeros < 0) CantidadVagonesPasajeros = 0;
            }

     
            const int ASIENTOS_POR_VAGON_PASAJERO = 34;
            CantidadPasajerosTotal = CantidadVagonesPasajeros * ASIENTOS_POR_VAGON_PASAJERO;
        }


        public override string ToString()
        {
            string rutaInfo = RutaAsignada != null && RutaAsignada.EstacionDePartida != null && RutaAsignada.EstacionDePartida.NombreEstacion != null && RutaAsignada.EstacionDePartida.NombreEstacion.Length > 0 &&
                              RutaAsignada.EstacionDeLlegada != null && RutaAsignada.EstacionDeLlegada.NombreEstacion != null && RutaAsignada.EstacionDeLlegada.NombreEstacion.Length > 0
                              ? $"{RutaAsignada.EstacionDePartida.NombreEstacion[0]}-{RutaAsignada.EstacionDeLlegada.NombreEstacion[0]}"
                              : "Sin Ruta Asignada";

            return $"ID: {IdTren} | Tipo: {TipoTren} | Ruta: {rutaInfo} | Vagones: {CapacidadVagones} ({CantidadVagonesPasajeros}P/{CantidadVagonesCarga}C) | Pasajeros Max: {CantidadPasajerosTotal}";
        }

        public void calcularCantidadPasajeros(List<Boleto> listaDeBoletos)

        {

            int cantidadPasajeros = 0;
            boletosAsignados.Clear();

            foreach (Boleto boleto in listaDeBoletos)
            {
                if (boleto.IdTren == this.IdTren)
                {
                    boletosAsignados.Add(boleto);
                    cantidadPasajeros++;
                }

            }
            Console.WriteLine($" Pasajeros asignados al tren '{this.Nombre}' (ID: {this.IdTren}): {cantidadPasajeros}");
        }

        public void AgregarBoletoAlTren(Boleto boleto)
        {
            if (boleto.IdTren == this.IdTren)
            {
                boletosAsignados.Add(boleto);
            }
        }


        public void calcularKilometrajeUsado(GrafoEstacion grafo)
        {
            if (rutaAsignada == null)
            {
                
                throw new InvalidOperationException("No hay ruta asignada al tren para calcular el kilometraje usado.");

            }

            if (rutaAsignada.EstacionDePartida == null || rutaAsignada.EstacionDePartida.NombreEstacion == null || rutaAsignada.EstacionDePartida.NombreEstacion.Length == 0 ||
                rutaAsignada.EstacionDeLlegada == null || rutaAsignada.EstacionDeLlegada.NombreEstacion == null || rutaAsignada.EstacionDeLlegada.NombreEstacion.Length == 0)
            {
                throw new InvalidOperationException("La ruta asignada no tiene estaciones de partida o llegada definidas correctamente.");
            }


            int origen = grafo.Estaciones.FindIndex(e => e.Estacion != null && e.Estacion.NombreEstacion != null && e.Estacion.NombreEstacion.Length > 0 && e.Estacion.NombreEstacion[0] == rutaAsignada.EstacionDePartida.NombreEstacion[0]);
            int destino = grafo.Estaciones.FindIndex(e => e.Estacion != null && e.Estacion.NombreEstacion != null && e.Estacion.NombreEstacion.Length > 0 && e.Estacion.NombreEstacion[0] == rutaAsignada.EstacionDeLlegada.NombreEstacion[0]);


            if (origen == -1 || destino == -1)
            {

                throw new ArgumentException("No se encontró alguna estación de la ruta asignada en el grafo.");

            }

            int distancia = grafo.CalcularDistancia(origen, destino);

            if (distancia == int.MaxValue) 
            {

                throw new InvalidOperationException($"No hay ruta válida disponible entre {rutaAsignada.EstacionDePartida.NombreEstacion[0]} y {rutaAsignada.EstacionDeLlegada.NombreEstacion[0]} en el grafo.");

            }

            Kilometraje += distancia; 

        }

        public void agregarRutaAltren(string idRuta, ListaEnlazadaSimple_Rutas listaRutas)
        {
            NodoDeRuta actual = listaRutas.Cabeza;

            while (actual != null)
            {
                if (actual.Ruta.IdRuta == idRuta)
                {
                    rutaAsignada = actual.Ruta;
                    Console.WriteLine($"Ruta con ID '{idRuta}' asignada al tren '{Nombre}'.");
                    return;
                }
                actual = actual.Siguiente;
            }

            Console.WriteLine("Ruta no encontrada.");
        }

        public void MostrarRutaAsignada()
        {
            if (rutaAsignada == null)
            {
                Console.WriteLine("Este tren no tiene una ruta asignada.");
                return;
            }

            Console.WriteLine("Información de la ruta asignada:");
            Console.WriteLine($"ID Ruta: {rutaAsignada.IdRuta}");
            Console.WriteLine($"Estación de Partida: {rutaAsignada.EstacionDePartida.NombreEstacion[0]}");
            Console.WriteLine($"Estación de Llegada: {rutaAsignada.EstacionDeLlegada.NombreEstacion[0]}");
            Console.WriteLine($"Salida: {rutaAsignada.FechaHoraDeSalida}");
            Console.WriteLine($"Llegada: {rutaAsignada.FechaHoraDeLlegada}");
        }

        public void agregarVagonAlTren(string idDelVagon)
        {
            int totalPasajeros = boletosAsignados.Count;
            int totalCargaKg = boletosAsignados.Sum(b => b.PesoEquipaje);

            Vagon vagonModelo = new Vagon(idDelVagon, 500, 1000, 40);

            
            vagonModelo.SeleccioneTipoVagon(totalPasajeros, totalCargaKg, this);

            int maxVagones = TipoTren.ToLower() == "mercedes-benz" ? 28 :
                             TipoTren.ToLower() == "arnold" ? 32 :
                             throw new ArgumentException("Tipo de tren no válido");

            int capacidadPasajerosPorVagon = vagonModelo.CapacidadDePasajeros;
            int capacidadCargaPorVagon = vagonModelo.PesoLimKg;

            int vagonesPasajeros = (int)Math.Ceiling((double)totalPasajeros / capacidadPasajerosPorVagon);
            int vagonesCarga = (int)Math.Ceiling((double)totalCargaKg / capacidadCargaPorVagon);
            int totalVagones = vagonesPasajeros + vagonesCarga;

            if (totalVagones > maxVagones)
            {
                Console.WriteLine($" No se pueden asignar {totalVagones} vagones. El tren tipo {TipoTren} solo permite hasta {maxVagones} vagones.");
                return;
            }

            for (int i = 1; i <= totalVagones; i++)
            {
                string tipoVagon = i <= vagonesPasajeros ? "Pasajeros" : "Carga";
                int capacidadPasajeros = tipoVagon == "Pasajeros" ? 40 : 0;
                Vagon nuevo = new Vagon($"{idDelVagon}_{i}", 500, 1000, capacidadPasajeros)
                {
                    TipoVagon = tipoVagon
                };

                pilaVagones.push(nuevo);
                Console.WriteLine($" Vagón #{i}: {tipoVagon} - ID: {nuevo.IdDelVagon}");
            }

            Console.WriteLine($" Se han asignado {totalVagones} vagones al tren '{Nombre}' (ID: {IdTren}).");
        }
    }

