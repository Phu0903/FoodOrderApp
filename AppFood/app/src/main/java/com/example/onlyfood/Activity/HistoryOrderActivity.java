package com.example.onlyfood.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.onlyfood.Adapater.ListHistoryAdapater;
import com.example.onlyfood.R;
import com.example.onlyfood.model.OrderModel;
import com.example.onlyfood.networking.ApiServices;
import com.example.onlyfood.networking.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HistoryOrderActivity extends AppCompatActivity {
    Button Back;
    String email;
    RecyclerView listHistoryRecyclerView;
    ListHistoryAdapater ListHistoryAdapater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_oder);
        //Data from user activity
        IntentFrom();
        //
        Retrofit retrofit = RetrofitClient.getRetrofitInstance();
        ApiServices jsonPlaceHolderApi = retrofit.create(ApiServices.class);
        //
        init();
        //Call Api history purchase
        CallHistory(jsonPlaceHolderApi);
        //click back home
        BackUser();


    }

    private void init()
    {
        listHistoryRecyclerView = findViewById(R.id.list_oder_history);
        Back = findViewById(R.id.Back_User);



    }

    private void BackUser()
    {
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void IntentFrom()
    {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            email= bundle.getString("email_to_history");
        }
        else {
            Log.d("null","null");
        }
    }
    private void CallHistory(ApiServices jsonPlaceHolderApi)
    {

        Call<List<OrderModel>> call = jsonPlaceHolderApi.getOder(email);

        call.enqueue(new Callback<List<OrderModel>>() {
            @Override
            public void onResponse(Call<List<OrderModel>> call, Response<List<OrderModel>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                List<OrderModel> gets = response.body();
                getHistoryData(gets);

            }
            @Override
            public void onFailure(Call<List<OrderModel>> call, Throwable t) {

            }
        });
    }

    private void  getHistoryData(List<OrderModel> historyList){
        ListHistoryAdapater = new ListHistoryAdapater(HistoryOrderActivity.this, historyList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HistoryOrderActivity.this, LinearLayoutManager.VERTICAL, false);
        listHistoryRecyclerView.setLayoutManager(layoutManager);
        listHistoryRecyclerView.setAdapter(ListHistoryAdapater);
    }

}