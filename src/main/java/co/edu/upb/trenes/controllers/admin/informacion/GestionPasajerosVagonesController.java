package co.edu.upb.trenes.controllers.admin.informacion;

import co.edu.upb.trenes.models.trenes.ResultadoCalculoVagones;
import co.edu.upb.trenes.models.trenes.TipoTren;
import co.edu.upb.trenes.services.TrenService;
import co.edu.upb.trenes.utils.NavigationUtils;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GestionPasajerosVagonesController {
    @FXML private ComboBox<TipoTren> tipoCombo;
    @FXML private TextField pasajerosField;
    @FXML private TextField equipajeField;
    @FXML private Label resultadoLabel;

    private final TrenService trenService = new TrenService();

    @FXML private void initialize() {
        tipoCombo.getItems().setAll(TipoTren.values());
        tipoCombo.getSelectionModel().select(TipoTren.ARNOLD);
    }

    @FXML private void calcular() {
        ResultadoCalculoVagones resultado = trenService.calcularCantidadVagones(
                Integer.parseInt(pasajerosField.getText()),
                Integer.parseInt(equipajeField.getText()),
                tipoCombo.getValue());
        resultadoLabel.setText(resultado.getVagonesPasajeros() + " pasajeros, " + resultado.getVagonesCarga()
                + " carga, total " + resultado.getTotalVagones() + ". " + resultado.getMensaje());
    }

    @FXML private void volver() { NavigationUtils.goDashboard((Stage) tipoCombo.getScene().getWindow()); }
}
