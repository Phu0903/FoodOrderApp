package com.example.onlyfood.Activity;



import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.onlyfood.R;
import com.example.onlyfood.model.LoginRegisterModel;
import com.example.onlyfood.networking.ApiServices;
import com.example.onlyfood.networking.RetrofitClient;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button Login;
    TextView Register;
    CheckBox checkbox;
    Retrofit retrofit = RetrofitClient.getRetrofitInstance();
    ApiServices jsonPlaceHolderApi = retrofit.create(ApiServices.class);
    SharedPreferences sharedPreferences;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Init();
        ClickRegister();

        ClickLogin();
    }

    //Ánh xạ
    public void Init()
    {
        sharedPreferences = getSharedPreferences("datalogin",MODE_PRIVATE);
        Login = findViewById(R.id.signinBtn);
        Register= findViewById(R.id.registerlayout);
        username = findViewById(R.id.signin_id);
        password = findViewById(R.id.signin_password);
        checkbox = findViewById(R.id.checkbox);
        username.setText(sharedPreferences.getString("username",""));
        password.setText(sharedPreferences.getString("password",""));
        checkbox.setChecked(sharedPreferences.getBoolean("checked",false));

    };
    private void ClickLogin()
    {
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Just Only Click
                Login(jsonPlaceHolderApi, String.valueOf(username.getText()), String.valueOf(password.getText()));
            }
        });
    }
    private void ClickRegister()
    {
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Just Only Click
                Register.setEnabled(false);
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();

            }
        });
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
                        intent.putExtras(bundle);
                        startActivity(intent);
                        //Neu co check
                        if(checkbox.isChecked())
                        {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("username",username);
                            editor.putString("password",password);
                            editor.putBoolean("checked",true);
                            editor.commit();
                        }
                        else{
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.remove("username");
                            editor.remove("password");
                            editor.remove("checked");
                            editor.commit();

                        }
                        finish();

                    }
                    else{
                        CharSequence notification =  new Gson().toJson(response.body().getMessage());
                        Toast.makeText(LoginActivity.this,notification,Toast.LENGTH_LONG).show();
                    }
                }else {
                    CharSequence notification =  new Gson().toJson(response.code());
                    Toast.makeText(LoginActivity.this,notification,Toast.LENGTH_LONG).show();
                    Log.d("Sai: " , new Gson().toJson(response.body()) );
                }
            }

            @Override
            public void onFailure(Call<LoginRegisterModel> call, Throwable t) {
                Log.d("Sai",t.getMessage());
            }
        });
    }

}
