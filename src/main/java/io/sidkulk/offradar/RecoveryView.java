package io.sidkulk.offradar;

import io.sidkulk.screens.WindowChangeRoutine;
import io.sidkulk.services.alerts.AlertBoxClass;
import io.sidkulk.services.authservice.UserAuthService;
import io.sidkulk.services.recovery.UserRecoveryService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class RecoveryView {
    private UserAuthService userAuthService;
    @FXML
    private Button logUserInBtn;

    @FXML
    private TextField nicknameTxt;

    @FXML
    private Button recoverAccBtn;

    @FXML
    private TextField recoveredPassword;

    @FXML
    private TextField recoveredUsername;

    @FXML
    private TextField schoolnameTxt;

    @FXML
    private AnchorPane recoveryAnchorRoot;

    @FXML
    void recoverUserDetails(ActionEvent event) {
        if(nicknameTxt.getText().isEmpty() || schoolnameTxt.getText().isEmpty()) {
            AlertBoxClass.showErrorAlert("Error", "You left account recovery field(s) blank! Fill all the fields.");
        } else {
            boolean isUserPresentInDatabase = UserRecoveryService.doesAccountExists(nicknameTxt.getText(), schoolnameTxt.getText());
            if(isUserPresentInDatabase) {
                recoveredUsername.setText(UserRecoveryService.getRecoverdUsername());
                recoveredPassword.setText(UserRecoveryService.getRecoveredPasswordHash());
                logUserInBtn.setDisable(false);
            } else {
                AlertBoxClass.showErrorAlert("ERROR", "User with nickname: " + nicknameTxt.getText() + " and school name: "+ schoolnameTxt.getText() +" Not present!");
            }
        }
    }

    @FXML
    void logUserIn(ActionEvent event) {
        WindowChangeRoutine.showHomeScreen(recoveryAnchorRoot);
    }

    @FXML
    void backToLoginPage(ActionEvent event) {
        WindowChangeRoutine.showLoginPage(recoveryAnchorRoot);
    }
}
