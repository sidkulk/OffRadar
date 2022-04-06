package io.sidkulk.services.core;

import io.sidkulk.encryption.UserInformationEncryptionService;
import io.sidkulk.model.Password;
import io.sidkulk.services.database.DatabaseSchemaServer;
import io.sidkulk.services.database.DatabaseService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoreApplicationServices {
    private static PreparedStatement pstmt;
    private static int rowUpdate = 0;
    private final UserInformationEncryptionService crypto;
    private final List<Password> userPasswordList = new ArrayList<>();
    private Connection connection;

    public CoreApplicationServices(String privateKey) {
        super();
        crypto = new UserInformationEncryptionService(privateKey);
        try {
            connection = DriverManager.getConnection(DatabaseService.getConnectionURL());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean addNewPasswordEntry(Password password, String username) {
        String query = "INSERT INTO " + DatabaseSchemaServer.PASSWORD_TAB_NAME
                + "(passwordtitle, passwordvalue, username) VALUES(?, ?, ?)";
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(DatabaseService.getConnectionURL());
            }

            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, password.getPasswordTitle());
            pstmt.setString(2, crypto.encrypt(password.getPasswordValue()));
            pstmt.setString(3, username);

            rowUpdate = pstmt.executeUpdate();
            if (rowUpdate > 0) {
                return true;
            }
            if (!connection.isClosed()) {
                connection.close();
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
            if (connection == null) {
                connection = DriverManager.getConnection(DatabaseService.getConnectionURL());
            }
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, p_id);
            rowUpdate = pstmt.executeUpdate();
            if (rowUpdate > 0) {
                return true;
            }
            if (!connection.isClosed()) {
                connection.close();
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
            if (connection == null) {
                connection = DriverManager.getConnection(DatabaseService.getConnectionURL());
            }
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, passwordTitleUpdated);
            pstmt.setString(2, crypto.encrypt(passwordValueUpdated));
            pstmt.setInt(3, p_id);

            rowUpdate = pstmt.executeUpdate();

            if (rowUpdate > 0) {
                return true;
            }
            if (!connection.isClosed()) {
                connection.close();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Password> showPasswordForUsername(String username) {
        String query = "SELECT * FROM " + DatabaseSchemaServer.PASSWORD_TAB_NAME + " WHERE username = ?";
        userPasswordList.clear();
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(DatabaseService.getConnectionURL());
            }
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, username);
            return getPasswords();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Password> getPasswords() throws SQLException {
        ResultSet resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            userPasswordList.add(new Password(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
        }

        if (userPasswordList.isEmpty()) {
            return null;
        }
        if (!connection.isClosed()) {
            connection.close();
        }
        return userPasswordList;
    }

    public List<Password> showAllPasswordEntriesInDatabase() {
        userPasswordList.clear();
        String query = "SELECT * FROM " + DatabaseSchemaServer.PASSWORD_TAB_NAME;

        try {
            if (connection == null) {
                connection = DriverManager.getConnection(DatabaseService.getConnectionURL());
            }
            pstmt = connection.prepareStatement(query);
            return getPasswords();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}