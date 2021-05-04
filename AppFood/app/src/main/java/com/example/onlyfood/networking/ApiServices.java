package com.example.onlyfood.networking;
import com.example.onlyfood.model.CategoryModel;
import com.example.onlyfood.model.FoodModel;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {
    @GET("productRouter/getSold/Sold")  //url get food popular
    Call<List<FoodModel>>  getFood();
    @GET("categoryRouter/getCategory") //url get category
    Call<List<CategoryModel>>  getCategory();
    @GET("productRouter/getFoodByCategory")//url get Food by Category
    Call<List<FoodModel>> getFoodbyCategory(@Query("Category") String category);

}
