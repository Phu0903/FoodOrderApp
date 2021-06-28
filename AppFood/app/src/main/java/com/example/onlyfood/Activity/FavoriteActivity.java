package com.example.onlyfood.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlyfood.Adapater.ListFoodAdapater;
import com.example.onlyfood.R;
import com.example.onlyfood.model.FoodModel;
import com.example.onlyfood.networking.ApiServices;
import com.example.onlyfood.networking.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FavoriteActivity extends Fragment {
    RecyclerView foodRecyclerView;
    ListFoodAdapater ListFoodAdapater;
    EditText editText;
    List<FoodModel> gets;
    String email;
    Retrofit retrofit = RetrofitClient.getRetrofitInstance();
    ApiServices jsonPlaceHolderApi = retrofit.create(ApiServices.class);
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.favorite_layout, null);
        foodRecyclerView =  mView.findViewById(R.id.list_favorite);
        editText = (EditText) mView.findViewById(R.id.edsearch2);

        DataFromAnotherActivity();
        callFavotiteFood(jsonPlaceHolderApi);
        initSearchWidgets();

        return mView;

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
        callFavotiteFood(jsonPlaceHolderApi);

    }
    private void DataFromAnotherActivity()
    {

        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            email= bundle.getString("username");
        }
        else {
            Log.d("null","null");
        }
    }
    private void callFavotiteFood(ApiServices jsonPlaceHolderApi)
    {
        Call<List<FoodModel>> call = jsonPlaceHolderApi.getFavoriteFood(email);
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
                ListFoodAdapater = new ListFoodAdapater(getContext(), postNewsModelArrayList,email);
                foodRecyclerView.setAdapter(ListFoodAdapater);

            }
        });
    }
    private void  getProductData(List<FoodModel> popularList){
        ListFoodAdapater = new ListFoodAdapater(getContext(), popularList,email);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        foodRecyclerView.setLayoutManager(layoutManager);
        foodRecyclerView.setAdapter(ListFoodAdapater);
    }
}

