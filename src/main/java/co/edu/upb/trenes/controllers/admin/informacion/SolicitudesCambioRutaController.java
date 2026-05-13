package co.edu.upb.trenes.controllers.admin.informacion;

import co.edu.upb.trenes.services.RutaService;
import co.edu.upb.trenes.utils.NavigationUtils;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class SolicitudesCambioRutaController {
    @FXML private ListView<String> solicitudesList;
    private final RutaService rutaService = new RutaService();

    @FXML private void initialize() {
        solicitudesList.getItems().setAll(rutaService.listarSolicitudes().stream()
                .map(s -> s.getId() + " | boleto " + s.getBoletoId() + " | " + s.getEstado())
                .toList());
    }

    @FXML private void volver() { NavigationUtils.goDashboard((Stage) solicitudesList.getScene().getWindow()); }
}
