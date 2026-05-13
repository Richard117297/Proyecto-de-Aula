package co.edu.upb.trenes.repositories.storage;

import co.edu.upb.trenes.config.JsonStorageConfig;

import java.nio.file.Path;

public class JsonDataInitializer {
    private static final String[] ARRAY_FILES = {
            "usuarios.json", "pasajeros.json", "empleados.json", "administradores.json",
            "boletos.json", "tipos-boleto.json", "trenes.json", "vagones.json",
            "rutas.json", "estaciones.json", "equipajes.json", "abordajes.json",
            "maquinas-venta.json"
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
    }
}
