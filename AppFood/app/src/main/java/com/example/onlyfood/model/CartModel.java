package com.example.onlyfood.model;

public class CartModel {
    private String email;
    private String ProductID;
    private String quantity;
    private String _Image;
    private String _NameProduct;
    private String _Price;
    private String _email;
    private String _ProductID;
    private String _quantity;

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

    public String get_quantity() {
        return _quantity;
    }

    public void set_quantity(String _quantity) {
        this._quantity = _quantity;
    }

    public String get_Image() {
        return _Image;
    }

    public void set_Image(String _Image) {
        this._Image = _Image;
    }

    public String get_NameProduct() {
        return _NameProduct;
    }

    public void set_NameProduct(String _NameProduct) {
        this._NameProduct = _NameProduct;
    }

    public String get_Price() {
        return _Price;
    }

    public void set_Price(String _Price) {
        this._Price = _Price;
    }

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
