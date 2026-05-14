package co.edu.upb.trenes.controllers.admin.trenes;

import co.edu.upb.trenes.utils.NavigationUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GestionTrenesAdminController {
    @FXML private Button volverButton;

    @FXML private void agregarTren() { go("/co/edu/upb/trenes/views/admin/trenes/agregar-tren.fxml", "Agregar tren"); }
    @FXML private void verificarTren() { go("/co/edu/upb/trenes/views/admin/trenes/verificar-tren.fxml", "Verificar tren"); }
    @FXML private void darBajaTren() { go("/co/edu/upb/trenes/views/admin/trenes/dar-baja-tren.fxml", "Dar de baja tren"); }
    @FXML private void informacionTrenes() { go("/co/edu/upb/trenes/views/admin/trenes/informacion-trenes.fxml", "Informacion de trenes"); }
    @FXML private void volver() { NavigationUtils.goDashboard(stage()); }

    private void go(String path, String title) { NavigationUtils.changeScene(stage(), path, title); }
    private Stage stage() { return (Stage) volverButton.getScene().getWindow(); }
}
