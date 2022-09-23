package com.hillel.orders.modifier;

import com.hillel.orders.connection.ConnectionProvider;

import java.sql.*;
import java.util.Map;
import java.util.Set;

public class Modifier {

    public void createNewOrder(Map<Integer, Integer> recordsOfOrder) throws SQLException {
        int prod_id;
        int prod_quantity;
        int order_id;
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

                Set<Map.Entry<Integer, Integer>> set = recordsOfOrder.entrySet();
                for (Map.Entry<Integer, Integer> productID : set) {
                    prod_id = productID.getKey();
                    prod_quantity = productID.getValue();
                    sqlQuery= "INSERT INTO `schema_orders`.`order_product` (`order_id`, `product_id`, `quantity`) VALUES ('" + order_id + "', '" + prod_id + "', '" + prod_quantity + "');\n";
                    int i1 = statement.executeUpdate(sqlQuery);
                }
            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                connection.close();
            }
        }
    }

//    public void deleteAllThatMatchQuantityAndProductID(int productID, int quantity) {
//        int prod_id;
//        int prod_quantity;
//        int order_id;
//        Connection connection = ConnectionProvider.provideConnection();
//
//        if (connection != null) {
//            try (Statement statement =
//                         connection.createStatement()) {
//
////                connection.prepareStatement("SELECT quantity, order_id, product_id FROM schema_orders.order_product op\n" +
////                        "where product_id=" + productID + " and quantity=" + quantity + ";");
//
//
//                String sqlQuery = "INSERT INTO `schema_orders`.`orders` (`date`) VALUES (CURRENT_DATE());";
//                int i = statement.executeUpdate(sqlQuery);
//
//                ResultSet resultSet = statement.executeQuery("SELECT * FROM schema_orders.orders ORDER BY id DESC LIMIT 1;");
//                resultSet.next();
//                order_id = resultSet.getInt("id");
//
//                System.out.println("new record was made");
//
//                Set<Map.Entry<Integer, Integer>> set = recordsOfOrder.entrySet();
//                for (Map.Entry<Integer, Integer> productID : set) {
//                    prod_id = productID.getKey();
//                    prod_quantity = productID.getValue();
//                    sqlQuery= "INSERT INTO `schema_orders`.`order_product` (`order_id`, `product_id`, `quantity`) VALUES ('" + order_id + "', '" + prod_id + "', '" + prod_quantity + "');\n";
//                    int i1 = statement.executeUpdate(sqlQuery);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//
//            } finally {
//                connection.close();
//            }
//        }
//
//
//
//    }
}
