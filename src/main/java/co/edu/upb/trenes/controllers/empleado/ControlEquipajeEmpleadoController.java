package co.edu.upb.trenes.controllers.empleado;

import co.edu.upb.trenes.models.boletos.Boleto;
import co.edu.upb.trenes.models.equipaje.Equipaje;
import co.edu.upb.trenes.services.BoletoService;
import co.edu.upb.trenes.services.EquipajeService;
import co.edu.upb.trenes.utils.AlertUtils;
import co.edu.upb.trenes.utils.NavigationUtils;
import co.edu.upb.trenes.utils.ValidationUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.util.List;

public class ControlEquipajeEmpleadoController {
    @FXML private ComboBox<Boleto> boletoCombo;
    @FXML private TextField pesoField;
    @FXML private Label detalleLabel;
    @FXML private Label contadorLabel;

    private final EquipajeService equipajeService = new EquipajeService();
    private final BoletoService boletoService = new BoletoService();

    @FXML private void initialize() {
        pesoField.setTextFormatter(ValidationUtils.decimalFormatter());
        boletoCombo.setItems(FXCollections.observableArrayList(boletoService.listarBoletos()));
        boletoCombo.setConverter(new StringConverter<>() {
            @Override public String toString(Boleto boleto) {
                return boleto == null ? "" : boleto.getId() + " - " + boleto.getNombrePasajero() + " - " + boleto.getOrigenId() + "->" + boleto.getDestinoId() + " - " + boleto.getEstado();
            }
            @Override public Boleto fromString(String string) { return null; }
        });
        boletoCombo.valueProperty().addListener((obs, oldValue, boleto) -> mostrarDetalle(boleto));
        if (!boletoCombo.getItems().isEmpty()) {
            boletoCombo.getSelectionModel().selectFirst();
        }
    }

    @FXML private void registrar() {
        Boleto boleto = boletoCombo.getValue();
        if (boleto == null) {
            AlertUtils.warning("Sin seleccion", "Seleccione un boleto para registrar equipaje.");
            return;
        }
        if (!ValidationUtils.esDecimalValido(pesoField.getText())
                || !ValidationUtils.esPesoEquipajeValido(ValidationUtils.parseDecimal(pesoField.getText()))) {
            AlertUtils.warning("Peso invalido", "El peso del equipaje debe ser mayor a 0 y maximo 80 kg.");
            return;
        }
        equipajeService.registrar(boleto.getPasajeroId(), boleto.getId(), ValidationUtils.parseDecimal(pesoField.getText()));
        mostrarDetalle(boleto);
        AlertUtils.success("Equipaje registrado", "Se validaron cantidad y peso permitidos.");
    }

    private void mostrarDetalle(Boleto boleto) {
        if (boleto == null) {
            detalleLabel.setText("Seleccione un boleto para visualizar pasajero, ruta y equipaje.");
            contadorLabel.setText("Maletas registradas: 0 / 2");
            return;
        }
        List<Equipaje> equipajes = equipajeService.listarPorBoleto(boleto.getId());
        detalleLabel.setText("Pasajero: " + boleto.getNombrePasajero()
                + "\nDocumento: " + boleto.getDocumentoPasajero()
                + "\nRuta: " + boleto.getOrigenId() + " -> " + boleto.getDestinoId()
                + "\nCategoria: " + boleto.getTipoBoleto()
                + "\nEstado boleto: " + boleto.getEstado()
                + "\nMaximo permitido: 80 kg por maleta");
        contadorLabel.setText("Maletas registradas: " + equipajes.size() + " / 2");
    }

    @FXML private void volver() { NavigationUtils.goDashboard((Stage) boletoCombo.getScene().getWindow()); }
}
