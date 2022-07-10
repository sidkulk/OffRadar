package io.sidkulk.services.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseService {
    public static Connection connection;
    private static final String URL = "jdbc:sqlite:src/main/resources/PassKeep.db";
    private static final String CLASS_FOR_NAME = "org.sqlite.JDBC";

    public static String getConnectionURL() {
        return URL;
    }
    
    public static Connection getConnection() {
    	return connection;
    }

    public void connectToDatabase() {
        try {
            Class.forName(CLASS_FOR_NAME);
            connection = DriverManager.getConnection(URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static String getClassForName() {
    	return CLASS_FOR_NAME;
    }

    public void createAllTables() {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(DatabaseSchemaServer.CREATE_USER_TAB_QUERY);
            stmt.execute(DatabaseSchemaServer.CREATE_PASSWORD_TAB_QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
