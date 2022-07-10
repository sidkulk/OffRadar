package io.sidkulk.offradar;

import io.sidkulk.services.database.DatabaseService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    private static DatabaseService databaseServer;
    @Override
    public void start(@SuppressWarnings("exports") Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login-view.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private static void initializeDB() {
        if (databaseServer == null) {
            databaseServer = new DatabaseService();
        }
        databaseServer.connectToDatabase();
    }

    private static void createAllTables() {
        if (databaseServer == null) {
            databaseServer = new DatabaseService();
        }
        databaseServer.createAllTables();
    }

    public static void main(String[] args) {
        initializeDB();
        createAllTables();
        launch(args);
    }
}
