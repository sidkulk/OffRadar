package io.sidkulk.services.authservice;

import io.sidkulk.model.CurrentUser;
import io.sidkulk.services.database.DatabaseSchemaServer;
import io.sidkulk.services.database.DatabaseService;
import io.sidkulk.services.hashservice.HashingServiceClass;
import io.sidkulk.userCache.LoggedInUserDataStore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserAuthService {
    private HashingServiceClass hashService;
    private static String usernameCurrentlyLoggedIn = "";
    private static boolean isCorrect = false;

    public boolean verifyUserLogin(String username, String passwordEntered) {
        String query = "SELECT passwordhash FROM " + DatabaseSchemaServer.USER_TAB_NAME + " WHERE username = ?";
        if (hashService == null) {
            hashService = new HashingServiceClass();
        }

        try {
            Connection connection = DriverManager.getConnection(DatabaseService.getConnectionURL());
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            String returnedHash = resultSet.getString(1);

            if (returnedHash == null) {
                throw new IllegalStateException("User with username: " + username + " does not exists!");
            } else {
                if (!connection.isClosed()) {
                    connection.close();
                }
                LoggedInUserDataStore.setCurrentlyLoggedInUserData(getCurrentLoggedInUserInfo(username));
                LoggedInUserDataStore.setCurrentlyLoggedInUserPrivateKey(getLoggedUserPrivateKey(connection, username));
                return hashService.verifyPassword(passwordEntered, returnedHash);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String getLoggedUserPrivateKey(Connection connection, String username) {
        String query = "SELECT privatekey FROM " + DatabaseSchemaServer.USER_TAB_NAME + " WHERE username = ?";
        try {
            if (connection.isClosed()) {
                connection = DriverManager.getConnection(DatabaseService.getConnectionURL());
            }
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            do {
                return resultSet.getString("privatekey");
            } while (resultSet.next());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static CurrentUser getCurrentLoggedInUserInfo(String usernameCurrentlyLoggedIn) {
        CurrentUser currentUser = null;
        try {
            String query = "SELECT * FROM " + DatabaseSchemaServer.USER_TAB_NAME + " WHERE username = ?";
            Connection connection = DriverManager.getConnection(DatabaseService.getConnectionURL());
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, usernameCurrentlyLoggedIn);
            ResultSet resultSet = preparedStatement.executeQuery();

            currentUser = new CurrentUser(resultSet.getString("username"), resultSet.getString("email"), resultSet.getString("nickname"), resultSet.getString("schoolname"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return currentUser;
    }
}
