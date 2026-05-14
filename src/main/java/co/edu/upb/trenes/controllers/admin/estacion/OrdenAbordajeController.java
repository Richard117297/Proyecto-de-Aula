package co.edu.upb.trenes.controllers.admin.estacion;

import co.edu.upb.trenes.models.abordaje.PasajeroPrioridad;
import co.edu.upb.trenes.models.boletos.Boleto;
import co.edu.upb.trenes.services.AbordajeService;
import co.edu.upb.trenes.services.BoletoService;
import co.edu.upb.trenes.utils.AlertUtils;
import co.edu.upb.trenes.utils.NavigationUtils;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.List;

public class OrdenAbordajeController {
    @FXML private ListView<String> ordenList;
    private final BoletoService boletoService = new BoletoService();
    private final AbordajeService abordajeService = new AbordajeService();

    @FXML private void publicar() {
        List<PasajeroPrioridad> pasajeros = boletoService.listarBoletos().stream()
                .map(this::toPrioridad)
                .toList();
        ordenList.getItems().setAll(abordajeService.publicarOrden(pasajeros).stream()
                .map(p -> p.getTipoBoleto() + " | vagon " + p.getNumeroVagon() + " | " + p.getNombre())
                .toList());
        AlertUtils.success("Orden publicado", "El orden se guardo en abordajes.json.");
    }

    private PasajeroPrioridad toPrioridad(Boleto boleto) {
        return new PasajeroPrioridad(boleto.getPasajeroId(), boleto.getNombrePasajero(), boleto.getTipoBoleto(), boleto.getNumeroAsiento());
    }

    @FXML private void volver() { NavigationUtils.goDashboard((Stage) ordenList.getScene().getWindow()); }
}
