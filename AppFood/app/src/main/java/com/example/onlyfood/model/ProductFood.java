package com.example.onlyfood.model;

public class ProductFood {
    private String _ProductID;
    private String _NameProdcut;
    private String _Price;
    private String _Info;
    private String _Image;
    private String _Category;
    private  int _Sold;

    public String get_ProductID() {
        return _ProductID;
    }

    public void set_ProductID(String _ProductID) {
        this._ProductID = _ProductID;
    }

    public String get_NameProdcut() {
        return _NameProdcut;
    }

    public void set_NameProdcut(String _NameProdcut) {
        this._NameProdcut = _NameProdcut;
    }

    public String get_Price() {
        return _Price;
    }

    public void set_Price(String _Price) {
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

    public int get_Sold() {
        return _Sold;
    }

    public void set_Sold(int _Sold) {
        this._Sold = _Sold;
    }
}
