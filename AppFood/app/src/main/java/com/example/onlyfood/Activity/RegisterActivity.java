package com.example.onlyfood.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.onlyfood.R;
import com.example.onlyfood.model.CheckUser;
import com.example.onlyfood.model.LoginRegisterModel;
import com.example.onlyfood.networking.ApiServices;
import com.example.onlyfood.networking.RetrofitClient;
import com.google.gson.Gson;

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
    Retrofit retrofit = RetrofitClient.getRetrofitInstance();
    ApiServices jsonPlaceHolderApi = retrofit.create(ApiServices.class);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        init();
        ClickLogin();
        ClickBtnRegister();




    }
    public void ClickBtnRegister()
    {
        add_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = signup_phonenumber.getText().toString();
                String email = signup_email.getText().toString();
                checkUser(jsonPlaceHolderApi,email,mobile);


            }
        });
    }
    public void ClickLogin()
    {

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
    private  void checkUser(ApiServices jsonPlaceHolderApi, String username, String phonenumber)
    {
        CheckUser checkuser = new CheckUser(username,phonenumber);
        Call<CheckUser> signup = jsonPlaceHolderApi.CheckUser(checkuser);
        signup.enqueue(new Callback<CheckUser>() {
            @Override
            public void onResponse(Call<CheckUser> call, Response<CheckUser> response) {
                if(response.isSuccessful()){

                    if(response.body().getMessage().equals("U can register"))
                    {

                        Intent i = new Intent(RegisterActivity.this,Verification_Activity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("email",signup_email.getText().toString());
                        bundle.putString("password",signup_password.getText().toString());
                        bundle.putString("name",signup_name.getText().toString());
                        bundle.putString("phone",signup_phonenumber.getText().toString());
                        bundle.putString("address",signup_address.getText().toString());
                        i.putExtras(bundle);
                        startActivity(i);


                    }
                    else {
                        CharSequence notification =  new Gson().toJson(response.body().getMessage());
                        Toast.makeText(RegisterActivity.this,notification,Toast.LENGTH_LONG).show();
                    }
                }else{
                    CharSequence notification =  new Gson().toJson(response.code());
                    Toast.makeText(RegisterActivity.this,notification,Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<CheckUser> call, Throwable t) {
                Log.d("Sai","Sai");
            }
        });
    }

}



