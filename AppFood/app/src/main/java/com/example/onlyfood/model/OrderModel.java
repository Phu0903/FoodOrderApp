package com.example.onlyfood.model;

import java.util.Date;

public class OrderModel {
    private String email;
    private String total;
    private String address;
    private String phonenumber;
    private String message;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public OrderModel(String email, String total, String address, String phonenumber) {
        this.email = email;
        this.total = total;
        this.address = address;
        this.phonenumber = phonenumber;
    }

    private String _email;
    private  String _OrderID;
    private String _total;
    private  String _status;
    private  String _address;
    private  String _phonenumber;
    private Date _createDay;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_OrderID() {
        return _OrderID;
    }

    public void set_OrderID(String _OrderID) {
        this._OrderID = _OrderID;
    }

    public String get_total() {
        return _total;
    }

    public void set_total(String _total) {
        this._total = _total;
    }

    public String get_status() {
        return _status;
    }

    public void set_status(String _status) {
        this._status = _status;
    }

    public String get_address() {
        return _address;
    }

    public void set_address(String _address) {
        this._address = _address;
    }

    public String get_phonenumber() {
        return _phonenumber;
    }

    public void set_phonenumber(String _phonenumber) {
        this._phonenumber = _phonenumber;
    }

    public Date get_createDay() {
        return _createDay;
    }

    public void set_createDay(Date _createDay) {
        this._createDay = _createDay;
    }
}
