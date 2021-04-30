package com.example.onlyfood.api;
import com.example.onlyfood.model.Category;
import com.example.onlyfood.model.FoodData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServices {
@GET("indexRouter/getSold/Sold")
    Call<List<FoodData>>  getFood();
    @GET("categoryRouter/getCategory")
    Call<List<Category>>  getCategory();
}
