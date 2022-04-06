package io.sidkulk.screens;

import io.sidkulk.offradar.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowChangeRoutine {
    public static void showLoginScreen() {
        Parent root = null;
        try {
            root = FXMLLoader.load(Main.class.getResource("login-view.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void showHomeScreen(AnchorPane anchorPane) {
        Parent root = null;
        try {
            root = FXMLLoader.load(Main.class.getResource("home-view.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setTitle("Home");
        stage.setScene(scene);
        stage.show();
    }

    public static void showLoginPage(AnchorPane rootpane) {
        Parent root = null;
        try {
            root = FXMLLoader.load(Main.class.getResource("login-view.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) rootpane.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void showRegisterPage(AnchorPane rootpane) {
        Parent root = null;
        try {
            root = FXMLLoader.load(Main.class.getResource("register-view.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) rootpane.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setTitle("Register");
        stage.setScene(scene);
        stage.show();
    }
}
