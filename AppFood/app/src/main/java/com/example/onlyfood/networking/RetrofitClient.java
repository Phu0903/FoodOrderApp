package com.example.onlyfood.networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;
   private static final String BASE_URL = "https://appoderfood.herokuapp.com/";//Link api
   //private static final String BASE_URL = "http://192.168.1.2:3000/";//Link api
    //192.168.1.7
   //http://172.20.13.243:3000/
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
