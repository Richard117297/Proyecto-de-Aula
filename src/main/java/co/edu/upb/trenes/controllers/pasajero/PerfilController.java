package co.edu.upb.trenes.controllers.pasajero;

import co.edu.upb.trenes.services.UsuarioService;
import co.edu.upb.trenes.utils.AlertUtils;
import co.edu.upb.trenes.utils.NavigationUtils;
import co.edu.upb.trenes.utils.SessionManager;
import co.edu.upb.trenes.utils.ValidationUtils;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PerfilController {
    @FXML private TextField nombreField;
    @FXML private TextField documentoField;
    @FXML private TextField telefonoField;

    private final UsuarioService usuarioService = new UsuarioService();

    @FXML private void initialize() {
        nombreField.setTextFormatter(ValidationUtils.soloLetrasFormatter());
        documentoField.setTextFormatter(ValidationUtils.soloNumerosFormatter());
        telefonoField.setTextFormatter(ValidationUtils.soloNumerosFormatter());
        nombreField.setText(SessionManager.nombre());
    }

    @FXML private void guardar() {
        if (!ValidationUtils.esTextoValido(nombreField.getText())) {
            AlertUtils.warning("Nombre invalido", "El nombre no debe contener numeros.");
            return;
        }
        SessionManager.usuarioActual().ifPresent(usuario -> {
            SessionManager.iniciarSesion(usuarioService.actualizarPerfilBasico(usuario, nombreField.getText()));
            AlertUtils.success("Perfil actualizado", "Se guardaron los datos basicos.");
        });
    }

    @FXML private void volver() { NavigationUtils.goDashboard((Stage) nombreField.getScene().getWindow()); }
}
