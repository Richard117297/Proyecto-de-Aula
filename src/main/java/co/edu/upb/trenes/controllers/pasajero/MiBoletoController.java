package co.edu.upb.trenes.controllers.pasajero;

import co.edu.upb.trenes.models.boletos.Boleto;
import co.edu.upb.trenes.services.BoletoService;
import co.edu.upb.trenes.utils.NavigationUtils;
import co.edu.upb.trenes.utils.SessionManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MiBoletoController {
    @FXML private TableView<Boleto> boletosTable;
    @FXML private TableColumn<Boleto, String> idColumn;
    @FXML private TableColumn<Boleto, String> estadoColumn;
    @FXML private TableColumn<Boleto, String> origenColumn;
    @FXML private TableColumn<Boleto, String> destinoColumn;
    @FXML private TableColumn<Boleto, Double> valorColumn;
    @FXML private Label detalleLabel;
    private final BoletoService boletoService = new BoletoService();

    @FXML private void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        estadoColumn.setCellValueFactory(new PropertyValueFactory<>("estado"));
        origenColumn.setCellValueFactory(new PropertyValueFactory<>("origenId"));
        destinoColumn.setCellValueFactory(new PropertyValueFactory<>("destinoId"));
        valorColumn.setCellValueFactory(new PropertyValueFactory<>("valorPasaje"));
        boletosTable.setItems(FXCollections.observableArrayList(boletoService.listarBoletosPorPasajero(SessionManager.idUsuario())));
        boletosTable.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, boleto) -> mostrarDetalle(boleto));
    }

    private void mostrarDetalle(Boleto boleto) {
        if (boleto == null) {
            detalleLabel.setText("Seleccione un boleto para visualizar su estado y detalle.");
            return;
        }
        detalleLabel.setText("Estado: " + boleto.getEstado()
                + "\nID: " + boleto.getId()
                + "\nRuta: " + boleto.getOrigenId() + " -> " + boleto.getDestinoId()
                + "\nTren: " + boleto.getTrenId()
                + "\nCategoria: " + boleto.getTipoBoleto()
                + "\nDistancia: " + boleto.getDistanciaKm() + " km"
                + "\nValor: $" + Math.round(boleto.getValorPasaje())
                + "\nContacto: " + boleto.getContactoEmergenciaNombre() + " / " + boleto.getContactoEmergenciaTelefono());
    }

    @FXML private void volver() { NavigationUtils.goDashboard((Stage) boletosTable.getScene().getWindow()); }
}
