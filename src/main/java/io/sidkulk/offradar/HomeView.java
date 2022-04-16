package io.sidkulk.offradar;

import io.sidkulk.screens.WindowChangeRoutine;
import io.sidkulk.userCache.LoggedInUserDataStore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeView implements Initializable {

    @FXML
    private Button addPwdBtn;

    @FXML
    private AnchorPane homeAnchorPane;

    @FXML
    private Button deletePwdBtn;

    @FXML
    private TableColumn<?, ?> pwdTitleCol;

    @FXML
    private TableColumn<?, ?> pwdValueCol;

    @FXML
    private ToggleButton showTabToggleBtn;

    @FXML
    private Button updatePwdBtn;

    @FXML
    private Label usernameLabel;

    @FXML
    void addPasswordEntry(ActionEvent event) {

    }

    @FXML
    void deleteSelectedRow(ActionEvent event) {

    }

    @FXML
    void logUserOut(ActionEvent event) {
        LoggedInUserDataStore.clearCurrentlyLoggedInUserData();
        WindowChangeRoutine.showLoginPage(this.homeAnchorPane);
    }

    @FXML
    void showHideToggleAction(ActionEvent event) {

    }

    @FXML
    void updateSelectedRow(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.usernameLabel.setText(LoggedInUserDataStore.getCurrentUsername());
    }
}
