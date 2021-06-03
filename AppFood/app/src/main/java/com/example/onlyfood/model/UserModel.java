package com.example.onlyfood.model;

public class UserModel {
      /* "_id": "60b706529f781f0022bc3fd2",
               "_email": "phu09032000",
               "_password": "$2a$10$9QcIAn.UWzL6j9f39rQqtuaeVR2TjKuLhQgflhdwGD27EptgrehTi",
               "_name": "Phú",
               "_PhoneNumber": 2313124212,
               "_Address": "3123123",*/
    private String _id;
    private String _email;
    private String _password;
    private String _PhoneNumber;
    private String _Address;

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    private String _name;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public String get_PhoneNumber() {
        return _PhoneNumber;
    }

    public void set_PhoneNumber(String _PhoneNumber) {
        this._PhoneNumber = _PhoneNumber;
    }

    public String get_Address() {
        return _Address;
    }

    public void set_Address(String _Address) {
        this._Address = _Address;
    }
}
