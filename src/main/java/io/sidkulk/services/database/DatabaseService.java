package io.sidkulk.services.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseService {
    private static Connection connection;
    private static final String URL = "jdbc:sqlite:PassKeep.db";

    public static String getConnectionURL() {
        return URL;
    }

    public void connectToDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(URL);
//            System.out.println("db connected!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createAllTables() {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(DatabaseSchemaServer.CREATE_USER_TAB_QUERY);
            stmt.execute(DatabaseSchemaServer.CREATE_PASSWORD_TAB_QUERY);
//            System.out.println("tables created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
