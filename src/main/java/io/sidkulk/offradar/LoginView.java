package io.sidkulk.offradar;

import io.sidkulk.screens.WindowChangeRoutine;
import io.sidkulk.services.alerts.AlertBoxClass;
import io.sidkulk.services.authservice.UserAuthService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LoginView {
    private UserAuthService userAuthService;

    @FXML
    private AnchorPane loginAnchorPane;

    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField pwdTxt;

    @FXML
    private Button registerBtn;

    @FXML
    private TextField usernameTxt;

    @FXML
    void goToRegisterPage(ActionEvent event) throws Exception {
        if (event.getSource() == registerBtn) {
            WindowChangeRoutine.showRegisterPage(loginAnchorPane);
        } else {
            WindowChangeRoutine.showLoginScreen();
        }
    }

    @FXML
    void goToRecoveryPage(ActionEvent event) {
        WindowChangeRoutine.showRecoveryPage(loginAnchorPane);
    }

    @FXML
    void logUserIn(ActionEvent event) {
        if (userAuthService == null) {
            userAuthService = new UserAuthService();
        }
        boolean isUserValid = userAuthService.verifyUserLogin(usernameTxt.getText(), pwdTxt.getText());
        if (isUserValid) {
            WindowChangeRoutine.showHomeScreen(loginAnchorPane);
        } else {
            // show error...
            AlertBoxClass.showErrorAlert("Login Error", "User with username: " + usernameTxt.getText() + " Not found!");
        }
    }
}
