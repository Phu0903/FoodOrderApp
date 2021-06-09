package com.example.onlyfood.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlyfood.Adapater.ListCartAdapater;
import com.example.onlyfood.R;
import com.example.onlyfood.model.CartModel;
import com.example.onlyfood.networking.ApiServices;
import com.example.onlyfood.networking.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CartActivity extends Fragment {
    private TextView textViewTerm;
    String email;
    RecyclerView popularRecyclerView, categoryRecyclerView; // RecyclerView
    ApiServices apiInterface; //Call ApiServices
    Context context;
    Integer total = 0;
    TextView cart_price,quantity;
    Button Delete;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.cart_layout, null);
        //Using retrofit
        Retrofit retrofit = RetrofitClient.getRetrofitInstance();
        ApiServices jsonPlaceHolderApi = retrofit.create(ApiServices.class);
        //Call function
        context = container.getContext();
        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            email= bundle.getString("username");
        }
        else {
            Log.d("null","null");
        }
        CallListFoodPopular(jsonPlaceHolderApi,email);
        //
        LocalBroadcastManager.getInstance(context).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-message"));

        //Find Id Text
        textViewTerm =mView.findViewById(R.id.textView2);
        popularRecyclerView = mView.findViewById(R.id.cart_recycler);
        cart_price = mView.findViewById(R.id.cart_totalprice);
        quantity = mView.findViewById(R.id.amount);


        return mView;

    }


    //Call list data
    private void CallListFoodPopular(ApiServices jsonPlaceHolderApi,String email)
    {

        Call<List<CartModel>> call = jsonPlaceHolderApi.getCart(email);

        call.enqueue(new Callback<List<CartModel>>() {
            @Override
            public void onResponse(Call<List<CartModel>> call, Response<List<CartModel>> response) {
                if (!response.isSuccessful()) {
                    textViewTerm.setText("Code: " + response.body());
                    return;
                }

                List<CartModel> gets = response.body();
                for(CartModel get : gets)
                {
                    total = Integer.valueOf(get.get_Price())*Integer.valueOf(get.get_quantity()) + total;

                }
                cart_price.setText(total.toString());

                getPopularData(gets);
            }
            @Override
            public void onFailure(Call<List<CartModel>> call, Throwable t) {
                textViewTerm.setText(t.getMessage());
            }
        });
    }
    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String qty = intent.getStringExtra("Price");
            Integer total_temp;
            Log.d("Total",total.toString());
            total =  total - Integer.valueOf(qty);
            cart_price.setText(total.toString());
        }
    };
    ListCartAdapater ListCart;
    private void  getPopularData(List<CartModel> popularList){
        ListCart = new ListCartAdapater(context, popularList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        popularRecyclerView.setLayoutManager(layoutManager);
        popularRecyclerView.setAdapter(ListCart);




    }

}