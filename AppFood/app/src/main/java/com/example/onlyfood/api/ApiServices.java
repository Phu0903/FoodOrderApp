package com.example.onlyfood.api;
import com.example.onlyfood.model.Currency;
import com.example.onlyfood.model.FoodData;
import com.example.onlyfood.model.ProductFood;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {
//http://apilayer.net/api/live?access_key=843d4d34ae72b3882e3db642c51e28e6&currencies=VND&source=USD&format=1
/*Gson gson= new GsonBuilder()
        .setDateFormat("yyyy-MM-đ HH:mm:ss")
        .create();
ApiServices apiServices =new Retrofit.Builder()
                        .baseUrl("http://apilayer.net")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(ApiServices.class);*/
/*@GET("api/live")
Call<Currency> convertUSDtoVND(@Query("access_key") String access_key,
                               @Query("currencies") String currencies,
                               @Query("source") String source,
                               @Query("format") int format);*/
@GET("indexRouter/getFood")
    Call<List<FoodData>>  getFood();
}
