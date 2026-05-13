package co.edu.upb.trenes.services;

import co.edu.upb.trenes.exceptions.ValidationException;
import co.edu.upb.trenes.models.boletos.Boleto;
import co.edu.upb.trenes.models.boletos.ListaEnlazadaSimpleBoletos;
import co.edu.upb.trenes.models.boletos.TipoBoleto;
import co.edu.upb.trenes.models.usuarios.RolUsuario;
import co.edu.upb.trenes.repositories.impl.BoletoJsonRepository;

import java.util.List;
import java.util.Optional;

public class BoletoService {
    private final BoletoJsonRepository boletoRepository;

    public BoletoService() {
        this(new BoletoJsonRepository());
    }

    public BoletoService(BoletoJsonRepository boletoRepository) {
        this.boletoRepository = boletoRepository;
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

    public Optional<Boleto> consultarPorId(String id) {
        return boletoRepository.findById(id);
    }

    public Boleto modificar(Boleto boleto, RolUsuario rol) {
        if (rol != RolUsuario.ADMINISTRADOR && rol != RolUsuario.EMPLEADO) {
            throw new ValidationException("Solo empleados o administradores pueden modificar boletos.");
        }
        return boletoRepository.update(boleto);
    }

    public List<Boleto> listarConEstructuraAcademica() {
        ListaEnlazadaSimpleBoletos lista = new ListaEnlazadaSimpleBoletos();
        boletoRepository.findAll().forEach(lista::agregar);
        return lista.aLista();
    }

    private double calcularTarifa(TipoBoleto tipoBoleto) {
        return switch (tipoBoleto) {
            case PREMIUM -> 120_000;
            case EJECUTIVO -> 90_000;
            case ESTANDAR -> 60_000;
        };
    }

    private void validarTexto(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new ValidationException("El campo " + campo + " es obligatorio.");
        }
    }
}
