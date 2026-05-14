package co.edu.upb.trenes.controllers.admin.trenes;

import co.edu.upb.trenes.models.trenes.Tren;
import co.edu.upb.trenes.services.TrenService;
import co.edu.upb.trenes.utils.AlertUtils;
import co.edu.upb.trenes.utils.NavigationUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class DarBajaTrenController {
    @FXML private ComboBox<Tren> trenCombo;
    @FXML private Label detalleLabel;

    private final TrenService trenService = new TrenService();

    @FXML private void initialize() {
        trenCombo.setItems(FXCollections.observableArrayList(trenService.listarTrenes()));
        trenCombo.setConverter(new StringConverter<>() {
            @Override public String toString(Tren tren) {
                return tren == null ? "" : tren.getTipoTren() + " - " + tren.getId() + " - " + tren.getEstado();
            }
            @Override public Tren fromString(String string) { return null; }
        });
        trenCombo.valueProperty().addListener((obs, oldValue, tren) -> mostrarDetalle(tren));
        if (!trenCombo.getItems().isEmpty()) {
            trenCombo.getSelectionModel().selectFirst();
        }
    }

    @FXML private void darBaja() {
        Tren trenSeleccionado = trenCombo.getValue();
        if (trenSeleccionado == null) {
            AlertUtils.warning("Sin seleccion", "Seleccione un tren para dar de baja.");
            return;
        }
        if (AlertUtils.confirm("Confirmar baja", "El tren pasara a estado BAJA.")) {
            Tren actualizado = trenService.darDeBaja(trenSeleccionado.getId());
            mostrarDetalle(actualizado);
            trenCombo.setItems(FXCollections.observableArrayList(trenService.listarTrenes()));
            AlertUtils.success("Tren dado de baja", "El cambio fue persistido en JSON.");
        }
    }

    @FXML private void volver() { NavigationUtils.changeScene(stage(), "/co/edu/upb/trenes/views/admin/trenes/gestion-trenes.fxml", "Gestion de trenes"); }
    private void mostrarDetalle(Tren tren) {
        if (tren == null) {
            detalleLabel.setText("Seleccione un tren para visualizar el resumen.");
            return;
        }
        detalleLabel.setText("Tipo: " + tren.getTipoTren()
                + "\nID: " + tren.getId()
                + "\nEstado actual: " + tren.getEstado()
                + "\nKilometraje: " + tren.getKilometraje() + " km"
                + "\nCapacidad: " + tren.totalVagones() + " vagones"
                + "\nRuta asignada: " + (tren.getRutaAsignadaId() == null ? "Sin ruta" : tren.getRutaAsignadaId()));
    }
    private Stage stage() { return (Stage) trenCombo.getScene().getWindow(); }
}
