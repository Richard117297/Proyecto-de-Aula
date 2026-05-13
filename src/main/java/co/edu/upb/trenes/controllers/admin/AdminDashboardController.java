package co.edu.upb.trenes.controllers.admin;

import co.edu.upb.trenes.config.AppConfig;
import co.edu.upb.trenes.utils.NavigationUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdminDashboardController {
    @FXML
    private Button trenesButton;

    @FXML private void gestionTrenes() { go("/co/edu/upb/trenes/views/admin/trenes/gestion-trenes.fxml", "Gestion de trenes"); }
    @FXML private void tarifasBoletos() { go("/co/edu/upb/trenes/views/admin/boletos/gestion-tarifas-boletos.fxml", "Tarifas y boletos"); }
    @FXML private void informacionTrenes() { go("/co/edu/upb/trenes/views/admin/trenes/informacion-trenes.fxml", "Informacion de trenes"); }
    @FXML private void rutas() { go("/co/edu/upb/trenes/views/admin/rutas/gestion-rutas-admin.fxml", "Gestion de rutas"); }
    @FXML private void pasajerosVagones() { go("/co/edu/upb/trenes/views/admin/informacion/gestion-pasajeros-vagones.fxml", "Pasajeros y vagones"); }
    @FXML private void ordenAbordaje() { go("/co/edu/upb/trenes/views/admin/estacion/orden-abordaje.fxml", "Orden de abordaje"); }
    @FXML private void cerrarSesion() { NavigationUtils.logout(stage()); }

    private void go(String path, String title) {
        NavigationUtils.changeScene(stage(), path, title);
    }

    private Stage stage() {
        return (Stage) trenesButton.getScene().getWindow();
    }
}
