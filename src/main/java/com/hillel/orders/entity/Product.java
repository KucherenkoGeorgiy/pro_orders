package com.hillel.orders.entity;

public class Product {
    private int id;
    private String name;
    private String describing;
    private int price;

    public Product(int id, String name, String describing, int price) {
        this.id = id;
        this.name = name;
        this.describing = describing;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", describing='" + describing + '\'' +
                ", price=" + price +
                '}';
    }
}
