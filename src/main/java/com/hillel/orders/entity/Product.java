package com.hillel.orders.entity;

public class Product {
    private int ID;
    private String name;
    private String describing;
    private int price;

    public Product(int ID, String name, String describing, int price) {
        this.ID = ID;
        this.name = name;
        this.describing = describing;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", describing='" + describing + '\'' +
                ", price=" + price +
                '}';
    }
}
