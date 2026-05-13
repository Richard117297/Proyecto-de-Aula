package co.edu.upb.trenes.controllers.admin.trenes;

import co.edu.upb.trenes.models.trenes.TipoTren;
import co.edu.upb.trenes.models.trenes.Tren;
import co.edu.upb.trenes.services.TrenService;
import co.edu.upb.trenes.utils.AlertUtils;
import co.edu.upb.trenes.utils.NavigationUtils;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.UUID;

public class AgregarTrenController {
    @FXML private ComboBox<TipoTren> tipoCombo;
    @FXML private TextField capacidadField;
    @FXML private TextField kilometrajeField;

    private final TrenService trenService = new TrenService();

    @FXML private void initialize() {
        tipoCombo.getItems().setAll(TipoTren.values());
        tipoCombo.getSelectionModel().select(TipoTren.ARNOLD);
    }

    @FXML private void agregar() {
        TipoTren tipo = tipoCombo.getValue();
        int capacidad = Integer.parseInt(capacidadField.getText());
        int pasajeros = Math.max(1, capacidad - (int) Math.ceil(capacidad / 3.0));
        Tren tren = new Tren("tren-" + UUID.randomUUID(), tipo.name() + " Nuevo", tipo);
        tren.setVagonesPasajeros(pasajeros);
        tren.setVagonesCarga(capacidad - pasajeros);
        tren.setKilometraje(Double.parseDouble(kilometrajeField.getText()));
        trenService.agregarTren(tren);
        AlertUtils.success("Tren agregado", "El tren fue guardado en trenes.json.");
    }

    @FXML private void volver() { NavigationUtils.changeScene(stage(), "/co/edu/upb/trenes/views/admin/trenes/gestion-trenes.fxml", "Gestion de trenes"); }
    private Stage stage() { return (Stage) tipoCombo.getScene().getWindow(); }
}
