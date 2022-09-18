package com.hillel.orders.repository;

import com.hillel.orders.connection.ConnectionProvider;
import com.hillel.orders.entity.OrderProduct;
import com.hillel.orders.entity.Orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderProductRepository extends BaseRepository {

    public List<OrderProduct> getAllDetailedOrdersFromDB() {


        Connection connection = ConnectionProvider.provideConnection();


        if (connection != null) {

            try (PreparedStatement statement = connection.prepareStatement("select * from order_product")) {

                ResultSet resultSet = statement.executeQuery();
                List<OrderProduct> result = new ArrayList<>();
                while (resultSet.next()) {
                    result.add(new OrderProduct(resultSet.getInt("order_id"),
                            resultSet.getInt("product_id"),
                            resultSet.getInt("quantity")));

                }
                return result;

            } catch (SQLException e) {
                e.printStackTrace();
                return List.of();

            } finally {
                closeConnection(connection);
            }
        }


        return List.of();

    }
}
