package com.hillel.orders.entity;

import java.sql.Date;
import java.util.Calendar;

public class Orders {
    private int ID;
    private Date date;

    public Orders(int ID, Date date) {
        this.ID = ID;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "ID=" + ID +
                ", date=" + date +
                '}';
    }
}
