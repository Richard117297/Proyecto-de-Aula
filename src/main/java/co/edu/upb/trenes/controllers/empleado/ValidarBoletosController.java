package co.edu.upb.trenes.controllers.empleado;

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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ValidarBoletosController {
    @FXML private TableView<Boleto> boletosTable;
    @FXML private TableColumn<Boleto, String> idColumn;
    @FXML private TableColumn<Boleto, String> pasajeroColumn;
    @FXML private TableColumn<Boleto, String> origenColumn;
    @FXML private TableColumn<Boleto, String> destinoColumn;
    @FXML private TableColumn<Boleto, String> categoriaColumn;
    @FXML private TableColumn<Boleto, Double> valorColumn;
    @FXML private TableColumn<Boleto, String> estadoColumn;
    @FXML private Label detalleLabel;
    @FXML private TextField motivoField;

    private final BoletoService boletoService = new BoletoService();

    @FXML private void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        pasajeroColumn.setCellValueFactory(new PropertyValueFactory<>("nombrePasajero"));
        origenColumn.setCellValueFactory(new PropertyValueFactory<>("origenId"));
        destinoColumn.setCellValueFactory(new PropertyValueFactory<>("destinoId"));
        categoriaColumn.setCellValueFactory(new PropertyValueFactory<>("tipoBoleto"));
        valorColumn.setCellValueFactory(new PropertyValueFactory<>("valorPasaje"));
        estadoColumn.setCellValueFactory(new PropertyValueFactory<>("estado"));
        boletosTable.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, boleto) -> mostrarDetalle(boleto));
        refrescar();
    }

    @FXML private void validar() {
        Boleto boleto = boletoSeleccionado();
        if (boleto == null) return;
        boletoService.validar(boleto.getId());
        AlertUtils.success("Boleto validado", "El boleto queda disponible para registro administrativo.");
        refrescar();
    }

    @FXML private void rechazar() {
        Boleto boleto = boletoSeleccionado();
        if (boleto == null) return;
        boletoService.rechazar(boleto.getId(), motivoField.getText());
        AlertUtils.warning("Boleto rechazado", "El pasajero podra ver el estado rechazado.");
        refrescar();
    }

    private void refrescar() {
        boletosTable.setItems(FXCollections.observableArrayList(boletoService.listarPorEstado(EstadoBoleto.PENDIENTE_VALIDACION)));
    }

    private Boleto boletoSeleccionado() {
        Boleto boleto = boletosTable.getSelectionModel().getSelectedItem();
        if (boleto == null) {
            AlertUtils.warning("Sin seleccion", "Seleccione un boleto de la tabla.");
        }
        return boleto;
    }

    private void mostrarDetalle(Boleto boleto) {
        if (boleto == null) {
            detalleLabel.setText("Seleccione un boleto para visualizar su informacion completa.");
            return;
        }
        detalleLabel.setText("ID: " + boleto.getId()
                + "\nPasajero: " + boleto.getNombrePasajero()
                + "\nDocumento: " + boleto.getDocumentoPasajero()
                + "\nTelefono: " + boleto.getTelefonoPasajero()
                + "\nRuta: " + boleto.getOrigenId() + " -> " + boleto.getDestinoId()
                + "\nCategoria: " + boleto.getTipoBoleto()
                + "\nValor: $" + Math.round(boleto.getValorPasaje())
                + "\nContacto: " + boleto.getContactoEmergenciaNombre() + " / " + boleto.getContactoEmergenciaTelefono());
    }

    @FXML private void volver() { NavigationUtils.goDashboard((Stage) boletosTable.getScene().getWindow()); }
}
