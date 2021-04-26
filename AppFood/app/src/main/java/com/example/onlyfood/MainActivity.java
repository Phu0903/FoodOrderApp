package com.example.onlyfood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlyfood.api.ApiServices;
import com.example.onlyfood.api.RetrofitClient;
import com.example.onlyfood.model.Currency;
import com.example.onlyfood.model.FoodData;
import com.example.onlyfood.model.ProductFood;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView textViewTerm;
    private Button btn_call;
    ApiServices apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewTerm = findViewById(R.id.textViewTemr);
        btn_call = findViewById(R.id.button);
        apiInterface = RetrofitClient.getRetrofitInstance().create(ApiServices.class);
        Call<List<FoodData>> call = apiInterface.getFood();
        call.enqueue(new Callback<List<FoodData>>() {
            @Override
            public void onResponse(Call<List<FoodData>> call, Response<List<FoodData>> response) {

                List<FoodData> foodDataList = response.body();




                // lets run it.
                // we have fetched data from server.
                // now we have to show data in app using recycler view
                // lets make recycler view adapter
                // we have setup and bind popular section
                // in a same way we add recommended and all menu items
                // we add two adapter class for allmenu and recommended items.
                // so lets do it fast.

            }

            @Override
            public void onFailure(Call<List<FoodData>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Server is not responding.", Toast.LENGTH_SHORT).show();
            }
        });
        /*btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            /*public void onClick(View v) {
                clickCallApi();
            }
        });*/
    }
    //http://apilayer.net/api/live?access_key=843d4d34ae72b3882e3db642c51e28e6&currencies=VND&source=USD&format=1


   /*private void clickCallApi() {

   }
           apiInterface.getFood().enqueue(new Callback<Currency>() {
            @Override
            public void onResponse(Call<Currency> call, Response<Currency> response) {
                Currency currency = response.body();
                if(currency != null && currency.isSuccess())
                {
                    textViewTerm.setText(currency.getTerms());
                }
            }

            @Override
            public void onFailure(Call<Currency> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Call api Error",Toast.LENGTH_LONG).show();
            }
        })
*/
   
}


