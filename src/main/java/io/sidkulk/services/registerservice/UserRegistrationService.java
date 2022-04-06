package io.sidkulk.services.registerservice;

import io.sidkulk.model.User;
import io.sidkulk.services.database.DatabaseSchemaServer;
import io.sidkulk.services.database.DatabaseService;
import io.sidkulk.services.hashservice.HashingServiceClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UserRegistrationService {
    private HashingServiceClass hashService;

    public boolean registerNewUser(User user) {
        if (this.hashService == null) {
            this.hashService = new HashingServiceClass();
        }
        String query = "INSERT INTO " + DatabaseSchemaServer.USER_TAB_NAME
                + "(username, email, passwordhash, nickname, schoolname, privatekey) VALUES(?, ?, ?, ?, ?, ?)";

        try {
            Connection connection = DriverManager.getConnection(DatabaseService.getConnectionURL());
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, this.hashService.generateStringPasswordHash(user.getPassword()));
            preparedStatement.setString(4, user.getNickname());
            preparedStatement.setString(5, user.getChildhoodSchoolName());
            preparedStatement.setString(6, user.getPrivateKey());
            int rowUpdateCount = preparedStatement.executeUpdate();
            if (!connection.isClosed()) {
                connection.close();
            }
            return rowUpdateCount > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
