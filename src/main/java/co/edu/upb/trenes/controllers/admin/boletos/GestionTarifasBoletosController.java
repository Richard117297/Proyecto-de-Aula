package co.edu.upb.trenes.controllers.admin.boletos;

import co.edu.upb.trenes.models.boletos.EstadoBoleto;
import co.edu.upb.trenes.models.boletos.Boleto;
import co.edu.upb.trenes.services.BoletoService;
import co.edu.upb.trenes.utils.AlertUtils;
import co.edu.upb.trenes.utils.NavigationUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class GestionTarifasBoletosController {
    @FXML private TableView<Boleto> boletosTable;
    @FXML private TableColumn<Boleto, String> idColumn;
    @FXML private TableColumn<Boleto, String> pasajeroColumn;
    @FXML private TableColumn<Boleto, String> rutaColumn;
    @FXML private TableColumn<Boleto, String> categoriaColumn;
    @FXML private TableColumn<Boleto, Double> valorColumn;
    @FXML private Label detalleLabel;

    private final BoletoService boletoService = new BoletoService();

    @FXML private void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        pasajeroColumn.setCellValueFactory(new PropertyValueFactory<>("nombrePasajero"));
        rutaColumn.setCellValueFactory(new PropertyValueFactory<>("rutaId"));
        categoriaColumn.setCellValueFactory(new PropertyValueFactory<>("tipoBoleto"));
        valorColumn.setCellValueFactory(new PropertyValueFactory<>("valorPasaje"));
        boletosTable.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, boleto) -> mostrarDetalle(boleto));
        refrescar();
    }

    @FXML private void registrarBoleto() {
        Boleto boleto = boletosTable.getSelectionModel().getSelectedItem();
        if (boleto == null) {
            AlertUtils.warning("Sin seleccion", "Seleccione un boleto validado.");
            return;
        }
        boletoService.registrar(boleto.getId());
        AlertUtils.success("Boleto registrado", "El boleto validado paso a estado REGISTRADO.");
        refrescar();
    }

    @FXML private void refrescar() {
        boletosTable.setItems(FXCollections.observableArrayList(boletoService.listarBoletos()));
    }

    @FXML private void verValidados() {
        boletosTable.setItems(FXCollections.observableArrayList(boletoService.listarPorEstado(EstadoBoleto.VALIDADO)));
    }

    private void mostrarDetalle(Boleto boleto) {
        if (boleto == null) {
            detalleLabel.setText("Seleccione un boleto para visualizar el registro completo.");
            return;
        }
        detalleLabel.setText("ID registro: " + boleto.getId()
                + "\nEstado: " + boleto.getEstado()
                + "\nPasajero: " + boleto.getNombrePasajero()
                + "\nDocumento: " + boleto.getDocumentoPasajero()
                + "\nRuta: " + boleto.getOrigenId() + " -> " + boleto.getDestinoId()
                + "\nTren: " + boleto.getTrenId()
                + "\nCategoria: " + boleto.getTipoBoleto()
                + "\nValor: $" + Math.round(boleto.getValorPasaje())
                + "\nContacto: " + boleto.getContactoEmergenciaNombre() + " / " + boleto.getContactoEmergenciaTelefono());
    }

    @FXML private void volver() { NavigationUtils.goDashboard((Stage) boletosTable.getScene().getWindow()); }
}
