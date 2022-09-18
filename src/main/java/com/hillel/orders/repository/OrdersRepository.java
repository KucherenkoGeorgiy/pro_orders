package com.hillel.orders.repository;

import com.hillel.orders.connection.ConnectionProvider;
import com.hillel.orders.entity.DetailedOrder;
import com.hillel.orders.entity.Orders;
import com.hillel.orders.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrdersRepository extends BaseRepository {


    public List<Orders> getAllOrdersFromDB() {
        Connection connection = ConnectionProvider.provideConnection();

        if (connection != null) {
            try (PreparedStatement statement = connection.prepareStatement("select * from orders order by date desc")) {
                ResultSet resultSet = statement.executeQuery();
                List<Orders> result = new ArrayList<>();
                while (resultSet.next()) {
                    result.add(new Orders(resultSet.getInt("id"),
                            resultSet.getDate("date")));
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

    public DetailedOrder getAllDetailsOfOrderByOrderID(int orderID) {
        boolean someFlag = false;
        int price;
        int quantity;
        int totalAmount = 0;
        Date orderDate = null;

        Connection connection = ConnectionProvider.provideConnection();
        if (connection != null) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT name, describing, price, date, quantity, order_id, product_id FROM schema_orders.order_product op JOIN product p ON op.product_ID=p.id and order_id=? JOIN orders o ON op.order_id=o.id order by order_id;")) {
                statement.setInt(1, orderID);

                ResultSet resultSet = statement.executeQuery();
                Map<Product, Integer> result = new HashMap<>();
                while (resultSet.next()) {
                    if (!someFlag) {
                        someFlag = true;
                        orderDate = resultSet.getDate("date");
                    }
                    price = resultSet.getInt("price");
                    quantity = resultSet.getInt("quantity");
                    totalAmount += price * quantity;

                    result.put(new Product(resultSet.getInt("product_id"), resultSet.getString("name"),
                            resultSet.getString("describing"),
                            price), quantity);
                }

                if (orderDate != null) {
                    return new DetailedOrder(orderID, orderDate, result, totalAmount);
                } else {
                    return null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;

            } finally {
                closeConnection(connection);
            }
        }
        return null;
    }
}
