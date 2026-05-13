package co.edu.upb.trenes.controllers.empleado;

import co.edu.upb.trenes.utils.NavigationUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class EmpleadoDashboardController {
    @FXML private Button validarButton;

    @FXML private void validarBoletos() { go("/co/edu/upb/trenes/views/empleado/validar-boletos.fxml", "Validar boletos"); }
    @FXML private void controlEquipaje() { go("/co/edu/upb/trenes/views/empleado/control-equipaje.fxml", "Control de equipaje"); }
    @FXML private void entregarEquipaje() { go("/co/edu/upb/trenes/views/empleado/entregar-equipaje.fxml", "Entregar equipaje"); }
    @FXML private void listaPasajeros() { go("/co/edu/upb/trenes/views/empleado/lista-pasajeros.fxml", "Lista de pasajeros"); }
    @FXML private void cerrarSesion() { NavigationUtils.logout(stage()); }

    private void go(String path, String title) { NavigationUtils.changeScene(stage(), path, title); }
    private Stage stage() { return (Stage) validarButton.getScene().getWindow(); }
}
