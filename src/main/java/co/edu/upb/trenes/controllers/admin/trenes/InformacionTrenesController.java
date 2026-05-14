package co.edu.upb.trenes.controllers.admin.trenes;

import co.edu.upb.trenes.services.TrenService;
import co.edu.upb.trenes.utils.NavigationUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class InformacionTrenesController {
    @FXML private TableView<co.edu.upb.trenes.models.trenes.Tren> trenesTable;
    @FXML private TableColumn<co.edu.upb.trenes.models.trenes.Tren, String> idColumn;
    @FXML private TableColumn<co.edu.upb.trenes.models.trenes.Tren, String> tipoColumn;
    @FXML private TableColumn<co.edu.upb.trenes.models.trenes.Tren, String> estadoColumn;
    @FXML private TableColumn<co.edu.upb.trenes.models.trenes.Tren, Double> kilometrajeColumn;
    @FXML private TableColumn<co.edu.upb.trenes.models.trenes.Tren, String> rutaColumn;
    @FXML private Label detalleLabel;
    private final TrenService trenService = new TrenService();

    @FXML private void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        tipoColumn.setCellValueFactory(new PropertyValueFactory<>("tipoTren"));
        estadoColumn.setCellValueFactory(new PropertyValueFactory<>("estado"));
        kilometrajeColumn.setCellValueFactory(new PropertyValueFactory<>("kilometraje"));
        rutaColumn.setCellValueFactory(new PropertyValueFactory<>("rutaAsignadaId"));
        trenesTable.setItems(FXCollections.observableArrayList(trenService.listarTrenes()));
        trenesTable.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, tren) -> mostrarDetalle(tren));
    }

    private void mostrarDetalle(co.edu.upb.trenes.models.trenes.Tren tren) {
        if (tren == null) {
            detalleLabel.setText("Seleccione un tren para visualizar su informacion.");
            return;
        }
        detalleLabel.setText("ID: " + tren.getId()
                + "\nNombre: " + tren.getNombre()
                + "\nTipo: " + tren.getTipoTren()
                + "\nEstado: " + tren.getEstado()
                + "\nKilometraje: " + tren.getKilometraje() + " km"
                + "\nPasajeros: " + tren.getVagonesPasajeros() + " vagones"
                + "\nCarga: " + tren.getVagonesCarga() + " vagones"
                + "\nRuta: " + (tren.getRutaAsignadaId() == null ? "Sin ruta" : tren.getRutaAsignadaId()));
    }

    @FXML private void volver() { NavigationUtils.goDashboard((Stage) trenesTable.getScene().getWindow()); }
}
