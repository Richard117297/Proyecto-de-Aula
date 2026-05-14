package co.edu.upb.trenes.controllers.auth;

import co.edu.upb.trenes.config.AppConfig;
import co.edu.upb.trenes.exceptions.BusinessException;
import co.edu.upb.trenes.services.AuthService;
import co.edu.upb.trenes.utils.AlertUtils;
import co.edu.upb.trenes.utils.NavigationUtils;
import co.edu.upb.trenes.utils.SessionManager;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private TextField usuarioField;
    @FXML
    private PasswordField contrasenaField;
    @FXML
    private ComboBox<String> rolCombo;

    private final AuthService authService = new AuthService();

    @FXML
    private void initialize() {
        rolCombo.getItems().setAll("PASAJERO", "EMPLEADO", "ADMINISTRADOR");
        rolCombo.getSelectionModel().select("PASAJERO");
    }

    @FXML
    private void iniciarSesion() {
        try {
            authService.login(usuarioField.getText(), contrasenaField.getText())
                    .filter(usuario -> usuario.getRol().name().equals(rolCombo.getValue()))
                    .ifPresentOrElse(
                            usuario -> {
                                SessionManager.iniciarSesion(usuario);
                                NavigationUtils.goDashboard((Stage) usuarioField.getScene().getWindow());
                            },
                            () -> AlertUtils.error("Inicio de sesion", "Credenciales invalidas, rol incorrecto o usuario inactivo.")
                    );
        } catch (BusinessException e) {
            AlertUtils.error(AppConfig.APP_NAME, e.getMessage());
        }
    }

    @FXML
    private void volver() {
        NavigationUtils.changeScene((Stage) usuarioField.getScene().getWindow(), AppConfig.WELCOME_VIEW, "StaffTrain");
    }

    @FXML
    private void recuperarContrasena() {
        NavigationUtils.changeScene((Stage) usuarioField.getScene().getWindow(), "/co/edu/upb/trenes/views/auth/recuperar-contrasena.fxml", "Recuperar acceso");
    }
}
