package com.hillel.orders.service;

import com.hillel.orders.entity.Order;
import com.hillel.orders.repository.RecordsOfOrderRepository;
import com.hillel.orders.repository.OrdersRepository;
import com.hillel.orders.repository.ProductRepository;

import java.util.List;

public class OrderServiceForReading {
    ProductRepository productRepository;
    OrdersRepository ordersRepository;
    RecordsOfOrderRepository recordsOfOrderRepository;


    public OrderServiceForReading() {
        productRepository = new ProductRepository();
        ordersRepository = new OrdersRepository();
        recordsOfOrderRepository = new RecordsOfOrderRepository();
    }



    public Order getDetailedOrderByOrderId(int orderID) {

        Order order = ordersRepository.getNonDetailedOrderByOrderID(orderID);
        order.setRecordsOfOrder(recordsOfOrderRepository.getRecordsOfOrderByOrderID(orderID));

        return order;
    }

    public List<Order> getByTotMaxAndQuantityOfDifGoods(int totalAmount, int quantityOfDifferentGoods) {
        List<Order> result = ordersRepository
                .getNonDetOrdersByMaxTotalAndQuantityOfDifferentGoods(totalAmount, quantityOfDifferentGoods);

        result.stream()
                .forEach(Order -> Order.setRecordsOfOrder(recordsOfOrderRepository.getRecordsOfOrderByOrderID(Order.getID())));



        return result;
    }

    public List<Order> getNonDetOrdersThatContainTheProduct(int productID) {
        List<Order> result = ordersRepository
                .getNonDetOrdersThatContainTheProduct(productID);

        result.stream()
                .forEach(Order -> Order.setRecordsOfOrder(recordsOfOrderRepository.getRecordsOfOrderByOrderID(Order.getID())));
        return result;
    }

    public List<Order> getNonDetOrdersThatDoNotContainTheProductAndContainCurDate(int productID) {
        List<Order> result = ordersRepository
                .getNonDetOrdersThatDoNotContainTheProductAndContainCurDate(productID);

        result.stream()
                .forEach(Order -> Order.setRecordsOfOrder(recordsOfOrderRepository.getRecordsOfOrderByOrderID(Order.getID())));
        return result;


    }

}
