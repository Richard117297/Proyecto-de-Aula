package co.edu.upb.trenes.utils;

import co.edu.upb.trenes.config.AppConfig;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public final class NavigationUtils {
    private NavigationUtils() {
    }

    public static void changeScene(Stage stage, String fxmlPath, String title) {
        try {
            Parent root = FXMLLoader.load(NavigationUtils.class.getResource(fxmlPath));
            Scene scene = new Scene(root);
            String css = NavigationUtils.class.getResource(AppConfig.CSS).toExternalForm();
            scene.getStylesheets().add(css);
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        } catch (IOException | NullPointerException e) {
            AlertUtils.error("Error de navegacion", "No se pudo cargar la vista: " + fxmlPath);
        }
    }

    public static void openModal(String fxmlPath, String title) {
        Stage modal = new Stage();
        modal.initModality(Modality.APPLICATION_MODAL);
        changeScene(modal, fxmlPath, title);
    }

    public static void goDashboard(Stage stage) {
        changeScene(stage, AppConfig.DASHBOARD_VIEW, AppConfig.APP_NAME);
    }
}
