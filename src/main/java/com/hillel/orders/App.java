package com.hillel.orders;

import com.hillel.orders.service.OrderServiceForEditing;
import com.hillel.orders.service.OrderServiceForReading;

import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException {
        System.out.println( "Hello World!" );

        OrderServiceForReading orderServiceForReading = new OrderServiceForReading();
        OrderServiceForEditing orderServiceForEditing = new OrderServiceForEditing();
//
//        System.out.println(orderServiceForReading.getAllProductsFromDB());
//        System.out.println();
//        System.out.println(orderServiceForReading.getListOfAllOrders());
//        System.out.println();
//        System.out.println(orderServiceForReading.getAllDetailedOrdersFromDB());
//        System.out.println();
//        System.out.println("the last part");
//        System.out.println(orderServiceForReading.getDetailedOrderByNumber(2));
        System.out.println(orderServiceForReading.getDetailedOrderByOrderId(1));
        System.out.println("=======================================================================");
//        System.out.println(orderServiceForReading.getOrderById(1));
        System.out.println(orderServiceForReading.getByTotMaxAndQuantityOfDifGoods(100, 2));
        System.out.println("=======================================================================");

        System.out.println(orderServiceForReading.getNonDetOrdersThatContainTheProduct( 2));

        System.out.println("=======================================================================");
        System.out.println("Don't contain product_id=2");
        System.out.println("=======================================================================");
        System.out.println(orderServiceForReading.getNonDetOrdersThatDoNotContainTheProductAndContainCurDate( 2));
        System.out.println("=======================================================================");
        System.out.println("now printing all today's records");
        System.out.println("=======================================================================");

//        System.out.println(orderServiceForEditing.getAllRecordsFromCurDate());

        orderServiceForEditing.addNewOrderWithCurDate();
        System.out.println("everything is OK");
    }
}
