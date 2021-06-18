package com.example.onlyfood.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.onlyfood.R;
import com.example.onlyfood.model.LoginRegisterModel;
import com.example.onlyfood.networking.ApiServices;
import com.example.onlyfood.networking.RetrofitClient;
import com.goodiebag.pinview.Pinview;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Verification_Activity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    // [END declare_auth]
    Retrofit retrofit = RetrofitClient.getRetrofitInstance();
    ApiServices jsonPlaceHolderApi = retrofit.create(ApiServices.class);
    String mverificationId;
    String verificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    Pinview pinView;
    String email,password,name,phone,address;
    Button btn_Click;
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verifyotp);
        mAuth = FirebaseAuth.getInstance();
        DataFromRegister();
        pinView =  findViewById(R.id.pin_view);

        sendVerificationCode(phone);
        btn_Click = findViewById(R.id.btn_ClickOTP);
        btn_Click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_Click.setEnabled(false);
                InputCode();
            }
        });

    }

    private void InputCode()
    {
        verifyPhoneNumberWithCode(pinView.getValue());
    }
    private void DataFromRegister()
    {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            email= bundle.getString("email");
            password = bundle.getString("password");
            name = bundle.getString("name");
            phone = bundle.getString("phone");
            Log.d("ádsa",phone);
            address= bundle.getString("address");

        }
        else {
            Log.d("null","null");
        }
    }



    private void verifyPhoneNumberWithCode(String code) {
        // [START verify_with_code]
        try {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
            signInWithPhoneAuthCredential(credential);
        }
        catch (Exception e){
            Toast toast = Toast.makeText(this, "Verification Code is wrong", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }
        // [END verify_with_code]
    }


//Hàm gửi mã xác thực
 private void sendVerificationCode(String phone) {
     PhoneAuthOptions options =
             PhoneAuthOptions.newBuilder(mAuth)
                     .setPhoneNumber("+84"+ phone)       // Phone number to verify
                     .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                     .setActivity(this)                 // Activity (for callback binding)
                     .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                     .build();
     PhoneAuthProvider.verifyPhoneNumber(options);
 }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            register(jsonPlaceHolderApi, email, password, name,
                                    phone,
                                    address);
                            Intent intent = new Intent(Verification_Activity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // Sign in failed, display a message and update the UI;
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(Verification_Activity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }



    private  PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        //Hàm này được gọi khi mã code đã được gửi thành công.
        public void onCodeSent(@NonNull String mverificationId,
                               @NonNull PhoneAuthProvider.ForceResendingToken token) {
            verificationId = mverificationId;
            mResendToken = token;
        }
        @Override
        // Hàm này được gọi khi bạn xác thực thành công. Tức là mã code nhập vào đúng với mã đã gửi.
        public void onVerificationCompleted(PhoneAuthCredential credential) {
            signInWithPhoneAuthCredential(credential);
        }

        @Override
        //Hàm này được gọi khi không gửi được mã code. Chúng ta cần thông báo cho người dùng biết. Ở ví dụ này mình đơn giản là hiển thị một Toast thông báo.
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(Verification_Activity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            Log.d("Lỗi",e.getMessage());

        }

    };


    private  void register(ApiServices jsonPlaceHolderApi, String username, String password, String name, String phonenumber, String address)
    {
        LoginRegisterModel register = new LoginRegisterModel(username,password,name,phonenumber,address);
        Call<LoginRegisterModel> signup = jsonPlaceHolderApi.Register(register);
        signup.enqueue(new Callback<LoginRegisterModel>() {
            @Override
            public void onResponse(Call<LoginRegisterModel> call, Response<LoginRegisterModel> response) {
                if(response.isSuccessful()){

                    if(response.body().getMessage().equals("Register success"))
                    {

                        Intent intent = new Intent(Verification_Activity.this, MainActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("username",email);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();


                    }
                    else {
                        CharSequence notification =  new Gson().toJson(response.body().getMessage());
                        Toast.makeText(Verification_Activity.this,notification,Toast.LENGTH_LONG).show();
                    }
                }else{
                    CharSequence notification =  new Gson().toJson(response.code());
                    Toast.makeText(Verification_Activity.this,notification,Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<LoginRegisterModel> call, Throwable t) {
                Log.d("Sai","Sai");
            }
        });
    }

}
