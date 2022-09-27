package com.hillel.orders.repository;

import com.hillel.orders.entity.Product;
import com.hillel.orders.entity.RecordsOfOrder;

import java.util.List;

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


    public List<RecordsOfOrder> getRecordsOfOrderByOrderID(int orderID) {
        return getListByID(orderID, SQL_LIST_SELECT_RECORDS_BY_ORDER_ID,
                resultSet -> new RecordsOfOrder(new Product(resultSet.getInt("product_id"),
                        resultSet.getString("name"),
                        resultSet.getString("describing"),
                        resultSet.getInt("price")), resultSet.getInt("quantity")));
    }

    public List<RecordsOfOrder> getAllRecordsFromCurDate() {
        return getListWithoutID(SQL_LIST_SELECT_RECORDS_FROM_TODAY,
                resultSet -> new RecordsOfOrder(new Product(resultSet.getInt("product_id"),
                        resultSet.getString("name"),
                        resultSet.getString("describing"),
                        resultSet.getInt("price")), resultSet.getInt("quantity")));
    }
}
