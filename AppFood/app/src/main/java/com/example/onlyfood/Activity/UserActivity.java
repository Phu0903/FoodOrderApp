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
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlyfood.Adapater.CategoryAdapater;
import com.example.onlyfood.Adapater.PopularAdapater;
import com.example.onlyfood.R;
import com.example.onlyfood.model.CartModel;
import com.example.onlyfood.model.CategoryModel;
import com.example.onlyfood.model.FoodModel;
import com.example.onlyfood.model.UserModel;
import com.example.onlyfood.networking.ApiServices;
import com.example.onlyfood.networking.RetrofitClient;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserActivity extends Fragment {
    private TextView textViewTerm;

    RecyclerView popularRecyclerView, categoryRecyclerView; // RecyclerView
    ApiServices apiInterface; //Call ApiServices
    Context context;
    Button signout;
    String email;
    TextView fullname,usermail,phonenumber,address;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.userr, null);
        signout = mView.findViewById(R.id.signout);
        fullname = mView.findViewById(R.id.user_name);
        usermail = mView.findViewById(R.id.user_email);
        phonenumber = mView.findViewById(R.id.user_phone);
        address =mView.findViewById(R.id.user_address);
        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            email= bundle.getString("username");
        }
        else {
            Log.d("null","null");
        }

        //Using retrofit
        Retrofit retrofit = RetrofitClient.getRetrofitInstance();
        ApiServices jsonPlaceHolderApi = retrofit.create(ApiServices.class);
        callApiUser(jsonPlaceHolderApi);
        //Call function
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
          }
        });

        context = container.getContext();
        return mView;

    }

    private void callApiUser(ApiServices jsonPlaceHolderApi)
    {
        Call<UserModel> call = jsonPlaceHolderApi.getInforUser(email);

        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (!response.isSuccessful()) {
                    Log.d("null",null);
                    return;
                }
                else{
                    fullname.setText(response.body().get_name());
                    usermail.setText(response.body().get_email());
                    phonenumber.setText(response.body().get_PhoneNumber());
                    address.setText(response.body().get_Address());



                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });
    }



}




