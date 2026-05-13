package co.edu.upb.trenes.models.rutas;

import java.time.LocalDateTime;
import java.util.UUID;

public class SolicitudCambioRuta {
    private String id;
    private String boletoId;
    private String rutaActualId;
    private String nuevaRutaId;
    private String nuevoDestinoId;
    private EstadoSolicitudCambioRuta estado = EstadoSolicitudCambioRuta.PENDIENTE;
    private String motivo;
    private LocalDateTime fechaSolicitud = LocalDateTime.now();

    public SolicitudCambioRuta() {
    }

    public static SolicitudCambioRuta nueva(String boletoId, String rutaActualId, String nuevaRutaId, String nuevoDestinoId) {
        SolicitudCambioRuta solicitud = new SolicitudCambioRuta();
        solicitud.id = "solicitud-" + UUID.randomUUID();
        solicitud.boletoId = boletoId;
        solicitud.rutaActualId = rutaActualId;
        solicitud.nuevaRutaId = nuevaRutaId;
        solicitud.nuevoDestinoId = nuevoDestinoId;
        return solicitud;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getBoletoId() { return boletoId; }
    public void setBoletoId(String boletoId) { this.boletoId = boletoId; }
    public String getRutaActualId() { return rutaActualId; }
    public void setRutaActualId(String rutaActualId) { this.rutaActualId = rutaActualId; }
    public String getNuevaRutaId() { return nuevaRutaId; }
    public void setNuevaRutaId(String nuevaRutaId) { this.nuevaRutaId = nuevaRutaId; }
    public String getNuevoDestinoId() { return nuevoDestinoId; }
    public void setNuevoDestinoId(String nuevoDestinoId) { this.nuevoDestinoId = nuevoDestinoId; }
    public EstadoSolicitudCambioRuta getEstado() { return estado; }
    public void setEstado(EstadoSolicitudCambioRuta estado) { this.estado = estado; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
    public LocalDateTime getFechaSolicitud() { return fechaSolicitud; }
    public void setFechaSolicitud(LocalDateTime fechaSolicitud) { this.fechaSolicitud = fechaSolicitud; }
}
