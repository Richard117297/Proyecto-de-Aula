package co.edu.upb.trenes.controllers.pasajero;

import co.edu.upb.trenes.models.boletos.Boleto;
import co.edu.upb.trenes.models.rutas.Estacion;
import co.edu.upb.trenes.models.rutas.Ruta;
import co.edu.upb.trenes.services.BoletoService;
import co.edu.upb.trenes.services.RutaService;
import co.edu.upb.trenes.utils.AlertUtils;
import co.edu.upb.trenes.utils.NavigationUtils;
import co.edu.upb.trenes.utils.SessionManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class SolicitarCambioRutaController {
    @FXML private ComboBox<Boleto> boletoCombo;
    @FXML private ComboBox<Ruta> nuevaRutaCombo;
    @FXML private ComboBox<Estacion> nuevoDestinoCombo;
    @FXML private Label detalleLabel;

    private final RutaService rutaService = new RutaService();
    private final BoletoService boletoService = new BoletoService();

    @FXML private void initialize() {
        boletoCombo.setItems(FXCollections.observableArrayList(boletoService.listarBoletosPorPasajero(SessionManager.idUsuario())));
        nuevaRutaCombo.setItems(FXCollections.observableArrayList(rutaService.listarRutas()));
        nuevoDestinoCombo.setItems(FXCollections.observableArrayList(rutaService.listarEstaciones()));

        boletoCombo.setConverter(new StringConverter<>() {
            @Override public String toString(Boleto boleto) {
                return boleto == null ? "" : boleto.getId() + " - " + boleto.getOrigenId() + "->" + boleto.getDestinoId() + " - " + boleto.getEstado();
            }
            @Override public Boleto fromString(String string) { return null; }
        });
        nuevaRutaCombo.setConverter(new StringConverter<>() {
            @Override public String toString(Ruta ruta) {
                return ruta == null ? "" : ruta.getId() + " - " + ruta.getOrigenId() + "->" + ruta.getDestinoId();
            }
            @Override public Ruta fromString(String string) { return null; }
        });
        nuevoDestinoCombo.setConverter(new StringConverter<>() {
            @Override public String toString(Estacion estacion) {
                return estacion == null ? "" : estacion.getId() + " - " + estacion.getNombre();
            }
            @Override public Estacion fromString(String string) { return null; }
        });
        boletoCombo.valueProperty().addListener((obs, oldValue, boleto) -> mostrarDetalle());
        nuevaRutaCombo.valueProperty().addListener((obs, oldValue, ruta) -> mostrarDetalle());
        if (!boletoCombo.getItems().isEmpty()) boletoCombo.getSelectionModel().selectFirst();
        if (!nuevaRutaCombo.getItems().isEmpty()) nuevaRutaCombo.getSelectionModel().selectFirst();
        if (!nuevoDestinoCombo.getItems().isEmpty()) nuevoDestinoCombo.getSelectionModel().selectFirst();
    }

    @FXML private void solicitar() {
        Boleto boleto = boletoCombo.getValue();
        Ruta nuevaRuta = nuevaRutaCombo.getValue();
        Estacion destino = nuevoDestinoCombo.getValue();
        if (boleto == null || nuevaRuta == null || destino == null) {
            AlertUtils.warning("Seleccion incompleta", "Seleccione boleto, ruta y destino.");
            return;
        }
        rutaService.solicitarCambio(boleto.getId(), boleto.getRutaId(), nuevaRuta.getId(), destino.getId());
        AlertUtils.success("Solicitud enviada", "El administrador revisara el cambio de ruta.");
    }

    private void mostrarDetalle() {
        Boleto boleto = boletoCombo.getValue();
        Ruta ruta = nuevaRutaCombo.getValue();
        if (boleto == null) {
            detalleLabel.setText("Seleccione un boleto para comparar rutas.");
            return;
        }
        detalleLabel.setText("Boleto: " + boleto.getId()
                + "\nRuta actual: " + boleto.getOrigenId() + " -> " + boleto.getDestinoId()
                + "\nEstado: " + boleto.getEstado()
                + "\nNueva ruta: " + (ruta == null ? "Sin seleccionar" : ruta.getOrigenId() + " -> " + ruta.getDestinoId()));
    }

    @FXML private void volver() { NavigationUtils.goDashboard((Stage) boletoCombo.getScene().getWindow()); }
}
