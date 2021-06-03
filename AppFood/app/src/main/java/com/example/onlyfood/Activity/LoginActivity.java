package com.example.onlyfood.Activity;



import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.onlyfood.R;
import com.example.onlyfood.model.CartModel;
import com.example.onlyfood.model.LoginRegisterModel;
import com.example.onlyfood.networking.ApiServices;
import com.example.onlyfood.networking.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button Login;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Retrofit retrofit = RetrofitClient.getRetrofitInstance();
        ApiServices jsonPlaceHolderApi = retrofit.create(ApiServices.class);
        Login = findViewById(R.id.signinBtn);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View();
                Login(jsonPlaceHolderApi, String.valueOf(username.getText()), String.valueOf(password.getText()));


            }
        });

    }

    //Ánh xạ
    private void View()
    {
        username = findViewById(R.id.signin_id);
        password = findViewById(R.id.signin_password);
    }
    private void Login(ApiServices jsonPlaceHolderApi, String username, String password){

        LoginRegisterModel login = new LoginRegisterModel(username,password);

        Call<LoginRegisterModel> Login = jsonPlaceHolderApi.Login(login);
        Login.enqueue(new Callback<LoginRegisterModel>() {
            @Override
            public void onResponse(Call<LoginRegisterModel> call, Response<LoginRegisterModel> response) {
                if(response.isSuccessful()){

                    if(response.body().getMessage().equals("Login success"))
                    {

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("username",username);
                        bundle.putString("password",password);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();

                    }
                }else{
                    Log.d("Sai: ",String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<LoginRegisterModel> call, Throwable t) {
                Log.d("Sai","Sai");
            }
        });
    }

}
