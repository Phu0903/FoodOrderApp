package com.example.onlyfood.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlyfood.R;
import com.example.onlyfood.model.UserModel;
import com.example.onlyfood.networking.ApiServices;
import com.example.onlyfood.networking.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserActivity extends Fragment {
    Context context;
    Button signout,pucharse_history;
    String email;
    TextView fullname,usermail,phonenumber,address;
    Retrofit retrofit = RetrofitClient.getRetrofitInstance();
    ApiServices jsonPlaceHolderApi = retrofit.create(ApiServices.class);
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.userr, null);

        signout = mView.findViewById(R.id.signout);
        fullname = mView.findViewById(R.id.user_name);
        usermail = mView.findViewById(R.id.user_email);
        phonenumber = mView.findViewById(R.id.user_phone);
        address =mView.findViewById(R.id.user_address);
        pucharse_history = mView.findViewById(R.id.purchase_history);


        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            email= bundle.getString("username");
        }
        else {
            Log.d("null","null");
        }

        //Using retrofit
        //click
        ClickSignOut();
        ClickHistory();
        callApiUser(jsonPlaceHolderApi);
        //Call function
        //
        context = container.getContext();
        return mView;

    }
    private void ClickHistory()
    {
        pucharse_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartHistory();
            }
        });
    }
    private void ClickSignOut()
    {
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }
    private void callApiUser(ApiServices jsonPlaceHolderApi)
    {
        Call<UserModel> call = jsonPlaceHolderApi.getInforUser(email);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                else{
                    fullname.setText(response.body().get_name());
                    usermail.setText(response.body().get_email());
                    phonenumber.setText("0"+response.body().get_PhoneNumber());
                    address.setText(response.body().get_Address());
                }
            }
            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });
    }
    private void StartHistory()
    {
        Intent intent = new Intent(getActivity(), HistoryOrderActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("email_to_history",email);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}




