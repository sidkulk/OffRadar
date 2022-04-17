package io.sidkulk.offradar;

import io.sidkulk.screens.WindowChangeRoutine;
import io.sidkulk.services.core.CoreApplicationServices;
import io.sidkulk.userCache.LoggedInUserDataStore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeView implements Initializable {

    private CoreApplicationServices coreApplicationServices;

    @FXML
    private Button addPwdBtn;

    @FXML
    private AnchorPane homeAnchorPane;

    @FXML
    private Button deletePwdBtn;

    @FXML
    private TableColumn pwdTitleCol;

    @FXML
    private TableColumn pwdValueCol;

    @FXML
    private ToggleButton showTabToggleBtn;

    @FXML
    private Button updatePwdBtn;

    @FXML
    private Label usernameLabel;

    @FXML
    private TableView<?> passTableView;

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
        passTableView = new TableView<>();
        pwdTitleCol = new TableColumn("Password Title");
        pwdValueCol = new TableColumn("Password Value");

        passTableView.getColumns().addAll(pwdTitleCol, pwdValueCol);


    }
}
