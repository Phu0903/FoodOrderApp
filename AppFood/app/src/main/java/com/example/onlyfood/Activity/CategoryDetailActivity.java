package com.example.onlyfood.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.onlyfood.R;
import com.example.onlyfood.networking.ApiServices;
import com.example.onlyfood.networking.RetrofitClient;

import retrofit2.Retrofit;

public class CategoryDetailActivity extends AppCompatActivity {
    ImageView imageView;
    TextView itemName, itemPrice, itemRating;
    String name,urlImage;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_food_by_category);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        urlImage = intent.getStringExtra("image");
        imageView = findViewById(R.id.category_image2);
        itemName = findViewById(R.id.NameCategory);
        Glide.with(getApplicationContext()).load(getApplicationContext().getResources().
                getIdentifier(urlImage, "drawable", getApplicationContext().getPackageName())).
                into(imageView);
        itemName.setText(name);
        Retrofit retrofit = RetrofitClient.getRetrofitInstance();
        ApiServices jsonPlaceHolderApi = retrofit.create(ApiServices.class);



    }
}
