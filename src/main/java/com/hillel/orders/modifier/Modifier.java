package com.hillel.orders.modifier;

import com.hillel.orders.connection.ConnectionProvider;
import com.hillel.orders.entity.Order;
import com.hillel.orders.entity.RecordsOfOrder;

import java.sql.*;
import java.util.List;

public class Modifier {

    public void createNewOrder(List<RecordsOfOrder> recordsOfOrder) throws SQLException {
        int order_id;

        if (!recordsOfOrder.isEmpty()) {
            Connection connection = ConnectionProvider.provideConnection();

            if (connection != null) {
                try (Statement statement =
                             connection.createStatement()) {

                    String sqlQuery = "INSERT INTO `schema_orders`.`orders` (`date`) VALUES (CURRENT_DATE());";
                    int i = statement.executeUpdate(sqlQuery);

                    ResultSet resultSet = statement.executeQuery("SELECT * FROM schema_orders.orders ORDER BY id DESC LIMIT 1;");
                    resultSet.next();
                    order_id = resultSet.getInt("id");

                    System.out.println("new record was made");

                    for (RecordsOfOrder ofOrder : recordsOfOrder) {
                        sqlQuery = "INSERT INTO `schema_orders`.`order_product` (`order_id`, `product_id`, `quantity`) VALUES ('"
                                + order_id + "', '" + ofOrder.getProduct()
                                + "', '" + ofOrder.getQuantityOfProduct() + "');";
                        int i1 = statement.executeUpdate(sqlQuery);
                    }

                } catch (Exception e) {
                    e.printStackTrace();

                } finally {
//                    closeConnection(connection);
                    connection.close();
                }
            }
        }
    }

    public void deleteAllOrders(List<Order> orders) {
        Connection connection = ConnectionProvider.provideConnection();

        if (connection != null) {

            try (Statement statement =
                         connection.createStatement()) {

                for (Order order : orders) {
                    List<RecordsOfOrder> recordsOfOrder = order.getRecordsOfOrder();
                    int orderID = order.getID();
                    for (RecordsOfOrder recordOfOrder : recordsOfOrder) {
                        int productID = recordOfOrder.getProduct().getID();
                        String sqlString = "DELETE FROM `schema_orders`.`order_product` WHERE (`order_id` = '"
                                + orderID + "') and (`product_id` = '" + productID + "');";
                        int i1 = statement.executeUpdate(sqlString);
                    }
                    String sqlDeleteOrder = "DELETE FROM `schema_orders`.`orders` WHERE (`id` = '" + orderID + "');";
                    int i2 = statement.executeUpdate(sqlDeleteOrder);

                }
            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                    closeConnection(connection);
//                connection.close();
            }
        }



    }
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
