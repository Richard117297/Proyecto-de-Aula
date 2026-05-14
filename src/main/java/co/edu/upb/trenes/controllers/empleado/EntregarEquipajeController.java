package co.edu.upb.trenes.controllers.empleado;

import co.edu.upb.trenes.models.equipaje.Equipaje;
import co.edu.upb.trenes.services.EquipajeService;
import co.edu.upb.trenes.utils.AlertUtils;
import co.edu.upb.trenes.utils.NavigationUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class EntregarEquipajeController {
    @FXML private ComboBox<Equipaje> equipajeCombo;
    @FXML private Label detalleLabel;
    private final EquipajeService equipajeService = new EquipajeService();

    @FXML private void initialize() {
        equipajeCombo.setItems(FXCollections.observableArrayList(equipajeService.listarEquipajesPendientes()));
        equipajeCombo.setConverter(new StringConverter<>() {
            @Override public String toString(Equipaje equipaje) {
                return equipaje == null ? "" : equipaje.getId() + " - " + equipaje.getBoletoId() + " - " + equipaje.getEstado();
            }
            @Override public Equipaje fromString(String string) { return null; }
        });
        equipajeCombo.valueProperty().addListener((obs, oldValue, equipaje) -> mostrarDetalle(equipaje));
        if (!equipajeCombo.getItems().isEmpty()) {
            equipajeCombo.getSelectionModel().selectFirst();
        }
    }

    @FXML private void entregar() {
        Equipaje equipaje = equipajeCombo.getValue();
        if (equipaje == null) {
            AlertUtils.warning("Sin seleccion", "Seleccione un equipaje pendiente.");
            return;
        }
        equipajeService.entregar(equipaje.getId());
        equipajeCombo.setItems(FXCollections.observableArrayList(equipajeService.listarEquipajesPendientes()));
        AlertUtils.success("Equipaje entregado", "El estado cambio a ENTREGADO.");
    }

    private void mostrarDetalle(Equipaje equipaje) {
        if (equipaje == null) {
            detalleLabel.setText("Seleccione un equipaje para visualizar su informacion.");
            return;
        }
        detalleLabel.setText("ID: " + equipaje.getId()
                + "\nPasajero: " + equipaje.getPasajeroId()
                + "\nBoleto: " + equipaje.getBoletoId()
                + "\nPeso: " + equipaje.getPesoKg() + " kg"
                + "\nEstado: " + equipaje.getEstado()
                + "\nVagon carga: " + (equipaje.getVagonCargaId() == null ? "Sin asignar" : equipaje.getVagonCargaId()));
    }

    @FXML private void volver() { NavigationUtils.goDashboard((Stage) equipajeCombo.getScene().getWindow()); }
}
