package co.edu.upb.trenes.controllers.auth;

import co.edu.upb.trenes.config.AppConfig;
import co.edu.upb.trenes.exceptions.BusinessException;
import co.edu.upb.trenes.services.AuthService;
import co.edu.upb.trenes.utils.AlertUtils;
import co.edu.upb.trenes.utils.NavigationUtils;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private TextField usuarioField;
    @FXML
    private PasswordField contrasenaField;

    private final AuthService authService = new AuthService();

    @FXML
    private void iniciarSesion() {
        try {
            authService.login(usuarioField.getText(), contrasenaField.getText())
                    .ifPresentOrElse(
                            usuario -> NavigationUtils.goDashboard((Stage) usuarioField.getScene().getWindow()),
                            () -> AlertUtils.error("Inicio de sesion", "Credenciales invalidas o usuario inactivo.")
                    );
        } catch (BusinessException e) {
            AlertUtils.error(AppConfig.APP_NAME, e.getMessage());
        }
    }
}
