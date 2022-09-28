package com.hillel.orders.entity;

public class RecordsOfOrder {
    private Product product;
    private int quantityOfProduct;

    public RecordsOfOrder(Product product, int quantityOfProduct) {
        this.product = product;
        this.quantityOfProduct = quantityOfProduct;
    }

    @Override
    public String toString() {
        return "RecordsOfOrder{" +
                "product=" + product +
                ", quantityOfProduct=" + quantityOfProduct +
                '}' + "\n";
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantityOfProduct() {
        return quantityOfProduct;
    }
}
