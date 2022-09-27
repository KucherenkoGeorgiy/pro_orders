package com.hillel.orders.repository;

import com.hillel.orders.connection.ConnectionProvider;
import com.hillel.orders.entity.Order;
import com.hillel.orders.entity.Product;
import com.hillel.orders.entity.RecordsOfOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecordsOfOrderRepository extends BaseRepository<RecordsOfOrder> {
    private static final String SQL_LIST_SELECT_RECORDS_BY_ORDER_ID = """
                                    SELECT product_id, name, describing, price, quantity
                                    FROM schema_orders.order_product op
                                    JOIN product p ON op.product_ID=p.id and order_id=?
                                    JOIN orders o ON op.order_id=o.id order by order_id;""";
    private static final String SQL_LIST_SELECT_RECORDS_FROM_TODAY = """
            SELECT sum(quantity), product_id FROM schema_orders.order_product op
            JOIN product p ON op.product_ID=p.id
            JOIN orders o ON op.order_id=o.id
            where date=curdate()
            group by product_id;""";


//    public List<RecordsOfOrder> getAllDetailedOrdersFromDB() {
//
//
//        Connection connection = ConnectionProvider.provideConnection();
//
//
//        if (connection != null) {
//
//            try (PreparedStatement statement = connection.prepareStatement("select * from order_product")) {
//
//                ResultSet resultSet = statement.executeQuery();
//                List<RecordsOfOrder> result = new ArrayList<>();
//                while (resultSet.next()) {
//                    result.add(new RecordsOfOrder(resultSet.getInt("order_id"),
//                            resultSet.getInt("product_id"),
//                            resultSet.getInt("quantity")));
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
//    }

    public List<RecordsOfOrder> getRecordsOfOrderByOrderID (int orderID) {
        return getListByID(orderID, SQL_LIST_SELECT_RECORDS_BY_ORDER_ID,
                resultSet -> new RecordsOfOrder(new Product(resultSet.getInt("product_id"),
                        resultSet.getString("name"),
                        resultSet.getString("describing"),
                        resultSet.getInt("price")), resultSet.getInt("quantity")));

//        return List.of();
    }
    /*
    public List<RecordsOfOrder> getRecordsOfOrderByOrderID2 (int orderID) {


        Connection connection = ConnectionProvider.provideConnection();
        if (connection != null) {
            try (PreparedStatement statement =
                         connection.prepareStatement("SELECT product_id, name, describing, price, quantity FROM schema_orders.order_product op JOIN product p ON op.product_ID=p.id and order_id=? JOIN orders o ON op.order_id=o.id order by order_id;")) {
                statement.setInt(1, orderID);

                ResultSet resultSet = statement.executeQuery();
                List<RecordsOfOrder> result = new ArrayList<>();
                while (resultSet.next()) {
                    result.add(new RecordsOfOrder(new Product(resultSet.getInt("product_id"),
                            resultSet.getString("name"),
                            resultSet.getString("describing"),
                            resultSet.getInt("price")), resultSet.getInt("quantity")));

                }


                return result;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            } finally {
                closeConnection(connection);
            }
        }
        return null;


    }
*/

    public List<RecordsOfOrder> getAllRecordsFromCurDate() {
        return getListWithoutID(SQL_LIST_SELECT_RECORDS_FROM_TODAY,
                resultSet -> new RecordsOfOrder(new Product(resultSet.getInt("product_id"),
                        resultSet.getString("name"),
                        resultSet.getString("describing"),
                        resultSet.getInt("price")), resultSet.getInt("quantity")));


    }


    public Map<Integer, Integer> getAllRecordsFromCurDate2() {


        Connection connection = ConnectionProvider.provideConnection();
        if (connection != null) {
            try (PreparedStatement statement =
                         connection.prepareStatement("SELECT sum(quantity), product_id FROM schema_orders.order_product op\n" +
                                 "JOIN product p ON op.product_ID=p.id \n" +
                                 "JOIN orders o ON op.order_id=o.id \n" +
                                 "where date=curdate()\n" +
                                 "group by product_id;")) {


                ResultSet resultSet = statement.executeQuery();
                Map<Integer, Integer> result = new HashMap<>();
                while (resultSet.next()) {
                    result.put(resultSet.getInt("product_id"), resultSet.getInt("sum(quantity)"));
                }


                return result;
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
