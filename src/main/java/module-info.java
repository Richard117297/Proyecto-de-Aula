module co.edu.upb.trenes {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;

    opens co.edu.upb.trenes.controllers.auth to javafx.fxml;
    opens co.edu.upb.trenes.controllers.dashboard to javafx.fxml;
    opens co.edu.upb.trenes.controllers.boletos to javafx.fxml;
    opens co.edu.upb.trenes.controllers.trenes to javafx.fxml;
    opens co.edu.upb.trenes.controllers.rutas to javafx.fxml;
    opens co.edu.upb.trenes.controllers.equipaje to javafx.fxml;
    opens co.edu.upb.trenes.controllers.estacion to javafx.fxml;

    opens co.edu.upb.trenes.models.usuarios to com.fasterxml.jackson.databind;
    opens co.edu.upb.trenes.models.boletos to com.fasterxml.jackson.databind;
    opens co.edu.upb.trenes.models.trenes to com.fasterxml.jackson.databind;
    opens co.edu.upb.trenes.models.rutas to com.fasterxml.jackson.databind;
    opens co.edu.upb.trenes.models.equipaje to com.fasterxml.jackson.databind;
    opens co.edu.upb.trenes.models.abordaje to com.fasterxml.jackson.databind;

    exports co.edu.upb.trenes.app;
    exports co.edu.upb.trenes.models.usuarios;
    exports co.edu.upb.trenes.models.boletos;
    exports co.edu.upb.trenes.models.trenes;
    exports co.edu.upb.trenes.models.rutas;
    exports co.edu.upb.trenes.models.equipaje;
    exports co.edu.upb.trenes.models.abordaje;
}
