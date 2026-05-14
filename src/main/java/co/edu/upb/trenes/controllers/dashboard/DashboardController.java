package co.edu.upb.trenes.controllers.dashboard;

import co.edu.upb.trenes.utils.AlertUtils;
import javafx.fxml.FXML;

public class DashboardController {
    @FXML
    private void abrirModuloPendiente() {
        AlertUtils.info("Modulo preparado", "La arquitectura base ya esta lista para conectar esta pantalla.");
    }
}
