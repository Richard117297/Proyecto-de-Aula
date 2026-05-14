package co.edu.upb.trenes.controllers.admin.trenes;

import co.edu.upb.trenes.config.JsonStorageConfig;
import co.edu.upb.trenes.models.config.ConfiguracionSistema;
import co.edu.upb.trenes.models.trenes.Tren;
import co.edu.upb.trenes.repositories.storage.JsonFileManager;
import co.edu.upb.trenes.services.TrenService;
import co.edu.upb.trenes.utils.AlertUtils;
import co.edu.upb.trenes.utils.NavigationUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class VerificarTrenController {
    @FXML private ComboBox<Tren> trenCombo;
    @FXML private TextField kilometrajeField;
    @FXML private Label detalleLabel;

    private final TrenService trenService = new TrenService();

    @FXML private void initialize() {
        trenCombo.setItems(FXCollections.observableArrayList(trenService.listarTrenes()));
        trenCombo.setConverter(new StringConverter<>() {
            @Override public String toString(Tren tren) {
                return tren == null ? "" : tren.getTipoTren() + " - " + tren.getId() + " - " + tren.getEstado() + " - " + tren.getKilometraje() + " km";
            }
            @Override public Tren fromString(String string) { return null; }
        });
        trenCombo.valueProperty().addListener((obs, oldValue, tren) -> mostrarDetalle(tren));
        if (!trenCombo.getItems().isEmpty()) {
            trenCombo.getSelectionModel().selectFirst();
        }
    }

    @FXML private void verificar() {
        Tren trenSeleccionado = trenCombo.getValue();
        if (trenSeleccionado == null) {
            AlertUtils.warning("Sin seleccion", "Seleccione un tren para verificar.");
            return;
        }
        ConfiguracionSistema config = new JsonFileManager().readObject(JsonStorageConfig.resolve("configuracion.json"), ConfiguracionSistema.class);
        Tren actualizado = trenService.verificar(trenSeleccionado.getId(), Double.parseDouble(kilometrajeField.getText()), config.getKilometrajeMaximoOperacion());
        mostrarDetalle(actualizado);
        trenCombo.setItems(FXCollections.observableArrayList(trenService.listarTrenes()));
        AlertUtils.success("Verificacion registrada", "El kilometraje fue actualizado y el estado se recalculo.");
    }

    @FXML private void volver() { NavigationUtils.changeScene(stage(), "/co/edu/upb/trenes/views/admin/trenes/gestion-trenes.fxml", "Gestion de trenes"); }
    private void mostrarDetalle(Tren tren) {
        if (tren == null) {
            detalleLabel.setText("Seleccione un tren para visualizar su informacion.");
            return;
        }
        detalleLabel.setText("ID: " + tren.getId()
                + "\nTipo: " + tren.getTipoTren()
                + "\nEstado: " + tren.getEstado()
                + "\nKilometraje: " + tren.getKilometraje() + " km"
                + "\nCapacidad: " + tren.totalVagones() + " vagones"
                + "\nRuta asignada: " + (tren.getRutaAsignadaId() == null ? "Sin ruta" : tren.getRutaAsignadaId()));
    }
    private Stage stage() { return (Stage) trenCombo.getScene().getWindow(); }
}
