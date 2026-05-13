package co.edu.upb.trenes.services;

import co.edu.upb.trenes.exceptions.ValidationException;
import co.edu.upb.trenes.models.trenes.EstadoTren;
import co.edu.upb.trenes.models.trenes.ListaEnlazadaSimpleTrenes;
import co.edu.upb.trenes.models.trenes.ResultadoCalculoVagones;
import co.edu.upb.trenes.models.trenes.TipoTren;
import co.edu.upb.trenes.models.trenes.Tren;
import co.edu.upb.trenes.repositories.impl.TrenJsonRepository;

import java.util.List;

public class TrenService {
    private static final int PASAJEROS_POR_VAGON = 40;
    private static final int TRIPULACION_OPERATIVA = 6;

    private final TrenJsonRepository trenRepository;

    public TrenService() {
        this(new TrenJsonRepository());
    }

    public TrenService(TrenJsonRepository trenRepository) {
        this.trenRepository = trenRepository;
    }

    public Tren agregarTren(Tren tren) {
        if (trenRepository.findById(tren.getId()).isPresent()) {
            throw new ValidationException("Ya existe un tren con ese ID.");
        }
        validarCapacidad(tren);
        return trenRepository.save(tren);
    }

    public Tren darDeBaja(String trenId) {
        Tren tren = trenRepository.findById(trenId)
                .orElseThrow(() -> new ValidationException("Tren no encontrado."));
        tren.setActivo(false);
        tren.setEstado(EstadoTren.BAJA);
        return trenRepository.update(tren);
    }

    public int[] calcularVagonesRequeridos(TipoTren tipoTren, int pasajeros, double cargaKg) {
        if (tipoTren == null || pasajeros < 0 || cargaKg < 0) {
            throw new ValidationException("Datos invalidos para calcular vagones.");
        }
        int vagonesPasajeros = (int) Math.ceil((pasajeros + TRIPULACION_OPERATIVA) / (double) PASAJEROS_POR_VAGON);
        int vagonesCarga = (int) Math.ceil(vagonesPasajeros / 2.0);
        int total = vagonesPasajeros + vagonesCarga;
        if (total > tipoTren.getCapacidadMaximaVagones()) {
            throw new ValidationException("La cantidad de vagones supera la capacidad del tren.");
        }
        return new int[]{vagonesPasajeros, vagonesCarga};
    }

    public ResultadoCalculoVagones calcularCantidadVagones(int cantidadPasajeros, int cantidadEquipaje, TipoTren tipoTren) {
        int vagonesPasajeros = (int) Math.ceil((cantidadPasajeros + TRIPULACION_OPERATIVA) / (double) PASAJEROS_POR_VAGON);
        int vagonesCarga = Math.max(1, (int) Math.ceil(vagonesPasajeros / 2.0));
        int total = vagonesPasajeros + vagonesCarga;
        boolean cumple = tipoTren != null && total <= tipoTren.getCapacidadMaximaVagones();
        String mensaje = cumple ? "La configuracion cumple la capacidad del tren." : "La configuracion supera la capacidad del tren.";
        return new ResultadoCalculoVagones(vagonesPasajeros, vagonesCarga, total, cumple, mensaje);
    }

    public Tren verificar(String trenId, double kilometrajeNuevo, double kilometrajeMaximo) {
        Tren tren = trenRepository.findById(trenId)
                .orElseThrow(() -> new ValidationException("Tren no encontrado."));
        tren.setKilometraje(tren.getKilometraje() + kilometrajeNuevo);
        if (tren.getKilometraje() >= kilometrajeMaximo) {
            tren.setEstado(EstadoTren.EN_REVISION);
        }
        return trenRepository.update(tren);
    }

    public List<Tren> listarConEstructuraAcademica() {
        ListaEnlazadaSimpleTrenes lista = new ListaEnlazadaSimpleTrenes();
        trenRepository.findAll().forEach(lista::agregar);
        return lista.aLista();
    }

    public List<Tren> listarTrenes() {
        return listarConEstructuraAcademica();
    }

    public List<Tren> listarTrenesActivos() {
        return listarTrenes().stream()
                .filter(Tren::isActivo)
                .filter(tren -> tren.getEstado() != EstadoTren.BAJA)
                .toList();
    }

    private void validarCapacidad(Tren tren) {
        if (tren.getTipoTren() == null) {
            throw new ValidationException("El tipo de tren es obligatorio.");
        }
        if (tren.totalVagones() < 1 || tren.totalVagones() > tren.getTipoTren().getCapacidadMaximaVagones()) {
            throw new ValidationException("Capacidad invalida para " + tren.getTipoTren());
        }
    }
}
