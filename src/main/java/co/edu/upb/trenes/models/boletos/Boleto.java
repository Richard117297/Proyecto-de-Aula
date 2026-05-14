package co.edu.upb.trenes.models.boletos;

import java.time.LocalDateTime;
import java.util.UUID;

public class Boleto {
    private String id;
    private String pasajeroId;
    private String nombrePasajero;
    private String documentoPasajero;
    private String telefonoPasajero;
    private String rutaId;
    private String trenId;
    private String origenId;
    private String destinoId;
    private TipoBoleto tipoBoleto;
    private EstadoBoleto estado = EstadoBoleto.PENDIENTE_VALIDACION;
    private double valorPasaje;
    private int distanciaKm;
    private int numeroAsiento;
    private boolean validado;
    private String formaPago;
    private String contactoEmergenciaNombre;
    private String contactoEmergenciaTelefono;
    private String motivoRechazo;
    private LocalDateTime fechaCompra;
    private LocalDateTime fechaSalida;
    private LocalDateTime fechaLlegada;

    public Boleto() {
    }

    public static Boleto nuevo(String pasajeroId, String rutaId, String trenId, TipoBoleto tipoBoleto, double valorPasaje) {
        Boleto boleto = new Boleto();
        boleto.id = "boleto-" + UUID.randomUUID();
        boleto.pasajeroId = pasajeroId;
        boleto.rutaId = rutaId;
        boleto.trenId = trenId;
        boleto.tipoBoleto = tipoBoleto;
        boleto.valorPasaje = valorPasaje;
        boleto.fechaCompra = LocalDateTime.now();
        return boleto;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getPasajeroId() { return pasajeroId; }
    public void setPasajeroId(String pasajeroId) { this.pasajeroId = pasajeroId; }
    public String getNombrePasajero() { return nombrePasajero; }
    public void setNombrePasajero(String nombrePasajero) { this.nombrePasajero = nombrePasajero; }
    public String getDocumentoPasajero() { return documentoPasajero; }
    public void setDocumentoPasajero(String documentoPasajero) { this.documentoPasajero = documentoPasajero; }
    public String getTelefonoPasajero() { return telefonoPasajero; }
    public void setTelefonoPasajero(String telefonoPasajero) { this.telefonoPasajero = telefonoPasajero; }
    public String getRutaId() { return rutaId; }
    public void setRutaId(String rutaId) { this.rutaId = rutaId; }
    public String getTrenId() { return trenId; }
    public void setTrenId(String trenId) { this.trenId = trenId; }
    public String getOrigenId() { return origenId; }
    public void setOrigenId(String origenId) { this.origenId = origenId; }
    public String getDestinoId() { return destinoId; }
    public void setDestinoId(String destinoId) { this.destinoId = destinoId; }
    public TipoBoleto getTipoBoleto() { return tipoBoleto; }
    public void setTipoBoleto(TipoBoleto tipoBoleto) { this.tipoBoleto = tipoBoleto; }
    public EstadoBoleto getEstado() { return estado; }
    public void setEstado(EstadoBoleto estado) { this.estado = estado; }
    public double getValorPasaje() { return valorPasaje; }
    public void setValorPasaje(double valorPasaje) { this.valorPasaje = valorPasaje; }
    public int getDistanciaKm() { return distanciaKm; }
    public void setDistanciaKm(int distanciaKm) { this.distanciaKm = distanciaKm; }
    public int getNumeroAsiento() { return numeroAsiento; }
    public void setNumeroAsiento(int numeroAsiento) { this.numeroAsiento = numeroAsiento; }
    public boolean isValidado() { return validado; }
    public void setValidado(boolean validado) { this.validado = validado; }
    public String getFormaPago() { return formaPago; }
    public void setFormaPago(String formaPago) { this.formaPago = formaPago; }
    public String getContactoEmergenciaNombre() { return contactoEmergenciaNombre; }
    public void setContactoEmergenciaNombre(String contactoEmergenciaNombre) { this.contactoEmergenciaNombre = contactoEmergenciaNombre; }
    public String getContactoEmergenciaTelefono() { return contactoEmergenciaTelefono; }
    public void setContactoEmergenciaTelefono(String contactoEmergenciaTelefono) { this.contactoEmergenciaTelefono = contactoEmergenciaTelefono; }
    public String getMotivoRechazo() { return motivoRechazo; }
    public void setMotivoRechazo(String motivoRechazo) { this.motivoRechazo = motivoRechazo; }
    public LocalDateTime getFechaCompra() { return fechaCompra; }
    public void setFechaCompra(LocalDateTime fechaCompra) { this.fechaCompra = fechaCompra; }
    public LocalDateTime getFechaSalida() { return fechaSalida; }
    public void setFechaSalida(LocalDateTime fechaSalida) { this.fechaSalida = fechaSalida; }
    public LocalDateTime getFechaLlegada() { return fechaLlegada; }
    public void setFechaLlegada(LocalDateTime fechaLlegada) { this.fechaLlegada = fechaLlegada; }
}
