package com.hillel.orders.repository;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class BaseRepository {

    public void closeConnection (Connection connection){
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("cannot close connection");
            }
        }

    }
}
