package co.edu.upb.trenes.models.boletos;

import java.time.LocalDateTime;
import java.util.UUID;

public class Boleto {
    private String id;
    private String pasajeroId;
    private String rutaId;
    private String trenId;
    private TipoBoleto tipoBoleto;
    private double valorPasaje;
    private int numeroAsiento;
    private boolean validado;
    private LocalDateTime fechaCompra;

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
    public String getRutaId() { return rutaId; }
    public void setRutaId(String rutaId) { this.rutaId = rutaId; }
    public String getTrenId() { return trenId; }
    public void setTrenId(String trenId) { this.trenId = trenId; }
    public TipoBoleto getTipoBoleto() { return tipoBoleto; }
    public void setTipoBoleto(TipoBoleto tipoBoleto) { this.tipoBoleto = tipoBoleto; }
    public double getValorPasaje() { return valorPasaje; }
    public void setValorPasaje(double valorPasaje) { this.valorPasaje = valorPasaje; }
    public int getNumeroAsiento() { return numeroAsiento; }
    public void setNumeroAsiento(int numeroAsiento) { this.numeroAsiento = numeroAsiento; }
    public boolean isValidado() { return validado; }
    public void setValidado(boolean validado) { this.validado = validado; }
    public LocalDateTime getFechaCompra() { return fechaCompra; }
    public void setFechaCompra(LocalDateTime fechaCompra) { this.fechaCompra = fechaCompra; }
}
