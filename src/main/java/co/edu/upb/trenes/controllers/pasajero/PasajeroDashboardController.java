package co.edu.upb.trenes.controllers.pasajero;

import co.edu.upb.trenes.utils.NavigationUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PasajeroDashboardController {
    @FXML private Button viajarButton;

    @FXML private void viajar() { go("/co/edu/upb/trenes/views/pasajero/comprar-boleto.fxml", "Comprar boleto"); }
    @FXML private void cambiarRuta() { go("/co/edu/upb/trenes/views/pasajero/solicitar-cambio-ruta.fxml", "Solicitar cambio de ruta"); }
    @FXML private void miBoleto() { go("/co/edu/upb/trenes/views/pasajero/mi-boleto.fxml", "Mi boleto"); }
    @FXML private void perfil() { go("/co/edu/upb/trenes/views/pasajero/perfil.fxml", "Mi perfil"); }
    @FXML private void cerrarSesion() { NavigationUtils.logout(stage()); }

    private void go(String path, String title) { NavigationUtils.changeScene(stage(), path, title); }
    private Stage stage() { return (Stage) viajarButton.getScene().getWindow(); }
}
