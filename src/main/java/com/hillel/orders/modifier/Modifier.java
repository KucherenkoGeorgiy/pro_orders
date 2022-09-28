package com.hillel.orders.modifier;

import com.hillel.orders.connection.ConnectionProvider;
import com.hillel.orders.entity.Order;
import com.hillel.orders.entity.RecordsOfOrder;

import java.sql.*;
import java.util.List;

public class Modifier {

    public static String SQL_PATTERN_DELETE_RECORD_OF_ORDER = """
            DELETE FROM `schema_orders`.`order_product`
            WHERE (`order_id` = '%d') and (`product_id` = '%d');""";
    public static String SQL_PATTERN_DELETE_ORDER = "DELETE FROM `schema_orders`.`orders` WHERE (`id` = '%d');";
    public static String SQL_CREATE_NEW_ORDER_WITH_CUR_DATE = """
            INSERT INTO `schema_orders`.`orders` (`date`) VALUES (CURRENT_DATE());""";
    public static String SQL_PATTERN_CREATE_NEW_RECORDS_OF_ORDER = """
            INSERT INTO `schema_orders`.`order_product`
            (`order_id`, `product_id`, `quantity`)
            VALUES ('%d', '%d', '%d');""";
    public static String SQL_GET_NUMBER_OF_LAST_ORDER = "SELECT * FROM schema_orders.orders ORDER BY id DESC LIMIT 1;";


    public void createNewOrder(List<RecordsOfOrder> recordsOfOrder) {
        if (!recordsOfOrder.isEmpty()) {
            Connection connection = ConnectionProvider.provideConnection();
            if (connection != null) {
                try (Statement statement = connection.createStatement()) {
                    int order_id;
                    statement.executeUpdate(SQL_CREATE_NEW_ORDER_WITH_CUR_DATE);
                    ResultSet resultSet = statement.executeQuery(SQL_GET_NUMBER_OF_LAST_ORDER);
                    resultSet.next();
                    order_id = resultSet.getInt("id");
                    for (RecordsOfOrder ofOrder : recordsOfOrder) {
                        statement.executeUpdate(String
                                .format(SQL_PATTERN_CREATE_NEW_RECORDS_OF_ORDER,
                                        order_id, ofOrder.getProduct().getID(), ofOrder.getQuantityOfProduct()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    closeConnection(connection);
                }
            }
        }
    }

    public void deleteAllOrders(List<Order> orders) {
        Connection connection = ConnectionProvider.provideConnection();

        if (connection != null) {
            try (Statement statement = connection.createStatement()) {
                for (Order order : orders) {
                    List<RecordsOfOrder> recordsOfOrder = order.getRecordsOfOrder();
                    int orderID = order.getID();

                    for (RecordsOfOrder recordOfOrder : recordsOfOrder) {
                        int productID = recordOfOrder.getProduct().getID();
                        statement.executeUpdate(String
                                .format(SQL_PATTERN_DELETE_RECORD_OF_ORDER, orderID, productID));
                    }
                    statement.executeUpdate(String.format(SQL_PATTERN_DELETE_ORDER, orderID));
                }
            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                closeConnection(connection);
            }
        }
    }

    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("cannot close connection");
            }
        }
    }
}
