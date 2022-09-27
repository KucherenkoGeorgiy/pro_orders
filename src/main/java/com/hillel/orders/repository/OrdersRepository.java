package com.hillel.orders.repository;

import com.hillel.orders.entity.Order;

import java.util.List;

public class OrdersRepository extends BaseRepository<Order> {

    private static final String SQL_LIST_OF_CONTAINING_PRODUCT = """
            select order_id, date from schema_orders.order_product op
            join product p on op.product_ID=p.id
            join orders o on op.order_id=o.id
            where product_id=?;""";
    private static final String SQL_LIST_OF_DO_NOT_CONTAIN_PROD = """
            SELECT *
            FROM orders
            WHERE not EXISTS
                (SELECT *
                FROM order_product
                where orders.id = order_product.order_id and order_product.product_id = ?)
            and date=CURDATE();""";
    private static final String SQL_FIND_BY_ORDER_ID = "SELECT * FROM schema_orders.orders where id=?;";
    private static final String SQL_LIST_OF_FILTER_TOTAL_AND_QUANTITY = """
            select order_id, date from schema_orders.order_product op
            join product p on op.product_ID=p.id
            join orders o on op.order_id=o.id
            Group by order_id
            having SUM(quantity*price)<? and count(product_id)=?
            order by order_id;""";
    private static final String SQL_LIST_OF_FILTER_PRODUCT_AND_QUANTITY = """
            SELECT name, describing, price, date, quantity, order_id, product_id
            FROM schema_orders.order_product op
            JOIN product p ON op.product_ID=p.id
            JOIN orders o ON op.order_id=o.id where product_id=? and quantity=?
            order by order_id;""";


    public Order getNonDetailedOrderByOrderID(int orderID) {
        return getListByID(orderID, SQL_FIND_BY_ORDER_ID,
                resultSet -> new Order(resultSet.getInt("id"), resultSet.getDate("date"))).get(0);
    }

    public List<Order> getNonDetOrdersByMaxTotalAndQuantityOfDifferentGoods(int maxTotal, int quantityOfDifferentGoods) {
        return getListByIDWithTwoParams(maxTotal, quantityOfDifferentGoods, SQL_LIST_OF_FILTER_TOTAL_AND_QUANTITY,
                resultSet -> new Order(resultSet.getInt("order_id"), resultSet.getDate("date")));
    }

    public List<Order> getNonDetOrdersByProductIDAndQuantity(int productID, int quantityOfGoods) {
        return getListByIDWithTwoParams(productID, quantityOfGoods, SQL_LIST_OF_FILTER_PRODUCT_AND_QUANTITY,
                resultSet -> new Order(resultSet.getInt("order_id"), resultSet.getDate("date")));
    }

    public List<Order> getNonDetOrdersThatContainTheProduct(int productID) {
        return getListByID(productID, SQL_LIST_OF_CONTAINING_PRODUCT,
                resultSet -> new Order(resultSet.getInt("order_id"), resultSet.getDate("date")));
    }

    public List<Order> getNonDetOrdersThatDoNotContainTheProductAndContainCurDate(int productID) {
        return getListByID(productID, SQL_LIST_OF_DO_NOT_CONTAIN_PROD,
                resultSet -> new Order(resultSet.getInt("id"), resultSet.getDate("date")));
    }
}

