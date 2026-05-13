package co.edu.upb.trenes.utils;

import javafx.scene.control.Alert;

public final class AlertUtils {
    private AlertUtils() {
    }

    public static void info(String title, String message) {
        show(Alert.AlertType.INFORMATION, title, message);
    }

    public static void error(String title, String message) {
        show(Alert.AlertType.ERROR, title, message);
    }

    private static void show(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
