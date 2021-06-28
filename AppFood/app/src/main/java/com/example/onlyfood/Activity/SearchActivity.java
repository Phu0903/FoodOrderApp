package com.example.onlyfood.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.onlyfood.Adapater.ListFoodAdapater;
import com.example.onlyfood.R;
import com.example.onlyfood.model.FoodModel;
import com.example.onlyfood.networking.ApiServices;
import com.example.onlyfood.networking.RetrofitClient;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SearchActivity extends AppCompatActivity {
    EditText editText;
    String email;
    RecyclerView popularRecyclerView;
    ListFoodAdapater ListFoodAdapater;
    Button BackHome;
    List<FoodModel> gets;
    //using retrofit call api
    Retrofit retrofit = RetrofitClient.getRetrofitInstance();
    ApiServices jsonPlaceHolderApi = retrofit.create(ApiServices.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        Bundle bundle = getIntent().getExtras();
        email = bundle.getString("search_email");
        init();

        //call food
        callAllFood(jsonPlaceHolderApi);
        //search
        initSearchWidgets();
        //BackHome
        BackHome();
    }
    //pause
    @Override
    public void onPause() {
        super.onPause();
    }
    //Restart
    @Override
    public void onStart() {
        super.onStart();
        callAllFood(jsonPlaceHolderApi);

    }
    private void init()
    {
        popularRecyclerView =  findViewById(R.id.list_seacrh);
        editText = (EditText) findViewById(R.id.edsearch);
        BackHome = findViewById(R.id.back_home4);
    }

    //back home
    private void BackHome()
    {
        BackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void callAllFood(ApiServices jsonPlaceHolderApi)
    {
        Call<List<FoodModel>> call = jsonPlaceHolderApi.getFullProducts();
        call.enqueue(new Callback<List<FoodModel>>() {
            @Override
            public void onResponse( Call<List<FoodModel>> call, Response<List<FoodModel>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                else{
                    gets = response.body();
                    getProductData(gets);
                }
            }
            @Override
            public void onFailure( Call<List<FoodModel>> call, Throwable t) {

            }
        });
    }


    //search
    private void initSearchWidgets() {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                ArrayList<FoodModel> postNewsModelArrayList = new ArrayList<>();

                for (FoodModel post: gets) {
                    if( post.get_NameProduct().toLowerCase().contains(s.toString().toLowerCase())) {
                        postNewsModelArrayList.add(post);
                    }
                }
                ListFoodAdapater = new ListFoodAdapater(SearchActivity.this, postNewsModelArrayList,email);
                popularRecyclerView.setAdapter(ListFoodAdapater);

            }
        });
    }
    private void  getProductData(List<FoodModel> popularList){
        ListFoodAdapater = new ListFoodAdapater(this, popularList,email);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL, false);
        popularRecyclerView.setLayoutManager(layoutManager);
        popularRecyclerView.setAdapter(ListFoodAdapater);

    }
}