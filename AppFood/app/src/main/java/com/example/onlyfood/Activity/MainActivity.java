package com.example.onlyfood.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.onlyfood.Adapater.CategoryAdapter;
import com.example.onlyfood.Adapater.PopularAdapter;
import com.example.onlyfood.R;
import com.example.onlyfood.networking.ApiServices;
import com.example.onlyfood.networking.RetrofitClient;
import com.example.onlyfood.model.CategoryModel;
import com.example.onlyfood.model.FoodModel;

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
        Call<List<CategoryModel>> call = jsonPlaceHolderApi.getCategory();

        call.enqueue(new Callback<List<CategoryModel>>() {
            @Override
            public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {
                if (!response.isSuccessful()) {
                    textViewTerm.setText("Code: " + response.code());
                    return;
                }
                List<CategoryModel> posts = response.body();
                getCategoryData(posts);

            }
            @Override
            public void onFailure(Call<List<CategoryModel>> call, Throwable t) {
                textViewTerm.setText(t.getMessage());
            }
        });
    }
    CategoryAdapter categoryAdapter;
    private void  getCategoryData(List<CategoryModel> categoryListList) {

        categoryRecyclerView = findViewById(R.id.category_recycler);
        categoryAdapter = new CategoryAdapter(this, categoryListList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        categoryRecyclerView.setLayoutManager(layoutManager);
        categoryRecyclerView.setAdapter(categoryAdapter);
    }
    private void CallListFoodPopular(ApiServices jsonPlaceHolderApi)
    {
        Call<List<FoodModel>> call = jsonPlaceHolderApi.getFood();

        call.enqueue(new Callback<List<FoodModel>>() {
            @Override
            public void onResponse(Call<List<FoodModel>> call, Response<List<FoodModel>> response) {
                if (!response.isSuccessful()) {
                    textViewTerm.setText("Code: " + response.code());
                    return;
                }
                List<FoodModel> gets = response.body();
                getPopularData(gets);
            }
            @Override
            public void onFailure(Call<List<FoodModel>> call, Throwable t) {
                textViewTerm.setText(t.getMessage());
            }
        });
    }

    PopularAdapter popularAdapter;
    private void  getPopularData(List<FoodModel> popularList){

        popularRecyclerView = findViewById(R.id.popular_recycler);
        popularAdapter = new PopularAdapter(this, popularList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        popularRecyclerView.setLayoutManager(layoutManager);
        popularRecyclerView.setAdapter(popularAdapter);

    }

}




