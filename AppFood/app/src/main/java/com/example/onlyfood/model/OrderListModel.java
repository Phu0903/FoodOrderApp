package com.example.onlyfood.model;

import java.io.Serializable;

public class OrderListModel implements Serializable {

    private String _NameProduct;
    private String _Image;
    private String _Quantity;
    private String _Price;

    public String get_NameProduct() {
        return _NameProduct;
    }

    public void set_NameProduct(String _NameProduct) {
        this._NameProduct = _NameProduct;
    }

    public String get_Image() {
        return _Image;
    }

    public void set_Image(String _Image) {
        this._Image = _Image;
    }

    public String get_Quantity() {
        return _Quantity;
    }

    public void set_Quantity(String _Quantity) {
        this._Quantity = _Quantity;
    }

    public String get_Price() {
        return _Price;
    }

    public void set_Price(String _Price) {
        this._Price = _Price;
    }

    public OrderListModel(String _NameProduct, String _Image, String _Quantity, String _Price) {
        this._NameProduct = _NameProduct;
        this._Image = _Image;
        this._Quantity = _Quantity;
        this._Price = _Price;
    }
}
