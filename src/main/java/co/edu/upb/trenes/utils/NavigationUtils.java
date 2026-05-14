package co.edu.upb.trenes.utils;

import co.edu.upb.trenes.config.AppConfig;
import co.edu.upb.trenes.models.usuarios.RolUsuario;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public final class NavigationUtils {
    private NavigationUtils() {
    }

    public static void changeScene(Stage stage, String fxmlPath, String title) {
        try {
            boolean maximized = stage.isMaximized();
            double width = stage.getWidth() > 0 ? stage.getWidth() : 1280;
            double height = stage.getHeight() > 0 ? stage.getHeight() : 780;
            Parent root = FXMLLoader.load(NavigationUtils.class.getResource(fxmlPath));
            Scene scene = new Scene(wrapWithScrollIfNeeded(root));
            String css = NavigationUtils.class.getResource(AppConfig.CSS).toExternalForm();
            scene.getStylesheets().add(css);
            stage.setTitle(title);
            stage.setMinWidth(1100);
            stage.setMinHeight(680);
            stage.setScene(scene);
            if (maximized) {
                stage.setMaximized(true);
            } else {
                stage.setWidth(Math.max(width, 1100));
                stage.setHeight(Math.max(height, 680));
            }
            stage.setMaximized(true);
            stage.show();
        } catch (IOException | NullPointerException e) {
            AlertUtils.error("Error de navegacion", "No se pudo cargar la vista: " + fxmlPath);
        }
    }

    private static Parent wrapWithScrollIfNeeded(Parent root) {
        if (root instanceof ScrollPane) {
            return root;
        }
        if (root instanceof BorderPane borderPane && borderPane.getCenter() instanceof ScrollPane) {
            return root;
        }
        StackPane wrapper = new StackPane(root);
        wrapper.setMinWidth(1120);
        wrapper.setPrefWidth(1280);
        wrapper.setMinHeight(680);
        wrapper.getStyleClass().add("scroll-center-wrapper");
        ScrollPane scrollPane = new ScrollPane(wrapper);
        scrollPane.setFitToWidth(false);
        scrollPane.setFitToHeight(false);
        scrollPane.setPannable(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.getStyleClass().add("app-scroll-pane");
        return scrollPane;
    }

    public static void openModal(String fxmlPath, String title) {
        Stage modal = new Stage();
        modal.initModality(Modality.APPLICATION_MODAL);
        changeScene(modal, fxmlPath, title);
    }

    public static void goDashboard(Stage stage) {
        RolUsuario rol = SessionManager.rol();
        if (rol == RolUsuario.ADMINISTRADOR) {
            changeScene(stage, AppConfig.ADMIN_DASHBOARD_VIEW, "StaffTrain - Administrador");
        } else if (rol == RolUsuario.EMPLEADO) {
            changeScene(stage, AppConfig.EMPLEADO_DASHBOARD_VIEW, "StaffTrain - Empleado");
        } else {
            changeScene(stage, AppConfig.PASAJERO_DASHBOARD_VIEW, "StaffTrain - Pasajero");
        }
    }

    public static void logout(Stage stage) {
        SessionManager.cerrarSesion();
        changeScene(stage, AppConfig.LOGIN_VIEW, "StaffTrain - Inicio de sesion");
    }
}
