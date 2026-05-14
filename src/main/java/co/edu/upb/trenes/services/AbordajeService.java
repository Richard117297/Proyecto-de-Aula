package co.edu.upb.trenes.services;

import co.edu.upb.trenes.models.abordaje.ColaPrioridadPasajeros;
import co.edu.upb.trenes.models.abordaje.PasajeroPrioridad;
import co.edu.upb.trenes.config.JsonStorageConfig;
import co.edu.upb.trenes.repositories.storage.JsonFileManager;

import java.util.List;

public class AbordajeService {
    public List<PasajeroPrioridad> ordenarAbordaje(List<PasajeroPrioridad> pasajeros) {
        ColaPrioridadPasajeros cola = new ColaPrioridadPasajeros();
        pasajeros.forEach(cola::encolar);
        return cola.aListaOrdenada();
    }

    public List<PasajeroPrioridad> publicarOrden(List<PasajeroPrioridad> pasajeros) {
        List<PasajeroPrioridad> orden = ordenarAbordaje(pasajeros);
        JsonFileManager manager = new JsonFileManager();
        manager.writeList(JsonStorageConfig.resolve("abordajes.json"), orden);
        return orden;
    }
}
