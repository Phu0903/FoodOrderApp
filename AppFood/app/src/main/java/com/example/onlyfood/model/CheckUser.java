package com.example.onlyfood.model;

public class CheckUser {
    private String email;
    private String phonenumber;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public CheckUser(String email, String phonenumber) {
        this.email = email;
        this.phonenumber = phonenumber;
    }
}
