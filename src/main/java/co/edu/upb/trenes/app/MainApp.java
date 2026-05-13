package co.edu.upb.trenes.app;

import co.edu.upb.trenes.config.AppConfig;
import co.edu.upb.trenes.repositories.storage.JsonDataInitializer;
import co.edu.upb.trenes.repositories.storage.JsonFileManager;
import co.edu.upb.trenes.utils.NavigationUtils;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        initializeData();
        primaryStage.setTitle("StaffTrain - Sistema de Gestion de Trenes");
        primaryStage.setMinWidth(1100);
        primaryStage.setMinHeight(680);
        primaryStage.setMaximized(true);
        NavigationUtils.changeScene(primaryStage, AppConfig.WELCOME_VIEW, "StaffTrain");
    }

    private void initializeData() {
        new JsonDataInitializer(new JsonFileManager()).initialize();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
