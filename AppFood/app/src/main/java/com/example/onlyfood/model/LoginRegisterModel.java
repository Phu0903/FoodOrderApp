package com.example.onlyfood.model;

public class LoginRegisterModel {

    /* '_email': email,
            '_password': hashPassword,
            '_name': name,
            '_InfoUser': InfoUser,
            '_PhoneNumber': phonenumber,
            '_Address': address*/
    private String email;
    private String password;
    private String name;
    private  String phonenumber;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public LoginRegisterModel(String email, String password, String name, String phonenumber) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phonenumber = phonenumber;
    }

    public LoginRegisterModel(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
