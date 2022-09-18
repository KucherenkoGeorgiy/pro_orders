package com.hillel.orders;

import com.hillel.orders.connection.ConnectionProvider;
import com.hillel.orders.service.OrderService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        OrderService orderService = new OrderService();

        System.out.println(orderService.getAllProductsFromDB());
        System.out.println();
        System.out.println(orderService.getListOfAllOrders());
        System.out.println();
        System.out.println(orderService.getAllDetailedOrdersFromDB());
        System.out.println();
        System.out.println("the last part");
        System.out.println(orderService.getDetailedOrderByNumber(1));


    }
}
