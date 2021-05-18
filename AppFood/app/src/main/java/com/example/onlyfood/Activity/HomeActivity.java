package com.example.onlyfood.Activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlyfood.Adapater.CategoryAdapater;
import com.example.onlyfood.Adapater.PopularAdapater;
import com.example.onlyfood.R;
import com.example.onlyfood.model.CategoryModel;
import com.example.onlyfood.model.FoodModel;
import com.example.onlyfood.networking.ApiServices;
import com.example.onlyfood.networking.RetrofitClient;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends Fragment {
    private TextView textViewTerm;

    RecyclerView popularRecyclerView, categoryRecyclerView; // RecyclerView
    ApiServices apiInterface; //Call ApiServices
    Context context;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.home_layout, null);
        //Using retrofit
        Retrofit retrofit = RetrofitClient.getRetrofitInstance();
        ApiServices jsonPlaceHolderApi = retrofit.create(ApiServices.class);
        //Call function
        CallListCategory(jsonPlaceHolderApi);
        CallListFoodPopular(jsonPlaceHolderApi);
        context = container.getContext();

        //Find Id Text
        textViewTerm =mView.findViewById(R.id.textView2);
        popularRecyclerView = mView.findViewById(R.id.popular_recycler);
        categoryRecyclerView = mView.findViewById(R.id.category_recycler); //Tim category recycler view
        return mView;

    }
    //Call list data
    private void CallListCategory(ApiServices jsonPlaceHolderApi)
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


        categoryAdapter = new CategoryAdapater(context, categoryListList); //
        // RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        // layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
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


        popularAdapter = new PopularAdapater(context, popularList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        popularRecyclerView.setLayoutManager(layoutManager);
        popularRecyclerView.setAdapter(popularAdapter);

    }



}




