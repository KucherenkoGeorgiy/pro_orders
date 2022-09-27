package com.hillel.orders.repository;

import com.hillel.orders.connection.ConnectionProvider;
import com.hillel.orders.entity.Order;
import com.hillel.orders.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository extends BaseRepository<Product> {


//    public List<Product> getAllProductsFromDB(){
//
//        Connection connection = ConnectionProvider.provideConnection();
//
//
//        if (connection != null) {
//
//            try (PreparedStatement statement = connection.prepareStatement("select * from product")) {
//
//                ResultSet resultSet = statement.executeQuery();
//                List<Product> result = new ArrayList<>();
//                while (resultSet.next()) {
//                    result.add(new Product(resultSet.getInt("id"),
//                            resultSet.getString("name"), resultSet.getString("describing"),
//                            resultSet.getInt("price")));
//
//                }
//                return result;
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//                return List.of();
//
//            } finally {
//                closeConnection(connection);
//            }
//        }
//
//
//        return List.of();
//
//
//
//    }
}
