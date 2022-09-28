package com.hillel.orders.entity;

import java.sql.Date;
import java.util.List;

public class Order {
    private int id;
    private Date date;
    private List<RecordsOfOrder> recordsOfOrder;

    public Order(int id, Date date) {
        this.id = id;
        this.date = date;
    }

    public void setRecordsOfOrder(List<RecordsOfOrder> recordsOfOrder) {
        this.recordsOfOrder = recordsOfOrder;
    }

    public int getId() {
        return id;
    }

    public List<RecordsOfOrder> getRecordsOfOrder() {
        return recordsOfOrder;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", recordsOfOrder=\n" + recordsOfOrder +
                '}';
    }
}
