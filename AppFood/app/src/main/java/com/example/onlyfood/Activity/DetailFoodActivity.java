package com.example.onlyfood.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.NoCopySpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.onlyfood.R;
import com.example.onlyfood.networking.ApiServices;
import com.example.onlyfood.networking.RetrofitClient;

import retrofit2.Retrofit;

public class DetailFoodActivity extends AppCompatActivity {
    ImageView imageView;
    TextView itemName, itemPrice, itemRating,itemDetail;
    RatingBar ratingBar;
    Button BackHome;
    String name, price, rating, imageUrl,ID_Product,detail,Adapater1,Adapater2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_food);
        Intent intent = getIntent();
        ID_Product = intent.getStringExtra("ID_Product");
        name = intent.getStringExtra("Name_Product");
        price = intent.getStringExtra("Price");
        imageUrl = intent.getStringExtra("Image");
        detail = intent.getStringExtra("Info");
        Adapater1=intent.getStringExtra("ListFood");
        Adapater2=intent.getStringExtra("PopularAdapater");





        imageView = findViewById(R.id.imageView5);
        itemName = findViewById(R.id.name);
        itemPrice = findViewById(R.id.price);
        itemDetail = findViewById(R.id.textView8);
        //itemRating = findViewById(R.id.rating);


        Glide.with(getApplicationContext()).load(imageUrl).into(imageView);
        itemName.setText(name);
        itemPrice.setText(price);
        itemDetail.setText(detail);
        //itemRating.setText(rating);
        //ratingBar.setRating(Float.parseFloat(rating));


       BackHome=findViewById(R.id.back_home2);
        BackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    finish();

            }
        });





    }
}
