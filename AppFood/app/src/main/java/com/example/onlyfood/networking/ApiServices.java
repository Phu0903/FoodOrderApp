package com.example.onlyfood.networking;
import androidx.core.content.pm.PermissionInfoCompat;

import com.example.onlyfood.model.CartModel;
import com.example.onlyfood.model.CategoryModel;
import com.example.onlyfood.model.CheckUser;
import com.example.onlyfood.model.FavoriteModel;
import com.example.onlyfood.model.FoodModel;
import com.example.onlyfood.model.LoginRegisterModel;
import com.example.onlyfood.model.OrderModel;
import com.example.onlyfood.model.UserModel;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiServices {
    @GET("/productRouter/getSold/Sold")  //url get food popular
    Call<List<FoodModel>>  getFood();
    @GET("/productRouter/getFood")//Lay toan bo san pham
    Call<List<FoodModel>> getFullProducts();
    @GET("/categoryRouter/getCategory") //url get category
    Call<List<CategoryModel>>  getCategory();
    @GET("/productRouter/getFoodByCategory")//url get Food by Category
    Call<List<FoodModel>> getFoodbyCategory(@Query("Category") String category);

    @POST("/usersRouter/CheckUser")//Check thong tin User
    Call <CheckUser> CheckUser(@Body CheckUser userModel);
    @POST("/usersRouter/dangnhap")//url dang nhap
    Call<LoginRegisterModel> Login(@Body LoginRegisterModel loginRegisterModel);
    @POST("/usersRouter/dangky")//url dang ky
    Call<LoginRegisterModel> Register(@Body LoginRegisterModel loginRegisterModel);
    @GET("/usersRouter/inforUser")//url thong tin user
    Call<UserModel> getInforUser(@Query("email") String email);

    @POST("/cartRouter/addTocart")//url add to cart
    Call<CartModel> createPost(@Body CartModel post);
    @GET("/cartRouter/getCart")//url thong tin gio hang
    Call<List<CartModel>> getCart(@Query("Email") String email);
    @DELETE("/cartRouter/delete")//url detele san pham
    Call<CartModel> deleteItem(@Query("ProductID") String productid, @Query("email") String email);

    @POST("/orderRouter/new-oder")//url don hang moi
    Call<OrderModel> postOrder(@Body OrderModel orderModel);
    @GET("/orderRouter/listbyemail")//lay don hang theo email
    Call<List<OrderModel>> getOder(@Query("email") String email);


    @PUT("/usersRouter/Addfavorite")//Them mon an yeu thich
    Call <FavoriteModel> AddFavorite(@Body FavoriteModel favoriteModel);
    @PUT("/usersRouter/Removefavorite")//Xoa mon an yeu thich
    Call <FavoriteModel> RemoveFavorite(@Body FavoriteModel favoriteModel);
    @POST("/usersRouter/getFavorite")//Lay mon an yeu thich
    Call <FavoriteModel> GetFavorite(@Body FavoriteModel favoriteModel);
    @GET("/usersRouter/GetProductFavorite") //Lay List cac mon an yeu thich
    Call <List<FoodModel>> getFavoriteFood (@Query("email") String email);

}
