package com.example.onlyfood.model;

public class CartModel {
    private String _email;
    private String _ProductID;
    private String quantity;

    public CartModel() {

    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_ProductID() {
        return _ProductID;
    }

    public void set_ProductID(String _ProductID) {
        this._ProductID = _ProductID;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public CartModel(String _email, String _ProductID, String quantity) {
        this._email = _email;
        this._ProductID = _ProductID;
        this.quantity = quantity;
    }
}
