package io.sidkulk.offradar;

import io.sidkulk.model.Password;
import io.sidkulk.screens.WindowChangeRoutine;
import io.sidkulk.services.alerts.AlertBoxClass;
import io.sidkulk.services.core.CoreApplicationServices;
import io.sidkulk.userCache.LoggedInUserDataStore;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
	private TableColumn<Password, String> pwdTitleCol;

	@FXML
	private TableColumn<Password, String> pwdValueCol;

	@FXML
	private ToggleButton showTabToggleBtn;

	@FXML
	private Button updatePwdBtn;

	@FXML
	private Label usernameLabel;

	@FXML
	private TextField pwdTitle;

	@FXML
	private TextField pwdValue;

	@FXML
	private TableView<Password> passTableView;

	@FXML
	void addPasswordEntry(ActionEvent event) {
		System.out.println("Add password action event called");
		if (coreApplicationServices == null) {
			coreApplicationServices = new CoreApplicationServices(LoggedInUserDataStore.getCurrentUserPrivateKey());
		}
		String passwordTitle = pwdTitle.getText();
		String passwordValue = pwdValue.getText();
		
		if(passwordTitle.isBlank() || passwordValue.isBlank()) {
			AlertBoxClass.showErrorAlert("ERROR", "Please fill in all the field(s)!");
		} else {
			coreApplicationServices.addNewPasswordEntry(new Password(passwordTitle, passwordValue), LoggedInUserDataStore.getCurrentUsername());
		}
	}

	@FXML
	void deleteSelectedRow(ActionEvent event) {
		System.out.println("Delete password action event called");

	}

	@FXML
	void logUserOut(ActionEvent event) {
		LoggedInUserDataStore.clearCurrentlyLoggedInUserData();
		WindowChangeRoutine.showLoginPage(this.homeAnchorPane);
	}

	@FXML
	void showHideToggleAction(ActionEvent event) {
		System.out.println("Show password action event called");

	}

	@FXML
	void updateSelectedRow(ActionEvent event) {
		System.out.println("Update password action event called");

	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		this.usernameLabel.setText(LoggedInUserDataStore.getCurrentUsername());
		ObservableList<Password> passList = coreApplicationServices
				.getPasswordListForLoggedInUser(LoggedInUserDataStore.getCurrentUsername());
		passTableView = new TableView<>();
		pwdTitleCol = new TableColumn<>("Password Title");
		pwdValueCol = new TableColumn<>("Password Value");

		try {
			coreApplicationServices = new CoreApplicationServices(LoggedInUserDataStore.getCurrentUserPrivateKey());
			passTableView.getColumns().addAll(pwdTitleCol, pwdValueCol);
			pwdTitleCol.setCellValueFactory(new PropertyValueFactory<>("PasswordTitle"));
			pwdValueCol.setCellValueFactory(new PropertyValueFactory<>("PasswordValue"));
			passTableView.setItems(passList);

			System.out.println(passList.getClass());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
