package com.example.onlyfood.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.onlyfood.Adapater.ListChekOutAdapater;
import com.example.onlyfood.R;
import com.example.onlyfood.model.CartModel;
import com.example.onlyfood.model.OrderListModel;
import com.example.onlyfood.model.OrderModel;
import com.example.onlyfood.model.UserModel;
import com.example.onlyfood.networking.ApiServices;
import com.example.onlyfood.networking.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CheckOutActivity extends AppCompatActivity {
    RecyclerView ListTtemsRecycerview;
    String email,name,address,phone;
    TextView quantity_items,totaldetail,taxdetal,total_checkout;
    Button BackHome,BuyNow;
    ImageView changenamecheckoutbtn,savenamebtn,
              changePhonebtn,savePhonebtn,
              changeAddressbtn,saveAddressbtn;
    Integer itmes = 0,tax,total=0;
    TextView name_user,address_user,phone_user;
    EditText name_checkout2,phone_checkout2,address_checkout2;
    double price_checkout;
    //Using retrofit
    Retrofit retrofit = RetrofitClient.getRetrofitInstance();
    ApiServices jsonPlaceHolderApi = retrofit.create(ApiServices.class);
    List<OrderListModel> prodcut_food;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout_layout);
        //
        intentfrom();
        //
        init();
        //getvalue from  another activity
        CheckOutList(jsonPlaceHolderApi,email);
        callApiUser(jsonPlaceHolderApi,email);
        //Click Back Home
        ClickBackHome();
        //Change
        ChangeInfo();
        //Clik buy now
        ClickBuyNow();



    }
    private void init()
    {
        quantity_items = findViewById(R.id.quantity_items);
        ListTtemsRecycerview = findViewById(R.id.list_items);
        BackHome = findViewById(R.id.back_home3);
        totaldetail = findViewById(R.id.ID_Order);
        taxdetal = findViewById(R.id.Create_Day);
        total_checkout = findViewById(R.id.total_checkout);
        name_user = findViewById(R.id.name_checkout);
        address_user = findViewById(R.id.address_checkout);
        phone_user = findViewById(R.id.phonenumber_checkout);
        BuyNow = findViewById(R.id.buynow);
        //name
        changenamecheckoutbtn = findViewById(R.id.change_namecheckout);
        name_checkout2 = findViewById(R.id.name_checkout2);
        savenamebtn = findViewById(R.id.savename);
        //phone
        changePhonebtn = findViewById(R.id.change_phonecheckout);
        phone_checkout2 = findViewById(R.id.phone_checkout2);
        savePhonebtn = findViewById(R.id.savephone);
        //address
        changeAddressbtn = findViewById(R.id.change_addresscheckout);
        address_checkout2 = findViewById(R.id.address_checkout2);
        saveAddressbtn = findViewById(R.id.saveaddress);
    }
    //Intent from cart activity
    private void intentfrom()
    {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            email= bundle.getString("email-for-checkout");

        }
        else {
            Log.d("null","null");
        }
    }
    private void ChangeInfo(){
        changenamecheckoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changenamecheckoutbtn.setVisibility(View.GONE);
                name_user.setVisibility(View.GONE);
                name_checkout2.setVisibility(View.VISIBLE);
                savenamebtn.setVisibility(View.VISIBLE);

            }
        });
        savenamebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("name_change",name_checkout2.getText().toString());
                name_user.setText(name_checkout2.getText().toString());
                name = name_checkout2.getText().toString();
                changenamecheckoutbtn.setVisibility(View.VISIBLE);
                name_user.setVisibility(View.VISIBLE);
                name_checkout2.setVisibility(View.GONE);
                savenamebtn.setVisibility(View.GONE);
            }
        });

        //phone
        changePhonebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePhonebtn.setVisibility(View.GONE);
                phone_user.setVisibility(View.GONE);
                phone_checkout2.setVisibility(View.VISIBLE);
                savePhonebtn.setVisibility(View.VISIBLE);

            }
        });
        savePhonebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone_user.setText(phone_checkout2.getText().toString());
                phone = phone_checkout2.getText().toString();
                changePhonebtn.setVisibility(View.VISIBLE);
                phone_user.setVisibility(View.VISIBLE);
                phone_checkout2.setVisibility(View.GONE);
                savePhonebtn.setVisibility(View.GONE);
            }
        });

        //address
        //phone
        changeAddressbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeAddressbtn.setVisibility(View.GONE);
                address_user.setVisibility(View.GONE);
                address_checkout2.setVisibility(View.VISIBLE);
                saveAddressbtn.setVisibility(View.VISIBLE);

            }
        });
        saveAddressbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                address_user.setText(address_checkout2.getText().toString());
                address = address_checkout2.getText().toString();
                changeAddressbtn.setVisibility(View.VISIBLE);
                address_user.setVisibility(View.VISIBLE);
                address_checkout2.setVisibility(View.GONE);
                saveAddressbtn.setVisibility(View.GONE);
            }
        });
    }
    //Click Back Homw
    private void ClickBackHome()
    {
        BackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackHome.setEnabled(false);
                finish();
            }

        });
    }

    //Click btn buy now
    private void ClickBuyNow(){
        BuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuyNow.setEnabled(false);
                AddtoOrder(jsonPlaceHolderApi,email,name,total.toString(),address,phone,prodcut_food);
            }
        });
    }


    //Call list data cart
    private void CheckOutList(ApiServices jsonPlaceHolderApi,String email)
    {

        Call<List<CartModel>> call = jsonPlaceHolderApi.getCart(email);

        call.enqueue(new Callback<List<CartModel>>() {
            @Override
            public void onResponse(Call<List<CartModel>> call, Response<List<CartModel>> response) {
                if (!response.isSuccessful()) {
                    //textViewTerm.setText("Code: " + response.body());
                    return;
                }
                prodcut_food = new ArrayList<>();

                List<CartModel> gets = response.body();
                for(CartModel get:gets)
                {
                    OrderListModel foodModel = new OrderListModel(
                            get.get_NameProduct(),
                            get.get_Image(),
                            get.get_quantity(),
                            get.get_Price()
                    );
                    prodcut_food.add(foodModel);
                    itmes= Integer.valueOf(get.get_quantity()) + itmes;
                    total = Integer.valueOf(get.get_quantity())*Integer.valueOf(get.get_Price()) + total;

                }
                quantity_items.setText(itmes+" items");
                totaldetail.setText("$" +total);
                taxdetal.setText("10%");
                if(total == 0)
                {
                    total_checkout.setText(0);
                }
                else{
                    price_checkout = total*0.01+ total;
                    total_checkout.setText("$"+ String.valueOf(price_checkout));
                }

                getPopularData(gets);


            }
            @Override
            public void onFailure(Call<List<CartModel>> call, Throwable t) {
                //textViewTerm.setText(t.getMessage());
            }
        });
    }

    ListChekOutAdapater ListCart;
    private void  getPopularData(List<CartModel> popularList){
        ListCart = new ListChekOutAdapater(CheckOutActivity.this, popularList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CheckOutActivity.this, LinearLayoutManager.VERTICAL, false);
        ListTtemsRecycerview.setLayoutManager(layoutManager);
        ListTtemsRecycerview.setAdapter(ListCart);

    }


    //api inforuser
    private void callApiUser(ApiServices jsonPlaceHolderApi,String email)
    {
        Call<UserModel> call = jsonPlaceHolderApi.getInforUser(email);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                else{
                    name = response.body().get_name();
                    phone = response.body().get_PhoneNumber();
                    address = response.body().get_Address();
                    //name
                    name_user.setText(name);
                    name_checkout2.setText(name);
                    //phone
                    phone_user.setText("0"+phone);
                    phone_checkout2.setText("0"+phone);
                    //address
                    address_user.setText(address);
                    address_checkout2.setText(address);
                }
            }
            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });
    }


    private void AddtoOrder(ApiServices jsonPlaceHolderApi,String email,String name,String total,String address,String phonenumber,List<OrderListModel> product_food)
    {

        OrderModel ordermodel = new OrderModel(email,name,total,address,phonenumber,product_food);
        Call<OrderModel> call = jsonPlaceHolderApi.postOrder(ordermodel);
        call.enqueue(new Callback<OrderModel>() {
            @Override
            public void onResponse(Call<OrderModel> call, Response<OrderModel> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                else{
                    Intent intent = new Intent(CheckOutActivity.this,NotificationActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("notification",email);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
            }
            @Override
            public void onFailure(Call<OrderModel> call, Throwable t) {

            }
        });
    }

}