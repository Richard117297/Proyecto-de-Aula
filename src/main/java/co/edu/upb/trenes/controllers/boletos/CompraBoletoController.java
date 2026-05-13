package co.edu.upb.trenes.controllers.boletos;

import co.edu.upb.trenes.models.boletos.TipoBoleto;
import co.edu.upb.trenes.services.BoletoService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CompraBoletoController {
    @FXML
    private Label estadoLabel;

    private final BoletoService boletoService = new BoletoService();

    @FXML
    private void crearBoletoDemo() {
        boletoService.crearBoleto("pasajero-001", "ruta-demo", "tren-demo", TipoBoleto.ESTANDAR);
        estadoLabel.setText("Boleto de prueba creado en JSON.");
    }
}
