package com.hillel.orders.entity;

public class OrderProduct {
    private int orderID;
    private int productID;
    private int quantityOfProduct;

    public OrderProduct(int orderID, int productID, int quantityOfProduct) {
        this.orderID = orderID;
        this.productID = productID;
        this.quantityOfProduct = quantityOfProduct;
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "orderID=" + orderID +
                ", productID=" + productID +
                ", quantityOfProduct=" + quantityOfProduct +
                '}';
    }
}
