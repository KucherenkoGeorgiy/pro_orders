package com.hillel.orders.service;

import com.hillel.orders.entity.DetailedOrder;
import com.hillel.orders.entity.OrderProduct;
import com.hillel.orders.entity.Orders;
import com.hillel.orders.entity.Product;
import com.hillel.orders.repository.OrderProductRepository;
import com.hillel.orders.repository.OrdersRepository;
import com.hillel.orders.repository.ProductRepository;

import java.util.List;

public class OrderService {
    ProductRepository productRepository;
    OrdersRepository ordersRepository;
    OrderProductRepository orderProductRepository;


    public OrderService() {
        productRepository = new ProductRepository();
        ordersRepository = new OrdersRepository();
        orderProductRepository = new OrderProductRepository();
    }


    public List<Product> getAllProductsFromDB () {

        return productRepository.getAllProductsFromDB();
    }

    public List<Orders> getListOfAllOrders () {

        return ordersRepository.getAllOrdersFromDB();
    }

    public List<OrderProduct> getAllDetailedOrdersFromDB() {
        return orderProductRepository.getAllDetailedOrdersFromDB();
    }

    public DetailedOrder getDetailedOrderByNumber (int orderID){

        return ordersRepository.getAllDetailsOfOrderByOrderID(orderID);
    }
}
