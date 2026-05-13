package co.edu.upb.trenes.services;

import co.edu.upb.trenes.exceptions.ValidationException;
import co.edu.upb.trenes.models.rutas.Estacion;
import co.edu.upb.trenes.models.rutas.GrafoEstaciones;
import co.edu.upb.trenes.models.rutas.ListaEnlazadaSimpleRutas;
import co.edu.upb.trenes.models.rutas.Ruta;
import co.edu.upb.trenes.repositories.impl.RutaJsonRepository;

import java.util.List;

public class RutaService {
    private final RutaJsonRepository rutaRepository;

    public RutaService() {
        this(new RutaJsonRepository());
    }

    public RutaService(RutaJsonRepository rutaRepository) {
        this.rutaRepository = rutaRepository;
    }

    public Ruta crearRuta(Ruta ruta) {
        validarRuta(ruta);
        return rutaRepository.save(ruta);
    }

    public Ruta modificarRuta(Ruta ruta) {
        Ruta actual = rutaRepository.findById(ruta.getId())
                .orElseThrow(() -> new ValidationException("Ruta no encontrada."));
        if (actual.isIniciada()) {
            throw new ValidationException("No se puede modificar una ruta que ya inicio.");
        }
        return rutaRepository.update(ruta);
    }

    public List<Ruta> consultarDisponibles() {
        ListaEnlazadaSimpleRutas lista = new ListaEnlazadaSimpleRutas();
        rutaRepository.findAll().stream().filter(ruta -> !ruta.isIniciada()).forEach(lista::agregar);
        return lista.aLista();
    }

    public int recomendarDistanciaKm(List<Estacion> estaciones, String origenId, String destinoId) {
        GrafoEstaciones grafo = new GrafoEstaciones();
        estaciones.forEach(grafo::agregarEstacion);
        return grafo.distanciaMinima(origenId, destinoId).orElse(Integer.MAX_VALUE);
    }

    private void validarRuta(Ruta ruta) {
        if (ruta.getId() == null || ruta.getId().isBlank()
                || ruta.getOrigenId() == null || ruta.getOrigenId().isBlank()
                || ruta.getDestinoId() == null || ruta.getDestinoId().isBlank()) {
            throw new ValidationException("La ruta requiere id, origen y destino.");
        }
    }
}
