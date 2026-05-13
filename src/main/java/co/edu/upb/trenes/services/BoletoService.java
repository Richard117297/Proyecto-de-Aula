package co.edu.upb.trenes.services;

import co.edu.upb.trenes.exceptions.ValidationException;
import co.edu.upb.trenes.models.boletos.Boleto;
import co.edu.upb.trenes.models.boletos.EstadoBoleto;
import co.edu.upb.trenes.models.boletos.ListaEnlazadaSimpleBoletos;
import co.edu.upb.trenes.models.boletos.Tarifa;
import co.edu.upb.trenes.models.boletos.TipoBoleto;
import co.edu.upb.trenes.models.usuarios.RolUsuario;
import co.edu.upb.trenes.repositories.impl.BoletoJsonRepository;
import co.edu.upb.trenes.repositories.impl.TarifaJsonRepository;

import java.util.List;
import java.util.Optional;

public class BoletoService {
    private final BoletoJsonRepository boletoRepository;
    private final TarifaJsonRepository tarifaRepository;
    private final RutaService rutaService;

    public BoletoService() {
        this(new BoletoJsonRepository(), new TarifaJsonRepository(), new RutaService());
    }

    public BoletoService(BoletoJsonRepository boletoRepository, TarifaJsonRepository tarifaRepository, RutaService rutaService) {
        this.boletoRepository = boletoRepository;
        this.tarifaRepository = tarifaRepository;
        this.rutaService = rutaService;
    }

    public Boleto crearBoleto(String pasajeroId, String rutaId, String trenId, TipoBoleto tipoBoleto) {
        validarTexto(pasajeroId, "pasajero");
        validarTexto(rutaId, "ruta");
        validarTexto(trenId, "tren");
        if (tipoBoleto == null) {
            throw new ValidationException("El tipo de boleto es obligatorio.");
        }
        Boleto boleto = Boleto.nuevo(pasajeroId, rutaId, trenId, tipoBoleto, calcularTarifa(tipoBoleto));
        return boletoRepository.save(boleto);
    }

    public Boleto comprarBoleto(String pasajeroId, String nombre, String documento, String telefono,
                                String origenId, String destinoId, TipoBoleto tipoBoleto, String formaPago,
                                String contactoNombre, String contactoTelefono) {
        return comprarBoleto(pasajeroId, nombre, documento, telefono, origenId, destinoId, "tren-arnold-001",
                tipoBoleto, formaPago, contactoNombre, contactoTelefono);
    }

    public Boleto comprarBoleto(String pasajeroId, String nombre, String documento, String telefono,
                                String origenId, String destinoId, String trenId, TipoBoleto tipoBoleto, String formaPago,
                                String contactoNombre, String contactoTelefono) {
        validarTexto(pasajeroId, "pasajero");
        validarTexto(origenId, "origen");
        validarTexto(destinoId, "destino");
        int distancia = rutaService.recomendarRuta(origenId, destinoId).getDistanciaTotalKm();
        Tarifa tarifa = tarifaRepository.findByCategoria(tipoBoleto.name())
                .orElseThrow(() -> new ValidationException("No existe tarifa para " + tipoBoleto));
        Boleto boleto = Boleto.nuevo(pasajeroId, "ruta-" + origenId + "-" + destinoId, trenId, tipoBoleto, tarifa.calcular(distancia));
        boleto.setNombrePasajero(nombre);
        boleto.setDocumentoPasajero(documento);
        boleto.setTelefonoPasajero(telefono);
        boleto.setOrigenId(origenId);
        boleto.setDestinoId(destinoId);
        boleto.setDistanciaKm(distancia);
        boleto.setFormaPago(formaPago);
        boleto.setContactoEmergenciaNombre(contactoNombre);
        boleto.setContactoEmergenciaTelefono(contactoTelefono);
        boleto.setEstado(EstadoBoleto.PENDIENTE_VALIDACION);
        return boletoRepository.save(boleto);
    }

    public Optional<Boleto> consultarPorId(String id) {
        return boletoRepository.findById(id);
    }

    public List<Boleto> listarBoletos() {
        return listarConEstructuraAcademica();
    }

    public List<Boleto> listarBoletosPendientes() {
        return listarPorEstado(EstadoBoleto.PENDIENTE_VALIDACION);
    }

    public List<Boleto> listarBoletosValidados() {
        return listarPorEstado(EstadoBoleto.VALIDADO);
    }

    public List<Boleto> listarBoletosPorPasajero(String pasajeroId) {
        return listarConEstructuraAcademica().stream()
                .filter(boleto -> pasajeroId.equals(boleto.getPasajeroId()))
                .toList();
    }

    public Boleto modificar(Boleto boleto, RolUsuario rol) {
        if (rol != RolUsuario.ADMINISTRADOR && rol != RolUsuario.EMPLEADO) {
            throw new ValidationException("Solo empleados o administradores pueden modificar boletos.");
        }
        return boletoRepository.update(boleto);
    }

    public List<Boleto> listarPorEstado(EstadoBoleto estado) {
        return boletoRepository.findAll().stream()
                .filter(boleto -> boleto.getEstado() == estado)
                .toList();
    }

    public Boleto validar(String boletoId) {
        Boleto boleto = boletoRepository.findById(boletoId)
                .orElseThrow(() -> new ValidationException("Boleto no encontrado."));
        boleto.setValidado(true);
        boleto.setEstado(EstadoBoleto.VALIDADO);
        return boletoRepository.update(boleto);
    }

    public Boleto rechazar(String boletoId, String motivo) {
        Boleto boleto = boletoRepository.findById(boletoId)
                .orElseThrow(() -> new ValidationException("Boleto no encontrado."));
        boleto.setValidado(false);
        boleto.setEstado(EstadoBoleto.RECHAZADO);
        boleto.setMotivoRechazo(motivo);
        return boletoRepository.update(boleto);
    }

    public Boleto registrar(String boletoId) {
        Boleto boleto = boletoRepository.findById(boletoId)
                .orElseThrow(() -> new ValidationException("Boleto no encontrado."));
        if (boleto.getEstado() != EstadoBoleto.VALIDADO) {
            throw new ValidationException("Solo se registran boletos validados.");
        }
        boleto.setEstado(EstadoBoleto.REGISTRADO);
        return boletoRepository.update(boleto);
    }

    public List<Boleto> listarConEstructuraAcademica() {
        ListaEnlazadaSimpleBoletos lista = new ListaEnlazadaSimpleBoletos();
        boletoRepository.findAll().forEach(lista::agregar);
        return lista.aLista();
    }

    private double calcularTarifa(TipoBoleto tipoBoleto) {
        return tarifaRepository.findByCategoria(tipoBoleto.name())
                .map(tarifa -> tarifa.calcular(100))
                .orElse(60_000.0);
    }

    private void validarTexto(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new ValidationException("El campo " + campo + " es obligatorio.");
        }
    }
}
