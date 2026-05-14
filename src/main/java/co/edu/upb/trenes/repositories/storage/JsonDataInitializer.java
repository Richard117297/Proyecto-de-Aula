package co.edu.upb.trenes.repositories.storage;

import co.edu.upb.trenes.config.JsonStorageConfig;
import co.edu.upb.trenes.models.boletos.Tarifa;
import co.edu.upb.trenes.models.boletos.TipoBoleto;
import co.edu.upb.trenes.models.config.ConfiguracionSistema;
import co.edu.upb.trenes.models.rutas.ConexionEstacion;
import co.edu.upb.trenes.models.rutas.Estacion;
import co.edu.upb.trenes.models.rutas.Ruta;
import co.edu.upb.trenes.models.trenes.TipoTren;
import co.edu.upb.trenes.models.trenes.Tren;
import co.edu.upb.trenes.models.usuarios.RolUsuario;
import co.edu.upb.trenes.models.usuarios.Usuario;
import co.edu.upb.trenes.repositories.impl.ConexionEstacionJsonRepository;
import co.edu.upb.trenes.repositories.impl.EstacionJsonRepository;
import co.edu.upb.trenes.repositories.impl.RutaJsonRepository;
import co.edu.upb.trenes.repositories.impl.TarifaJsonRepository;
import co.edu.upb.trenes.repositories.impl.TrenJsonRepository;
import co.edu.upb.trenes.repositories.impl.UsuarioJsonRepository;

import java.nio.file.Path;
import java.time.LocalDateTime;

public class JsonDataInitializer {
    private static final String[] ARRAY_FILES = {
            "usuarios.json", "pasajeros.json", "empleados.json", "administradores.json",
            "boletos.json", "tipos-boleto.json", "trenes.json", "vagones.json",
            "rutas.json", "estaciones.json", "equipajes.json", "abordajes.json",
            "maquinas-venta.json", "tarifas.json", "solicitudes-cambio-ruta.json",
            "notificaciones.json", "historial-operaciones.json", "conexiones-estaciones.json"
    };

    private final JsonFileManager fileManager;

    public JsonDataInitializer(JsonFileManager fileManager) {
        this.fileManager = fileManager;
    }

    public void initialize() {
        for (String fileName : ARRAY_FILES) {
            fileManager.ensureArrayFile(JsonStorageConfig.resolve(fileName));
        }
        Path config = JsonStorageConfig.resolve("configuracion.json");
        fileManager.ensureObjectFile(config);
        seedConfiguration(config);
        seedUsuarios();
        seedTarifas();
        seedEstacionesYGrafo();
        seedTrenesYRutas();
    }

    private void seedConfiguration(Path config) {
        ConfiguracionSistema actual = fileManager.readObject(config, ConfiguracionSistema.class);
        if (actual.getValorBasePorKm() <= 0) {
            fileManager.writeObject(config, new ConfiguracionSistema());
        }
    }

    private void seedUsuarios() {
        UsuarioJsonRepository repository = new UsuarioJsonRepository();
        if (repository.findAll().isEmpty()) {
            repository.save(new Usuario("admin-001", "admin", "admin123", RolUsuario.ADMINISTRADOR, "Administrador Principal", true));
            repository.save(new Usuario("empleado-001", "empleado", "empleado123", RolUsuario.EMPLEADO, "Empleado Principal", true));
            repository.save(new Usuario("pasajero-001", "pasajero", "pasajero123", RolUsuario.PASAJERO, "Pasajero Principal", true));
        }
    }

    private void seedTarifas() {
        TarifaJsonRepository repository = new TarifaJsonRepository();
        if (repository.findAll().isEmpty()) {
            repository.save(new Tarifa("tarifa-estandar", TipoBoleto.ESTANDAR, 320, 1.0));
            repository.save(new Tarifa("tarifa-ejecutivo", TipoBoleto.EJECUTIVO, 320, 1.35));
            repository.save(new Tarifa("tarifa-premium", TipoBoleto.PREMIUM, 320, 1.75));
        }
    }

    private void seedEstacionesYGrafo() {
        EstacionJsonRepository estaciones = new EstacionJsonRepository();
        if (estaciones.findAll().isEmpty()) {
            for (String id : new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"}) {
                estaciones.save(new Estacion(id, "Estacion " + id, "Zona " + id));
            }
        }
        ConexionEstacionJsonRepository conexiones = new ConexionEstacionJsonRepository();
        if (conexiones.findAll().isEmpty()) {
            conexiones.save(new ConexionEstacion("A-B", "A", "B", 30));
            conexiones.save(new ConexionEstacion("A-D", "A", "D", 50));
            conexiones.save(new ConexionEstacion("A-F", "A", "F", 50));
            conexiones.save(new ConexionEstacion("A-C", "A", "C", 40));
            conexiones.save(new ConexionEstacion("D-E", "D", "E", 20));
            conexiones.save(new ConexionEstacion("F-E", "F", "E", 65));
            conexiones.save(new ConexionEstacion("F-G", "F", "G", 80));
            conexiones.save(new ConexionEstacion("G-H", "G", "H", 30));
            conexiones.save(new ConexionEstacion("C-J", "C", "J", 120));
            conexiones.save(new ConexionEstacion("C-K", "C", "K", 110));
            conexiones.save(new ConexionEstacion("C-I", "C", "I", 80));
            conexiones.save(new ConexionEstacion("I-G", "I", "G", 145));
        }
    }

    private void seedTrenesYRutas() {
        TrenJsonRepository trenes = new TrenJsonRepository();
        if (trenes.findAll().isEmpty()) {
            Tren arnold = new Tren("tren-arnold-001", "Arnold Principal", TipoTren.ARNOLD);
            arnold.setVagonesPasajeros(10);
            arnold.setVagonesCarga(5);
            arnold.setKilometraje(12500);
            trenes.save(arnold);

            Tren mercedes = new Tren("tren-mercedes-001", "Mercedes-Benz Principal", TipoTren.MERCEDES_BENZ);
            mercedes.setVagonesPasajeros(8);
            mercedes.setVagonesCarga(4);
            mercedes.setKilometraje(8300);
            trenes.save(mercedes);
        }
        RutaJsonRepository rutas = new RutaJsonRepository();
        if (rutas.findAll().isEmpty()) {
            Ruta ruta = new Ruta();
            ruta.setId("ruta-A-H");
            ruta.setOrigenId("A");
            ruta.setDestinoId("H");
            ruta.setTrenId("tren-arnold-001");
            ruta.setSalida(LocalDateTime.now().plusDays(1).withHour(8).withMinute(0));
            ruta.setLlegada(LocalDateTime.now().plusDays(1).withHour(12).withMinute(30));
            rutas.save(ruta);
        }
    }
}
