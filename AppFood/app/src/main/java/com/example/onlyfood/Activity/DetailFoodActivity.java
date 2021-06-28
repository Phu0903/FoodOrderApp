package com.example.onlyfood.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.onlyfood.R;
import com.example.onlyfood.model.CartModel;
import com.example.onlyfood.model.FavoriteModel;
import com.example.onlyfood.networking.ApiServices;
import com.example.onlyfood.networking.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DetailFoodActivity extends AppCompatActivity {
    ImageView imageView,favorite_btn1,favorite_btn2;
    TextView itemName, itemPrice,itemDetail,Number;
    Button BackHome,AddtoCart,Add,Remove;
    String name, price,imageUrl,ID_Product,detail,Adapater1,Adapater2,email;
    String status_favorite;
    Retrofit retrofit = RetrofitClient.getRetrofitInstance();
    ApiServices jsonPlaceHolderApi = retrofit.create(ApiServices.class);
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_food);
        init();
        ItentFromAnotherActivity();
        setLayout();
        ClicKBackHome();
        ClickAddToCart();
        Favorite();


    }

    public void CallFavorite(ApiServices jsonPlaceHolderApi)
    {
        FavoriteModel favoriteModel = new FavoriteModel(email,ID_Product);
        Call<FavoriteModel> call = jsonPlaceHolderApi.GetFavorite(favoriteModel);

        call.enqueue(new Callback<FavoriteModel>() {
            @Override
            public void onResponse(Call<FavoriteModel> call, Response<FavoriteModel> response) {
                if (response.isSuccessful()) {
                  
                    if(response.body().getMessage().equals("Yes"))
                    {
                        favorite_btn1.setVisibility(View.VISIBLE);
                        favorite_btn2.setVisibility(View.GONE);
                    }
                    else{

                        favorite_btn1.setVisibility(View.GONE);
                        favorite_btn2.setVisibility(View.VISIBLE);
                    }

                }
            }
            @Override
            public void onFailure(Call<FavoriteModel> call, Throwable t) {
                Toast.makeText(DetailFoodActivity.this,"Failed"+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
    public void Favorite(){
        CallFavorite(jsonPlaceHolderApi);
        //Add
        favorite_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favorite_btn1.setEnabled(false);
                favorite_btn1.setVisibility(View.GONE);
                favorite_btn2.setVisibility(View.VISIBLE);
                RemoveFavorite(jsonPlaceHolderApi);

            }
        });
        favorite_btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                favorite_btn2.setEnabled(false);
                favorite_btn2.setVisibility(View.GONE);
                favorite_btn1.setVisibility(View.VISIBLE);
                AddFavorite(jsonPlaceHolderApi);
            }
        });
    }

    //Add favorite
    private void AddFavorite(ApiServices jsonPlaceHolderApi){
        FavoriteModel favoriteModel = new FavoriteModel(email,ID_Product);

        Call<FavoriteModel> call = jsonPlaceHolderApi.AddFavorite(favoriteModel);
        call.enqueue(new Callback<FavoriteModel>() {
            @Override
            public void onResponse(Call<FavoriteModel> call, Response<FavoriteModel> response) {
                if(response.isSuccessful()){
                }
            }

            @Override
            public void onFailure(Call<FavoriteModel> call, Throwable t) {
                Log.d("Oke","Ko Oke");
            }
        });
    }

    //Remove favorite
    private void RemoveFavorite(ApiServices jsonPlaceHolderApi)
    {
        FavoriteModel favoriteModel = new FavoriteModel(email,ID_Product);

        Call<FavoriteModel> call = jsonPlaceHolderApi.RemoveFavorite(favoriteModel);
        call.enqueue(new Callback<FavoriteModel>() {
            @Override
            public void onResponse(Call<FavoriteModel> call, Response<FavoriteModel> response) {
                if(response.isSuccessful()){

                }
            }

            @Override
            public void onFailure(Call<FavoriteModel> call, Throwable t) {
                Log.d("Oke","Ko Oke");
            }
        });
    }
    private void ClickAddToCart()
    {

        AddtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPost(jsonPlaceHolderApi);
            }
        });
    }

    private void ClicKBackHome()
    {
        BackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }

        });
    }
    private  void setLayout()
    {
        Glide.with(getApplicationContext())
                .load(getResources()
                        .getIdentifier(imageUrl, "drawable", this.getPackageName())).into(imageView);
        itemName.setText(name);
        itemPrice.setText("$"+price);
        itemDetail.setText(detail);
    }
    private void ItentFromAnotherActivity()
    {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        ID_Product = bundle.getString("ID_Product");
        name = bundle.getString("Name_Product");
        price = bundle.getString("Price");
        imageUrl = bundle.getString("Image");
        detail = bundle.getString("Info");
        Adapater1=bundle.getString("ListFood");
        Adapater2=bundle.getString("PopularAdapater");
        email = bundle.getString("username");

    }
    public void countIN(View view)
    {
        count++;
        Number.setText(Integer.toString(count));
    }
    public void countDE(View view)
    {
        count--;
        if(count < 0)
        {
            count=0;
            Number.setText(Integer.toString(0));
        }
        else{
            Number.setText(Integer.toString(count));
        }
    }
    private void createPost(ApiServices jsonPlaceHolderApi){
        CartModel post = new CartModel(email,ID_Product,Integer.toString(count));

        Call<CartModel> call = jsonPlaceHolderApi.createPost(post);
        call.enqueue(new Callback<CartModel>() {
            @Override
            public void onResponse(Call<CartModel> call, Response<CartModel> response) {
                if(response.isSuccessful()){
                    Toast.makeText(DetailFoodActivity.this,response.body().getMessage(),Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(DetailFoodActivity.this,"failed",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<CartModel> call, Throwable t) {
                   Toast.makeText(DetailFoodActivity.this,"Failed"+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public  void init()
    {
        AddtoCart = findViewById(R.id.AddToCart);
        BackHome=findViewById(R.id.back_home2);
        imageView = findViewById(R.id.imageView5);
        itemName = findViewById(R.id.name);
        itemPrice = findViewById(R.id.cart_totalprice);
        itemDetail = findViewById(R.id.textView8);
        Number= findViewById(R.id.number);
        Add = findViewById(R.id.add);
        Remove = findViewById(R.id.remove);
        favorite_btn1 = findViewById(R.id.fvr_btn_1);
        favorite_btn2 = findViewById(R.id.fvr_btn_2);


    }


}
