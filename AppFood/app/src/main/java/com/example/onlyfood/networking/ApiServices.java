package com.example.onlyfood.networking;
import com.example.onlyfood.model.CartModel;
import com.example.onlyfood.model.CategoryModel;
import com.example.onlyfood.model.FoodModel;
import com.example.onlyfood.model.LoginRegisterModel;
import com.example.onlyfood.model.UserModel;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServices {
    @GET("productRouter/getSold/Sold")  //url get food popular
    Call<List<FoodModel>>  getFood();
    @GET("categoryRouter/getCategory") //url get category
    Call<List<CategoryModel>>  getCategory();
    @GET("productRouter/getFoodByCategory")//url get Food by Category
    Call<List<FoodModel>> getFoodbyCategory(@Query("Category") String category);
    @POST("cartRouter/addTocart")
    Call<CartModel> createPost(@Body CartModel post);

    @POST("usersRouter/dangnhap")
    Call<LoginRegisterModel> Login(@Body LoginRegisterModel loginRegisterModel);

    @GET("/usersRouter/inforUser")
    Call<UserModel> getInforUser(@Query("email") String email);

}
//get
//parmas
//post