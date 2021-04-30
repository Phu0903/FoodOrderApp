package com.example.onlyfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlyfood.api.ApiServices;
import com.example.onlyfood.api.RetrofitClient;
import com.example.onlyfood.model.Category;
import com.example.onlyfood.model.FoodData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textViewTerm;
    RecyclerView popularRecyclerView, recommendedRecyclerView, allMenuRecyclerView;
    ApiServices apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewTerm = findViewById(R.id.textView2);

        Retrofit retrofit = RetrofitClient.getRetrofitInstance();
        ApiServices jsonPlaceHolderApi = retrofit.create(ApiServices.class);
        Call<List<Category>> call = jsonPlaceHolderApi.getCategory();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (!response.isSuccessful()) {
                    textViewTerm.setText("Code: " + response.code());
                    return;
                }
                List<Category> posts = response.body();
                /*for (FoodData post : posts) {
                    String content = "";
                    content += "ID: " + post.get_NameProduct() + "\n";
                    content += "User ID: " + post.get_NameProduct() + "\n";
                    content += "Title: " + post.get_ProductID() + "\n";
                    content += "Text: " + post.get_Price() + "\n\n";
                    textViewTerm.append(content);
                }*/
                getPopularData(posts);
            }
            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                textViewTerm.setText(t.getMessage());
            }
        });
    }

    CategoryAdapter categoryAdapter;
    private void  getPopularData(List<Category> popularList){

        popularRecyclerView = findViewById(R.id.popular_recycler);
        categoryAdapter = new CategoryAdapter(this, popularList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        popularRecyclerView.setLayoutManager(layoutManager);
        popularRecyclerView.setAdapter(categoryAdapter);

    }
}


