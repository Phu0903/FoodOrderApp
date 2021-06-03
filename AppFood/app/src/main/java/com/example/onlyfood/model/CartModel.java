package com.example.onlyfood.model;

public class CartModel {
    private String email;
    private String ProductID;
    private String quantity;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public CartModel(String email, String productID, String quantity) {
        this.email = email;
        this.ProductID = productID;
        this.quantity = quantity;
    }
}
