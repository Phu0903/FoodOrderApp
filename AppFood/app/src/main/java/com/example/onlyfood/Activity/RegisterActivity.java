package com.example.onlyfood.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.onlyfood.R;
import com.example.onlyfood.model.LoginRegisterModel;
import com.example.onlyfood.networking.ApiServices;
import com.example.onlyfood.networking.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {
    EditText signup_name,signup_password;
    EditText signup_phonenumber,signup_address;
    EditText signup_email;
    TextView login;
    Button add_account;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        Retrofit retrofit = RetrofitClient.getRetrofitInstance();
        ApiServices jsonPlaceHolderApi = retrofit.create(ApiServices.class);
        init();

        add_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register(jsonPlaceHolderApi,
                        String.valueOf(signup_email.getText()),
                        String.valueOf(signup_password.getText()),
                        String.valueOf(signup_name.getText()),
                        String.valueOf(signup_phonenumber.getText()),
                        String.valueOf(signup_address.getText()));
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
    //Ánh xạ
    public void init()
    {
        signup_name = findViewById(R.id.signup_name);
        signup_password= findViewById(R.id.signup_password);
        signup_address = findViewById(R.id.signup_address);
        signup_email = findViewById(R.id.signup_email);
        signup_phonenumber=findViewById(R.id.signup_phonenumber);
        add_account = findViewById(R.id.add_account);
        login = findViewById(R.id.loginlayout);
    }
    private  void register(ApiServices jsonPlaceHolderApi, String username, String password,String name, String phonenumber, String address)
    {
        LoginRegisterModel register = new LoginRegisterModel(username,password,name,phonenumber,address);
        Call<LoginRegisterModel> signup = jsonPlaceHolderApi.Register(register);
        signup.enqueue(new Callback<LoginRegisterModel>() {
            @Override
            public void onResponse(Call<LoginRegisterModel> call, Response<LoginRegisterModel> response) {
                if(response.isSuccessful()){

                    if(response.body().getMessage().equals("Register success"))
                    {

                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();

                    }
                }else{
                    Log.d("Sai: ",response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<LoginRegisterModel> call, Throwable t) {
                Log.d("Sai","Sai");
            }
        });
    }

}

