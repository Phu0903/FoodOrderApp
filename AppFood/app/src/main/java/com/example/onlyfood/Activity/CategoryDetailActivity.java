package com.example.onlyfood.Activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.onlyfood.Adapater.ListFoodAdapater;
import com.example.onlyfood.R;
import com.example.onlyfood.model.FoodModel;
import com.example.onlyfood.networking.ApiServices;
import com.example.onlyfood.networking.RetrofitClient;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CategoryDetailActivity extends AppCompatActivity {
    RecyclerView popularRecyclerView;
    ImageView imageView;
    TextView itemName;
    String name,urlImage,email;
    Button BackHome,btn_up,btn_down,btn_fvr_up,btn_fvr_down;
    ListFoodAdapater ListFoodAdapater;
    List<FoodModel> gets;
    Retrofit retrofit = RetrofitClient.getRetrofitInstance();
    ApiServices jsonPlaceHolderApi = retrofit.create(ApiServices.class);
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_food_by_category);
        IntentFromAnotherActivity();
        init();
        LoadImage();
        ClickBackHome();
        CallListFoodPopular(jsonPlaceHolderApi);
        Filter();

    }

    private void ClickBackHome()
    {
        BackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void LoadImage()
    {
        Glide.with(getApplicationContext()).load(getApplicationContext().getResources().
                getIdentifier(urlImage, "drawable", getApplicationContext().getPackageName())).
                into(imageView);
        itemName.setText(name);
    }
    private void IntentFromAnotherActivity()
    {
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("name");
        urlImage = bundle.getString("image");
        email = bundle.getString("email");
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
                gets = response.body();
                getPopularData(gets);
            }
            @Override
            public void onFailure(Call<List<FoodModel>> call, Throwable t) {
                itemName.setText(t.getMessage());
            }
        });
    }

    private void Filter() {
        //Sort price filter
        btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<FoodModel> postPricelArrayList = new ArrayList<>();

                for (FoodModel post: gets) {
                    postPricelArrayList.add(post);
                }
                Collections.sort(postPricelArrayList, new Comparator<FoodModel>() {
                    @Override
                    public int compare(FoodModel o1, FoodModel o2) {
                        return o1.get_Price() - o2.get_Price();
                    }
                });
                ListFoodAdapater = new ListFoodAdapater(CategoryDetailActivity.this, postPricelArrayList,email);
                popularRecyclerView.setAdapter(ListFoodAdapater);
            }
        });
        btn_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<FoodModel> postPricelArrayList = new ArrayList<>();

                for (FoodModel post: gets) {
                    postPricelArrayList.add(post);
                }
                Collections.sort(postPricelArrayList, new Comparator<FoodModel>() {
                    @Override
                    public int compare(FoodModel o1, FoodModel o2) {
                        return o2.get_Price() - o1.get_Price();
                    }
                });
                ListFoodAdapater = new ListFoodAdapater(CategoryDetailActivity.this, postPricelArrayList,email);
                popularRecyclerView.setAdapter(ListFoodAdapater);
            }

        });

        btn_fvr_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<FoodModel> postfvrlArrayList = new ArrayList<>();

                for (FoodModel post: gets) {
                    postfvrlArrayList.add(post);
                }
                Collections.sort(postfvrlArrayList, new Comparator<FoodModel>() {
                    @Override
                    public int compare(FoodModel o1, FoodModel o2) {
                        return Integer.valueOf(o1.get_Favorite()) - Integer.valueOf(o2.get_Favorite());
                    }
                });
                ListFoodAdapater = new ListFoodAdapater(CategoryDetailActivity.this, postfvrlArrayList,email);
                popularRecyclerView.setAdapter(ListFoodAdapater);
            }

        });
        btn_fvr_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<FoodModel> postfvrlArrayList = new ArrayList<>();

                for (FoodModel post: gets) {
                    postfvrlArrayList.add(post);
                }
                Collections.sort(postfvrlArrayList, new Comparator<FoodModel>() {
                    @Override
                    public int compare(FoodModel o1, FoodModel o2) {
                        return Integer.valueOf(o2.get_Favorite()) - Integer.valueOf(o1.get_Favorite());
                    }
                });
                ListFoodAdapater = new ListFoodAdapater(CategoryDetailActivity.this, postfvrlArrayList,email);
                popularRecyclerView.setAdapter(ListFoodAdapater);
            }

        });
    }
    //Ánh xạ view
    private void init()
    {
        imageView = findViewById(R.id.category_image2);
        itemName = findViewById(R.id.NameCategory);
        BackHome = findViewById(R.id.back_home);
        popularRecyclerView = findViewById(R.id.list_food_category);
        btn_up = findViewById(R.id.btn_up_price);
        btn_down =findViewById(R.id.btn_down_price);
        btn_fvr_up =findViewById(R.id.btn_up_fvr);
        btn_fvr_down =findViewById(R.id.btn_down_fvr);
    }

    private void  getPopularData(List<FoodModel> popularList){
        ListFoodAdapater = new ListFoodAdapater(CategoryDetailActivity.this, popularList,email);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CategoryDetailActivity.this, LinearLayoutManager.VERTICAL, false);
        popularRecyclerView.setLayoutManager(layoutManager);
        popularRecyclerView.setAdapter(ListFoodAdapater);

    }

}
