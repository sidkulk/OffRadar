package io.sidkulk.offradar;

import io.sidkulk.encryption.UserInformationEncryptionService;
import io.sidkulk.model.User;
import io.sidkulk.services.alerts.AlertBoxClass;
import io.sidkulk.services.registerservice.UserRegistrationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class RegisterView {
    @FXML
    private Button backBtn;

    @FXML
    private TextField childFrndTxt;

    @FXML
    private TextField childSchoolTxt;

    @FXML
    private PasswordField confPwdTxt;

    @FXML
    private TextField emailTxt;

    @FXML
    private PasswordField pwdTxt;

    @FXML
    private Button regUserBtn;

    @FXML
    private AnchorPane registerAnchorPane;

    @FXML
    private TextField usernameTxt;

    @FXML
    void goBackToLogin(ActionEvent event) throws Exception {
        Stage stage;
        Parent root;

        if (event.getSource() == backBtn) {
            stage = (Stage) backBtn.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login-view.fxml")));
        } else {
            stage = (Stage) backBtn.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("register-view.fxml")));
            stage.setResizable(false);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void registerUser(ActionEvent event) throws IOException {
        System.out.println("Yet to be implemented");
        String username = usernameTxt.getText();
        String email = emailTxt.getText();
        String password = pwdTxt.getText();
        String confPassword = confPwdTxt.getText();
        String childFriend = childFrndTxt.getText();
        String childSchool = childSchoolTxt.getText();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confPassword.isEmpty() || childFriend.isEmpty() || childSchool.isEmpty()) {
            AlertBoxClass.showErrorAlert("Registration Error", "Please fill in all the fields!");
        } else {
            if (!password.equals(confPassword)) {
                AlertBoxClass.showErrorAlert("Registration Error", "Passwords did not match!");
            } else {
                UserRegistrationService userRegistrationService = new UserRegistrationService();
                boolean isRegistered = userRegistrationService.registerNewUser(new User(username, email, password, childFriend, childSchool, UserInformationEncryptionService.generateRandomString(16)));
                if(!isRegistered) {
                    AlertBoxClass.showErrorAlert("Registration Error", "Something went wrong while registring you! Contact your software vendor.");
                } else {
                    AlertBoxClass.showSuccessAlert("Success", "User: " + username + " Registered successfully! You'll be redirected to login page.");
                    Stage stage;
                    Parent root;
                    stage = (Stage) backBtn.getScene().getWindow();
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login-view.fxml")));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
                }
            }
        }
    }
}
