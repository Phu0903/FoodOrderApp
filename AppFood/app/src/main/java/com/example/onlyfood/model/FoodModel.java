package com.example.onlyfood.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FoodModel {
    private String _ProductID;
    private String _NameProduct;
    private Integer _Price;
    private String _Info;
    private String _Image;
    private String _Category;
    private String _Sold;

    public String get_Favorite() {
        return _Favorite;
    }

    public void set_Favorite(String _Favorite) {
        this._Favorite = _Favorite;
    }

    private String _Favorite;


    //Getter and Setter
    public String get_ProductID() {
        return _ProductID;
    }

    public void set_ProductID(String _ProductID) {
        this._ProductID = _ProductID;
    }

    public String get_NameProduct() {
        return _NameProduct;
    }

    public void set_NameProduct(String _NameProduct) {
        this._NameProduct = _NameProduct;
    }

    public Integer get_Price() {
        return _Price;
    }

    public void set_Price(Integer _Price) {
        this._Price = _Price;
    }

    public String get_Info() {
        return _Info;
    }

    public void set_Info(String _Info) {
        this._Info = _Info;
    }

    public String get_Image() {
        return _Image;
    }

    public void set_Image(String _Image) {
        this._Image = _Image;
    }

    public String get_Category() {
        return _Category;
    }

    public void set_Category(String _Category) {
        this._Category = _Category;
    }

    public String get_Sold() {
        return _Sold;
    }

    public void set_Sold(String _Sold) {
        this._Sold = _Sold;
    }
}
