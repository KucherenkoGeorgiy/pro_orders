package com.hillel.orders.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionProvider {
    public static Connection provideConnection() {

        Properties properties = new Properties();

        try (FileInputStream inStream = new FileInputStream("src\\main\\resources\\db.properties")) {
            properties.load(inStream);

        } catch (IOException e) {
            System.err.println("cannot read properties");
            return null;
        }

        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/schema_orders", properties);
        } catch (SQLException e) {
            System.err.println("cannot get connection");
            return null;
        }

    }
}
