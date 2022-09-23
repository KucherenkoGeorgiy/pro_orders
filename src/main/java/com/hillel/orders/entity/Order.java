package com.hillel.orders.entity;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public class Order {
    private int ID;
    private Date date;
    private List<RecordsOfOrder> recordsOfOrder;

    public Order(int ID, Date date) {
        this.ID = ID;
        this.date = date;
    }

    public void setRecordsOfOrder(List<RecordsOfOrder> recordsOfOrder) {
        this.recordsOfOrder = recordsOfOrder;
    }

    public int getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "Order{" +
                "ID=" + ID +
                ", date=" + date +
                ", recordsOfOrder=\n" + recordsOfOrder +
                '}';
    }
}
