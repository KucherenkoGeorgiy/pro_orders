package com.hillel.orders.repository;

import com.hillel.orders.connection.ConnectionProvider;
import com.hillel.orders.entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersRepository extends BaseRepository {


//    public List<Order> getAllOrdersFromDB() {
//        Connection connection = ConnectionProvider.provideConnection();
//
//        if (connection != null) {
//            try (PreparedStatement statement = connection.prepareStatement("select * from orders order by date desc")) {
//                ResultSet resultSet = statement.executeQuery();
//                List<Order> result = new ArrayList<>();
//                while (resultSet.next()) {
//                    result.add(new Order(resultSet.getInt("id"),
//                            resultSet.getDate("date")));
//                }
//                return result;
//            } catch (SQLException e) {
//                e.printStackTrace();
//                return List.of();
//
//            } finally {
//                closeConnection(connection);
//            }
//        }
//        return List.of();
//    }

//    public Order getAllDetailsOfOrderByOrderID(int orderID) {
//        boolean someFlag = false;
//        int price;
//        int quantity;
//        int totalAmount = 0;
//        Date orderDate = null;
//
//
////        Function<Integer, Integer>
//        Connection connection = ConnectionProvider.provideConnection();
//        if (connection != null) {
//            try (PreparedStatement statement = connection.prepareStatement("SELECT name, describing, price, date, quantity, order_id, product_id FROM schema_orders.order_product op JOIN product p ON op.product_ID=p.id and order_id=? JOIN orders o ON op.order_id=o.id order by order_id;")) {
//                statement.setInt(1, orderID);
//
//                ResultSet resultSet = statement.executeQuery();
//                Map<Product, Integer> result = new HashMap<>();
//                while (resultSet.next()) {
//                    if (!someFlag) {
//                        someFlag = true;
//                        orderDate = resultSet.getDate("date");
//                    }
//                    price = resultSet.getInt("price");
//                    quantity = resultSet.getInt("quantity");
//                    totalAmount += price * quantity;
//
//                    result.put(new Product(resultSet.getInt("product_id"), resultSet.getString("name"),
//                            resultSet.getString("describing"),
//                            price), quantity);
//                }
//
//
//                if (orderDate != null) {
//                    return new Order(orderID, orderDate, result);
//                } else {
//                    return null;
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//                return null;
//
//            } finally {
//                closeConnection(connection);
//            }
//        }
//        return null;
//    }

    public Order getNonDetailedOrderByOrderID(int orderID) {

        Connection connection = ConnectionProvider.provideConnection();

        if (connection != null) {
            try (PreparedStatement statement =
                         connection.prepareStatement("SELECT * FROM schema_orders.orders where id=?;")) {

                statement.setInt(1, orderID);

                ResultSet resultSet = statement.executeQuery();
//                System.out.println(resultSet.getFetchSize());
//                if (resultSet.getFetchSize() > 0) {
                    resultSet.next();
                    return new Order(orderID, resultSet.getDate("date"));
//                } else {
//                    return null;
//                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            } finally {
                closeConnection(connection);
            }
        }
        return null;
    }

    public List<Order> getNonDetOrdersByMaxTotalAndQuantityOfDifferentGoods(int maxTotal, int quantityOfDifferentGoods) {

        Connection connection = ConnectionProvider.provideConnection();

        if (connection != null) {
            try (PreparedStatement statement =
                         connection.prepareStatement("select order_id, date from schema_orders.order_product op\n" +
                                 "join product p on op.product_ID=p.id\n" +
                                 "join orders o on op.order_id=o.id\n" +
                                 "Group by order_id\n" +
                                 "having SUM(quantity*price)<? and count(product_id)=? \n" +
                                 "order by order_id;")) {

                statement.setInt(1, maxTotal);
                statement.setInt(2, quantityOfDifferentGoods);

                ResultSet resultSet = statement.executeQuery();
                List<Order> result = new ArrayList<>();
                while (resultSet.next()){
                    result.add(new Order(resultSet.getInt("order_id"), resultSet.getDate("date")));
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


    public List<Order> getNonDetOrdersThatContainTheProduct(int productID) {

        Connection connection = ConnectionProvider.provideConnection();

        if (connection != null) {
            try (PreparedStatement statement =
                         connection.prepareStatement("select order_id, date from schema_orders.order_product op\n" +
                                 "join product p on op.product_ID=p.id\n" +
                                 "join orders o on op.order_id=o.id\n" +
                                 "where product_id=?;")) {

                statement.setInt(1, productID);

                ResultSet resultSet = statement.executeQuery();
                List<Order> result = new ArrayList<>();
                while (resultSet.next()){
                    result.add(new Order(resultSet.getInt("order_id"), resultSet.getDate("date")));
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

    public List<Order> getNonDetOrdersThatDoNotContainTheProductAndContainCurDate(int productID) {

        Connection connection = ConnectionProvider.provideConnection();

        if (connection != null) {
            try (PreparedStatement statement =
                         connection.prepareStatement("SELECT *\n" +
                                 "  FROM orders\n" +
                                 " WHERE not EXISTS\n" +
                                 "  (SELECT * \n" +
                                 "     FROM order_product\n" +
                                 "    where orders.id = order_product.order_id and order_product.product_id = ?) and date=CURDATE();")) {

                statement.setInt(1, productID);

                ResultSet resultSet = statement.executeQuery();
                List<Order> result = new ArrayList<>();
                while (resultSet.next()){
                    result.add(new Order(resultSet.getInt("id"), resultSet.getDate("date")));
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

