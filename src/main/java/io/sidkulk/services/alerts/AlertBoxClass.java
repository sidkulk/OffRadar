package io.sidkulk.services.alerts;

import javafx.scene.control.Alert;

public class AlertBoxClass {
    private static Alert alert;

    public static void showErrorAlert(String boxTitle, String message) {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(boxTitle);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void showSuccessAlert(String boxTitle, String message) {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle(boxTitle);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void showConfirmationAlert(String boxTitle, String message) {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle(boxTitle);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
