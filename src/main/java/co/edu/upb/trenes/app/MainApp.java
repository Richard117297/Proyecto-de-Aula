package co.edu.upb.trenes.app;

import co.edu.upb.trenes.config.AppConfig;
import co.edu.upb.trenes.models.usuarios.RolUsuario;
import co.edu.upb.trenes.models.usuarios.Usuario;
import co.edu.upb.trenes.repositories.impl.UsuarioJsonRepository;
import co.edu.upb.trenes.repositories.storage.JsonDataInitializer;
import co.edu.upb.trenes.repositories.storage.JsonFileManager;
import co.edu.upb.trenes.utils.NavigationUtils;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        initializeData();
        NavigationUtils.changeScene(primaryStage, AppConfig.LOGIN_VIEW, AppConfig.APP_NAME);
    }

    private void initializeData() {
        new JsonDataInitializer(new JsonFileManager()).initialize();
        UsuarioJsonRepository usuarios = new UsuarioJsonRepository();
        if (usuarios.findAll().isEmpty()) {
            usuarios.save(new Usuario("admin-001", "admin", "admin123", RolUsuario.ADMINISTRADOR, "Administrador Principal", true));
            usuarios.save(new Usuario("empleado-001", "empleado", "empleado123", RolUsuario.EMPLEADO, "Empleado Principal", true));
            usuarios.save(new Usuario("pasajero-001", "pasajero", "pasajero123", RolUsuario.PASAJERO, "Pasajero Principal", true));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
