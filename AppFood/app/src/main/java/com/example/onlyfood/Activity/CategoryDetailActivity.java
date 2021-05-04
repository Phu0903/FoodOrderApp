package com.example.onlyfood.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.onlyfood.Adapater.PopularAdapter;
import com.example.onlyfood.R;
import com.example.onlyfood.model.FoodModel;
import com.example.onlyfood.networking.ApiServices;
import com.example.onlyfood.networking.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CategoryDetailActivity extends AppCompatActivity {
    RecyclerView popularRecyclerView;
    ImageView imageView;
    TextView itemName, itemPrice, itemRating;
    String name,urlImage;
    Button BackHome;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_food_by_category);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        urlImage = intent.getStringExtra("image");
        imageView = findViewById(R.id.category_image2);
        itemName = findViewById(R.id.NameCategory);
        BackHome = findViewById(R.id.back_home);
        Glide.with(getApplicationContext()).load(getApplicationContext().getResources().
                getIdentifier(urlImage, "drawable", getApplicationContext().getPackageName())).
                into(imageView);
        itemName.setText(name);

        BackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CategoryDetailActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
        Retrofit retrofit = RetrofitClient.getRetrofitInstance();
        ApiServices jsonPlaceHolderApi = retrofit.create(ApiServices.class);
        CallListFoodPopular(jsonPlaceHolderApi);

    }
    private void CallListFoodPopular(ApiServices jsonPlaceHolderApi)
    {
        Call<List<FoodModel>> call = jsonPlaceHolderApi.getFoodbyCategory(name);

        call.enqueue(new Callback<List<FoodModel>>() {
            @Override
            public void onResponse(Call<List<FoodModel>> call, Response<List<FoodModel>> response) {
                if (!response.isSuccessful()) {
                    itemName.setText("Code: " + response.code());
                    return;
                }
                List<FoodModel> gets = response.body();
                getPopularData(gets);
            }
            @Override
            public void onFailure(Call<List<FoodModel>> call, Throwable t) {
                itemName.setText(t.getMessage());
            }
        });
    }

    PopularAdapter popularAdapter;
    private void  getPopularData(List<FoodModel> popularList){

        popularRecyclerView = findViewById(R.id.list_food_recycler);
        popularAdapter = new PopularAdapter(CategoryDetailActivity.this, popularList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CategoryDetailActivity.this, LinearLayoutManager.VERTICAL, false);
        popularRecyclerView.setLayoutManager(layoutManager);
        popularRecyclerView.setAdapter(popularAdapter);

    }

}
