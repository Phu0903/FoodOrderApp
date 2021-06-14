package com.example.onlyfood.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlyfood.Adapater.CategoryAdapater;
import com.example.onlyfood.Adapater.PopularAdapater;
import com.example.onlyfood.R;
import com.example.onlyfood.model.CategoryModel;
import com.example.onlyfood.model.FoodModel;
import com.example.onlyfood.model.UserModel;
import com.example.onlyfood.networking.ApiServices;
import com.example.onlyfood.networking.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeActivity extends Fragment {
    private TextView textViewTerm,fullname;
    String email;
    RecyclerView popularRecyclerView, categoryRecyclerView; // RecyclerView
    ApiServices apiInterface; //Call ApiServices
    Context context;
    TextView search;
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
        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            email= bundle.getString("username");
        }
        else {
            Log.d("null","null");
        }
        callApiUser(jsonPlaceHolderApi,email);


        //Find Id Text
        fullname = mView.findViewById(R.id.fullname);
        textViewTerm =mView.findViewById(R.id.textView2);
        popularRecyclerView = mView.findViewById(R.id.cart_recycler);
        categoryRecyclerView = mView.findViewById(R.id.category_recycler); //Tim category recycler view
        search = mView.findViewById(R.id.search);
        ClickSearch();
        return mView;

    }
    private void ClickSearch()
    {
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("search_email",email);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }
    private void callApiUser(ApiServices jsonPlaceHolderApi,String email)
    {
        Call<UserModel> call = jsonPlaceHolderApi.getInforUser(email);

        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (!response.isSuccessful()) {
                    Log.d("null",null);
                    return;
                }
                else{
                    fullname.setText(response.body().get_name());


                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });
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

        categoryAdapter = new CategoryAdapater(context, categoryListList,email); //
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
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
      popularAdapter = new PopularAdapater(context, popularList,email);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        popularRecyclerView.setLayoutManager(layoutManager);
        popularRecyclerView.setAdapter(popularAdapter);

    }

}




