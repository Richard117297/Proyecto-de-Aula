package co.edu.upb.trenes.controllers.bienvenida;

import co.edu.upb.trenes.config.AppConfig;
import co.edu.upb.trenes.utils.AlertUtils;
import co.edu.upb.trenes.utils.NavigationUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class BienvenidaController {
    @FXML
    private Button loginButton;

    @FXML
    private void iniciarSesion() {
        NavigationUtils.changeScene((Stage) loginButton.getScene().getWindow(), AppConfig.LOGIN_VIEW, "StaffTrain - Inicio de sesion");
    }

    @FXML
    private void conocerModulos() {
        AlertUtils.info("Modulos StaffTrain", "Pasajeros, empleados y administradores trabajan conectados con boletos, rutas, trenes, equipaje y abordaje.");
    }
}
