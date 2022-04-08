package io.sidkulk.services.recovery;

import io.sidkulk.model.CurrentUser;
import io.sidkulk.services.database.DatabaseSchemaServer;
import io.sidkulk.services.database.DatabaseService;
import io.sidkulk.userCache.LoggedInUserDataStore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRecoveryService {
    private static String recoverdUsername = null;
    private static String recoveredPasswordHash = null;

    public static boolean doesAccountExists(String nickname, String childHoodSchoolName) {
        try {
            Connection connection = DriverManager.getConnection(DatabaseService.getConnectionURL());
            String query = "SELECT username, passwordhash, email FROM " + DatabaseSchemaServer.USER_TAB_NAME + " WHERE nickname = ? AND schoolname = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nickname);
            preparedStatement.setString(2, childHoodSchoolName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next() == false) {
                throw new IllegalStateException("User doesn't exists in database");
            } else {
                do {
                    recoverdUsername = resultSet.getString("username");
                    recoveredPasswordHash = resultSet.getString("passwordhash");
                    String recoveredEmail = resultSet.getString("email");
                    LoggedInUserDataStore.setCurrentlyLoggedInUserData(new CurrentUser(recoverdUsername, recoveredEmail, nickname, childHoodSchoolName));
                } while (resultSet.next());
                return true;
            }

        } catch (Exception e) {
            return false;
        }
    }

    public static String getRecoverdUsername() {
        if (recoverdUsername == null || recoverdUsername.isEmpty()) {
            throw new IllegalStateException("Recovered Username not found! Contact your software vendor!");
        } else {
            return recoverdUsername;
        }
    }

    public static String getRecoveredPasswordHash() {
        if (recoveredPasswordHash == null || recoveredPasswordHash.isEmpty()) {
            throw new IllegalStateException("Recovered Username not found! Contact your software vendor!");
        } else {
            return recoveredPasswordHash;
        }
    }
}
