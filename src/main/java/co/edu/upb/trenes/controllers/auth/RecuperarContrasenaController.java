package co.edu.upb.trenes.controllers.auth;

import co.edu.upb.trenes.config.AppConfig;
import co.edu.upb.trenes.repositories.impl.UsuarioJsonRepository;
import co.edu.upb.trenes.utils.AlertUtils;
import co.edu.upb.trenes.utils.NavigationUtils;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RecuperarContrasenaController {
    @FXML
    private TextField usuarioField;

    private final UsuarioJsonRepository usuarioRepository = new UsuarioJsonRepository();

    @FXML
    private void buscarUsuario() {
        usuarioRepository.findByUsuario(usuarioField.getText())
                .ifPresentOrElse(
                        usuario -> AlertUtils.info("Usuario encontrado", "Solicite al administrador el restablecimiento de acceso."),
                        () -> AlertUtils.warning("Sin resultados", "No se encontro un usuario con ese dato.")
                );
    }

    @FXML
    private void volver() {
        NavigationUtils.changeScene((Stage) usuarioField.getScene().getWindow(), AppConfig.LOGIN_VIEW, "StaffTrain - Inicio de sesion");
    }
}
