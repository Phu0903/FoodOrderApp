package com.example.onlyfood.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.onlyfood.R;
import com.example.onlyfood.networking.ApiServices;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private TextView textViewTerm;
    RecyclerView popularRecyclerView, categoryRecyclerView; // RecyclerView
    ApiServices apiInterface; //Call ApiServices

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Using retrofit
        /*Retrofit retrofit = RetrofitClient.getRetrofitInstance();
        ApiServices jsonPlaceHolderApi = retrofit.create(ApiServices.class);
        //Call function
        CallListCategory(jsonPlaceHolderApi);
        CallListFoodPopular(jsonPlaceHolderApi);
        //Find Id Text
        textViewTerm = findViewById(R.id.textView2);*/
        bottomNavigationView=findViewById(R.id.button_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new HomeActivity()).commit();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new HomeActivity();
                            break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                            selectedFragment).commit();
                    return true;
                }
            };
}
    //Call list data
    /*private void CallListCategory(ApiServices jsonPlaceHolderApi)
    {
       //Save List Api from data to call
        Call<List<CategoryModel>> call = jsonPlaceHolderApi.getCategory();
        call.enqueue(new Callback<List<CategoryModel>>() {
            @Override
            public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {
                if (!response.isSuccessful()) {
                    textViewTerm.setText("Code: " + response.code()); //respone status api
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
    CategoryAdapater categoryAdapter;
    private void  getCategoryData(List<CategoryModel> categoryListList) {

        categoryRecyclerView = findViewById(R.id.category_recycler); //Tim category recycler view
        categoryAdapter = new CategoryAdapater(MainActivity.this, categoryListList); //
       // RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
       // layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
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

    PopularAdapater popularAdapter;
    private void  getPopularData(List<FoodModel> popularList){

        popularRecyclerView = findViewById(R.id.popular_recycler);
        popularAdapter = new PopularAdapater(this, popularList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        popularRecyclerView.setLayoutManager(layoutManager);
        popularRecyclerView.setAdapter(popularAdapter);

    }

    private void mapping()
    {

    }*/





