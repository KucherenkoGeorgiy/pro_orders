package com.hillel.orders;

import com.hillel.orders.service.OrderServiceForEditing;
import com.hillel.orders.service.OrderServiceForReading;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        /*
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
        System.out.println("============================LOOK ABOVE===========================================");

        System.out.println(orderServiceForReading.getNonDetOrdersThatContainTheProduct( 2));

        System.out.println("=======================================================================");
        System.out.println("Contain product_id=2");
        System.out.println("=======================================================================");
        System.out.println(orderServiceForReading.getNonDetOrdersThatDoNotContainTheProductAndContainCurDate( 2));
        System.out.println("=======================================================================");
        System.out.println("now printing all today's records");
        System.out.println("=======================================================================");

//        System.out.println(orderServiceForEditing.getAllRecordsFromCurDate());

        orderServiceForEditing.addNewOrderWithCurDate();
        System.out.println("everything is OK");*/
        System.out.println("trying to delete product=1 quantity=1");
        orderServiceForEditing.deleteOrdersThatContainTheProductWithQuantity(1, 1);
        System.out.println("deleted!");
//
//        List<String> peopleGreetings = Stream.of("Elena", "John", "Alex", "Jim", "Sara")
//                .peek(x -> System.out.println("Hello " + x + " !!!"))
//                .collect(Collectors.toList());
//
//        System.out.println("==============");
//        System.out.println(peopleGreetings);

//        ArrayList<String> nameList = new ArrayList<>();
//        nameList.add("Elena");
//        nameList.add("John");
//        nameList.add("Alex");
//        nameList.add("Jim");
//        nameList.add("Sara");
//
//        Stream.generate(() -> {
//            int value = (int) (Math.random() * nameList.size());
//            return nameList.get(value);
//        }).limit(5).forEach(System.out::println);

    }
}
