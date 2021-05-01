package com.example.onlyfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.onlyfood.api.ApiServices;
import com.example.onlyfood.api.RetrofitClient;
import com.example.onlyfood.model.Category;
import com.example.onlyfood.model.FoodData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private TextView textViewTerm;
    RecyclerView popularRecyclerView, categoryRecyclerView, allMenuRecyclerView;
    ApiServices apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewTerm = findViewById(R.id.textView2);

        Retrofit retrofit = RetrofitClient.getRetrofitInstance();
        ApiServices jsonPlaceHolderApi = retrofit.create(ApiServices.class);
        CallListCategory(jsonPlaceHolderApi);
        CallListFoodPopular(jsonPlaceHolderApi);

    }
    private void CallListCategory(ApiServices jsonPlaceHolderApi)
    {
        Call<List<Category>> call = jsonPlaceHolderApi.getCategory();

        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (!response.isSuccessful()) {
                    textViewTerm.setText("Code: " + response.code());
                    return;
                }
                List<Category> posts = response.body();
                getCategoryData(posts);

            }
            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                textViewTerm.setText(t.getMessage());
            }
        });
    }

    CategoryAdapter categoryAdapter;
    private void  getCategoryData(List<Category> categoryListList){

        categoryRecyclerView = findViewById(R.id.category_recycler);
        categoryAdapter = new CategoryAdapter(this, categoryListList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        categoryRecyclerView.setLayoutManager(layoutManager);
        categoryRecyclerView.setAdapter(categoryAdapter);

    }
    private void CallListFoodPopular(ApiServices jsonPlaceHolderApi)
    {
        Call<List<FoodData>> call = jsonPlaceHolderApi.getFood();

        call.enqueue(new Callback<List<FoodData>>() {
            @Override
            public void onResponse(Call<List<FoodData>> call, Response<List<FoodData>> response) {
                if (!response.isSuccessful()) {
                    textViewTerm.setText("Code: " + response.code());
                    return;
                }
                List<FoodData> gets = response.body();
                getPopularData(gets);
            }
            @Override
            public void onFailure(Call<List<FoodData>> call, Throwable t) {
                textViewTerm.setText(t.getMessage());
            }
        });
    }
    PopularAdapter popularAdapter;
    private void  getPopularData(List<FoodData> popularList){

        popularRecyclerView = findViewById(R.id.popular_recycler);
        popularAdapter = new PopularAdapter(this, popularList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        popularRecyclerView.setLayoutManager(layoutManager);
        popularRecyclerView.setAdapter(popularAdapter);

    }

}


