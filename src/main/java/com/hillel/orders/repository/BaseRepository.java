package com.hillel.orders.repository;

import com.hillel.orders.connection.ConnectionProvider;
import com.hillel.orders.entity.Order;
import com.hillel.orders.util.EntityExtractor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseRepository<T>  {

    public void closeConnection (Connection connection){
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("cannot close connection");
            }
        }

    }

    public List<T> getListWithoutID(String selectSql, EntityExtractor<T> entityExtractor) {
        return getListByID(-1, selectSql, entityExtractor);
    }


        public List<T> getListByID(int id, String selectSql, EntityExtractor<T> entityExtractor) {
        return getListByIDWithTwoParams(id, -1, selectSql, entityExtractor);

        /*Connection connection = ConnectionProvider.provideConnection();
        if (connection != null) {
            try (PreparedStatement statement = connection.prepareStatement(selectSql)) {
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();
                List<T> result = new ArrayList<>();
                while (resultSet.next()){
                    result.add(entityExtractor.extract(resultSet));
                }
                return result;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            } finally {
                closeConnection(connection);
            }
        }
        return List.of();*/
    }

    public List<T> getListByIDWithTwoParams(int idFirst, int idSecond, String selectSql, EntityExtractor<T> entityExtractor) {
        Connection connection = ConnectionProvider.provideConnection();
        if (connection != null) {
            try (PreparedStatement statement = connection.prepareStatement(selectSql)) {
                if (idFirst != -1) {
                    statement.setInt(1, idFirst);
                    if (idSecond != -1) {
                        statement.setInt(2, idSecond);
                    }
                }
                ResultSet resultSet = statement.executeQuery();
                List<T> result = new ArrayList<>();
                while (resultSet.next()){
                    result.add(entityExtractor.extract(resultSet));
                }
                return result;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            } finally {
                closeConnection(connection);
            }
        }
        return List.of();
    }

}

