package com.hillel.orders;

import com.hillel.orders.service.OrderServiceForEditing;
import com.hillel.orders.service.OrderServiceForReading;

import java.sql.SQLException;

public class App {
    private static final String SEPARATOR = "======================";

    public static void main(String[] args) throws SQLException {
        OrderServiceForReading orderServiceForReading = new OrderServiceForReading();
        OrderServiceForEditing orderServiceForEditing = new OrderServiceForEditing();

        System.out.println(SEPARATOR);

        System.out.println(orderServiceForReading.getDetailedOrderByOrderId(1));
        System.out.println(SEPARATOR + " above you can see details of order 1" + SEPARATOR);

        System.out.println(orderServiceForReading.getByTotMaxAndQuantityOfDifGoods(100, 2));
        System.out.println(SEPARATOR + " above you can see orders where TOTAL is less than 100 and quantity of different goods is 2" + SEPARATOR);

        System.out.println(orderServiceForReading.getOrdersThatContainTheProduct(3));
        System.out.println(SEPARATOR + " above you can see orders that contain product with product_id = 3" + SEPARATOR);

        System.out.println(orderServiceForReading.getOrdersThatDoNotContainTheProductAndContainCurDate(3));
        System.out.println(SEPARATOR + " above you can see orders that don't contain product_ID=3 and were made today" + SEPARATOR);

        orderServiceForEditing.addNewOrderWithCurDate();

        orderServiceForEditing.deleteOrdersThatContainTheProductWithQuantity(2, 400);
    }
}
