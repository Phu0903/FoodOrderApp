package com.example.onlyfood.model;

public class FavoriteModel {
    private String email;
    private String id_product;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    public FavoriteModel(String email, String id_product) {
        this.email = email;
        this.id_product = id_product;
    }
}
