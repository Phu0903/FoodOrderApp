package com.example.onlyfood.networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://app-food-using-project-java.herokuapp.com/";//Link api
    //192.168.1.7
   //http://10.45.152.14:3000/
    //http://10.0.135.172:3000/
    //Using Retrofit
    public static Retrofit getRetrofitInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;

    }
}
