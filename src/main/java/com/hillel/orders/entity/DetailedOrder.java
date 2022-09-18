package com.hillel.orders.entity;

import java.sql.Date;
import java.util.Map;

public class DetailedOrder {
    private int ID;
    private Date date;
    private Map<Product, Integer> goods;
    private int totalAmount;

    public DetailedOrder(int ID, Date date, Map<Product, Integer> goods, int totalAmount) {
        this.ID = ID;
        this.date = date;
        this.goods = goods;
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "DetailedOrder{" +
                "order ID=" + ID + "\n" +
                "order date=" + date + "\n" +
                "goods in order: " + goods + "\n" +
                "totalAmount=" + totalAmount +
                '}';
    }
}
