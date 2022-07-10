package io.sidkulk.services.core;

import io.sidkulk.encryption.UserInformationEncryptionService;
import io.sidkulk.model.Password;
import io.sidkulk.services.database.DatabaseSchemaServer;
import io.sidkulk.services.database.DatabaseService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class CoreApplicationServices {
	private static PreparedStatement pstmt;
	private static int rowUpdate = 0;
	private final UserInformationEncryptionService crypto;

	public CoreApplicationServices(String privateKey) {

		crypto = new UserInformationEncryptionService(privateKey);
	}

	public boolean addNewPasswordEntry(Password password, String username) {
		String query = "INSERT INTO " + DatabaseSchemaServer.PASSWORD_TAB_NAME
				+ "(passwordtitle, passwordvalue, username) VALUES(?, ?, ?)";
		try {
			if (DatabaseService.connection.isClosed()) {
				DatabaseService.connection = DriverManager.getConnection(DatabaseService.getConnectionURL());
			}

			pstmt = DatabaseService.connection.prepareStatement(query);
			pstmt.setString(1, password.getPasswordTitle());
			pstmt.setString(2, crypto.encrypt(password.getPasswordValue()));
			pstmt.setString(3, username);

			rowUpdate = pstmt.executeUpdate();
			if (rowUpdate > 0) {
				return true;
			}
			if (!DatabaseService.connection.isClosed()) {
				DatabaseService.connection.close();
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean removePasswordEntry(int p_id) {
		String query = "DELETE FROM " + DatabaseSchemaServer.PASSWORD_TAB_NAME + " WHERE p_id = ?";
		try {
			if (DatabaseService.connection.isClosed()) {
				DatabaseService.connection = DriverManager.getConnection(DatabaseService.getConnectionURL());
			}
			pstmt = DatabaseService.connection.prepareStatement(query);
			pstmt.setInt(1, p_id);
			rowUpdate = pstmt.executeUpdate();
			if (rowUpdate > 0) {
				return true;
			}
			if (!DatabaseService.connection.isClosed()) {
				DatabaseService.connection.close();
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateSelectedEntry(int p_id, String passwordTitleUpdated, String passwordValueUpdated) {
		String query = "UPDATE " + DatabaseSchemaServer.PASSWORD_TAB_NAME
				+ " SET passwordtitle = ?, passwordvalue = ? WHERE p_id = ?";
		try {
			if (DatabaseService.connection.isClosed()) {
				DatabaseService.connection = DriverManager.getConnection(DatabaseService.getConnectionURL());
			}
			pstmt = DatabaseService.connection.prepareStatement(query);
			pstmt.setString(1, passwordTitleUpdated);
			pstmt.setString(2, crypto.encrypt(passwordValueUpdated));
			pstmt.setInt(3, p_id);

			rowUpdate = pstmt.executeUpdate();

			if (rowUpdate > 0) {
				return true;
			}
			if (!DatabaseService.connection.isClosed()) {
				DatabaseService.connection.close();
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public ObservableList<Password> getPasswordListForLoggedInUser(String username) {
		ObservableList<Password> passwordList = FXCollections.observableArrayList();

		String query = "SELECT passwordtitle, passwordvalue FROM " + DatabaseSchemaServer.PASSWORD_TAB_NAME
				+ " WHERE username = ?";

		try {
			Connection connection = DriverManager.getConnection(DatabaseService.getConnectionURL());
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				passwordList
						.add(new Password(resultSet.getString("passwordtitle"), resultSet.getString("passwordvalue")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return passwordList;
	}
}
