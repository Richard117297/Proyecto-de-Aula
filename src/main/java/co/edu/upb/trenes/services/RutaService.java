package co.edu.upb.trenes.services;

import co.edu.upb.trenes.exceptions.ValidationException;
import co.edu.upb.trenes.models.rutas.Estacion;
import co.edu.upb.trenes.models.rutas.GrafoEstaciones;
import co.edu.upb.trenes.models.rutas.ListaEnlazadaSimpleRutas;
import co.edu.upb.trenes.models.rutas.ResultadoRuta;
import co.edu.upb.trenes.models.rutas.Ruta;
import co.edu.upb.trenes.models.rutas.SolicitudCambioRuta;
import co.edu.upb.trenes.repositories.impl.ConexionEstacionJsonRepository;
import co.edu.upb.trenes.repositories.impl.EstacionJsonRepository;
import co.edu.upb.trenes.repositories.impl.RutaJsonRepository;
import co.edu.upb.trenes.repositories.impl.SolicitudCambioRutaJsonRepository;

import java.util.List;

public class RutaService {
    private final RutaJsonRepository rutaRepository;
    private final EstacionJsonRepository estacionRepository = new EstacionJsonRepository();
    private final ConexionEstacionJsonRepository conexionRepository = new ConexionEstacionJsonRepository();
    private final SolicitudCambioRutaJsonRepository solicitudRepository = new SolicitudCambioRutaJsonRepository();

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

    public List<Ruta> listarRutas() {
        ListaEnlazadaSimpleRutas lista = new ListaEnlazadaSimpleRutas();
        rutaRepository.findAll().forEach(lista::agregar);
        return lista.aLista();
    }

    public List<Estacion> listarEstaciones() {
        return estacionRepository.findAll();
    }

    public int recomendarDistanciaKm(List<Estacion> estaciones, String origenId, String destinoId) {
        GrafoEstaciones grafo = new GrafoEstaciones();
        estaciones.forEach(grafo::agregarEstacion);
        return grafo.distanciaMinima(origenId, destinoId).orElse(Integer.MAX_VALUE);
    }

    public ResultadoRuta recomendarRuta(String origenId, String destinoId) {
        GrafoEstaciones grafo = cargarGrafo();
        return grafo.obtenerRutaMasCorta(origenId, destinoId)
                .orElseThrow(() -> new ValidationException("No existe ruta disponible entre las estaciones seleccionadas."));
    }

    public SolicitudCambioRuta solicitarCambio(String boletoId, String rutaActualId, String nuevaRutaId, String nuevoDestinoId) {
        return solicitudRepository.save(SolicitudCambioRuta.nueva(boletoId, rutaActualId, nuevaRutaId, nuevoDestinoId));
    }

    public List<SolicitudCambioRuta> listarSolicitudes() {
        return solicitudRepository.findAll();
    }

    private GrafoEstaciones cargarGrafo() {
        GrafoEstaciones grafo = new GrafoEstaciones();
        estacionRepository.findAll().forEach(grafo::agregarEstacion);
        conexionRepository.findAll().forEach(conexion ->
                grafo.agregarConexion(conexion.getOrigenId(), conexion.getDestinoId(), conexion.getDistanciaKm()));
        return grafo;
    }

    private void validarRuta(Ruta ruta) {
        if (ruta.getId() == null || ruta.getId().isBlank()
                || ruta.getOrigenId() == null || ruta.getOrigenId().isBlank()
                || ruta.getDestinoId() == null || ruta.getDestinoId().isBlank()) {
            throw new ValidationException("La ruta requiere id, origen y destino.");
        }
    }
}
