package co.edu.upb.trenes.controllers.admin.rutas;

import co.edu.upb.trenes.models.rutas.Estacion;
import co.edu.upb.trenes.models.rutas.ResultadoRuta;
import co.edu.upb.trenes.services.RutaService;
import co.edu.upb.trenes.utils.AlertUtils;
import co.edu.upb.trenes.utils.NavigationUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class GestionRutasAdminController {
    @FXML private ComboBox<Estacion> origenCombo;
    @FXML private ComboBox<Estacion> destinoCombo;
    @FXML private Label resultadoLabel;

    private final RutaService rutaService = new RutaService();

    @FXML private void initialize() {
        origenCombo.setItems(FXCollections.observableArrayList(rutaService.listarEstaciones()));
        destinoCombo.setItems(FXCollections.observableArrayList(rutaService.listarEstaciones()));
        StringConverter<Estacion> converter = new StringConverter<>() {
            @Override public String toString(Estacion estacion) {
                return estacion == null ? "" : estacion.getId() + " - " + estacion.getNombre();
            }
            @Override public Estacion fromString(String string) { return null; }
        };
        origenCombo.setConverter(converter);
        destinoCombo.setConverter(converter);
        if (!origenCombo.getItems().isEmpty()) origenCombo.getSelectionModel().selectFirst();
        if (destinoCombo.getItems().size() > 7) destinoCombo.getSelectionModel().select(7);
    }

    @FXML private void recomendar() {
        ResultadoRuta resultado = rutaService.recomendarRuta(origenCombo.getValue().getId(), destinoCombo.getValue().getId());
        resultadoLabel.setText("Ruta recomendada: " + resultado.resumen()
                + "\nEstaciones intermedias: " + String.join(", ", resultado.getEstaciones())
                + "\nTiempo estimado: " + Math.max(1, resultado.getDistanciaTotalKm() / 80) + " h aprox.");
    }

    @FXML private void publicar() {
        recomendar();
        AlertUtils.success("Ruta publicada", "La ruta recomendada queda disponible para compra.");
    }

    @FXML private void volver() { NavigationUtils.goDashboard((Stage) origenCombo.getScene().getWindow()); }
}
