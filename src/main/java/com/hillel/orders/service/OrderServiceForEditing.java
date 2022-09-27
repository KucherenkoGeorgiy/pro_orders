package com.hillel.orders.service;

import com.hillel.orders.entity.Order;
import com.hillel.orders.entity.RecordsOfOrder;
import com.hillel.orders.modifier.Modifier;
import com.hillel.orders.repository.OrdersRepository;
import com.hillel.orders.repository.ProductRepository;
import com.hillel.orders.repository.RecordsOfOrderRepository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderServiceForEditing {

    OrdersRepository ordersRepository;
    RecordsOfOrderRepository recordsOfOrderRepository;
    Modifier modifier;


    public OrderServiceForEditing() {
        ordersRepository = new OrdersRepository();
        recordsOfOrderRepository = new RecordsOfOrderRepository();
        modifier = new Modifier();
    }

    public List<RecordsOfOrder> getAllRecordsFromCurDate() {
        return recordsOfOrderRepository.getAllRecordsFromCurDate();
    }

    public void addNewOrderWithCurDate () throws SQLException {
        modifier.createNewOrder(getAllRecordsFromCurDate());
    }

    public void deleteOrdersThatContainTheProductWithQuantity (int productID, int quantityOfProduct) {
        List<Order> orders = ordersRepository.getNonDetOrdersByProductIDAndQuantity(productID, quantityOfProduct);

        if (!orders.isEmpty()){
            orders.forEach(Order ->
                    Order.setRecordsOfOrder(recordsOfOrderRepository.getRecordsOfOrderByOrderID(Order.getID())));
            modifier.deleteAllOrders(orders);
        }
    }
}
