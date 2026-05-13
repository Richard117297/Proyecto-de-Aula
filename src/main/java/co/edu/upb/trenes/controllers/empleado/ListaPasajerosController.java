package co.edu.upb.trenes.controllers.empleado;

import co.edu.upb.trenes.models.boletos.Boleto;
import co.edu.upb.trenes.services.BoletoService;
import co.edu.upb.trenes.utils.NavigationUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ListaPasajerosController {
    @FXML private TableView<Boleto> pasajerosTable;
    @FXML private TableColumn<Boleto, String> documentoColumn;
    @FXML private TableColumn<Boleto, String> nombreColumn;
    @FXML private TableColumn<Boleto, String> telefonoColumn;
    @FXML private TableColumn<Boleto, String> boletoColumn;
    @FXML private TableColumn<Boleto, String> categoriaColumn;
    @FXML private TableColumn<Boleto, String> estadoColumn;
    @FXML private Label detalleLabel;
    private final BoletoService boletoService = new BoletoService();

    @FXML private void initialize() {
        documentoColumn.setCellValueFactory(new PropertyValueFactory<>("documentoPasajero"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombrePasajero"));
        telefonoColumn.setCellValueFactory(new PropertyValueFactory<>("telefonoPasajero"));
        boletoColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        categoriaColumn.setCellValueFactory(new PropertyValueFactory<>("tipoBoleto"));
        estadoColumn.setCellValueFactory(new PropertyValueFactory<>("estado"));
        pasajerosTable.setItems(FXCollections.observableArrayList(boletoService.listarBoletos()));
        pasajerosTable.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, boleto) -> mostrarDetalle(boleto));
    }

    private void mostrarDetalle(Boleto boleto) {
        if (boleto == null) {
            detalleLabel.setText("Seleccione un pasajero/boleto para visualizar trazabilidad.");
            return;
        }
        detalleLabel.setText("Pasajero: " + boleto.getNombrePasajero()
                + "\nDocumento: " + boleto.getDocumentoPasajero()
                + "\nTelefono: " + boleto.getTelefonoPasajero()
                + "\nBoleto: " + boleto.getId()
                + "\nCategoria: " + boleto.getTipoBoleto()
                + "\nEstado: " + boleto.getEstado()
                + "\nTren: " + boleto.getTrenId()
                + "\nRuta: " + boleto.getOrigenId() + " -> " + boleto.getDestinoId());
    }

    @FXML private void volver() { NavigationUtils.goDashboard((Stage) pasajerosTable.getScene().getWindow()); }
}
