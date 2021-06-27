package com.example.onlyfood.Adapater;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.onlyfood.Activity.DetailFoodActivity;
import com.example.onlyfood.R;
import com.example.onlyfood.model.CartModel;
import com.example.onlyfood.model.FavoriteModel;
import com.example.onlyfood.model.FoodModel;
import com.example.onlyfood.networking.ApiServices;
import com.example.onlyfood.networking.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ListFoodAdapater extends RecyclerView.Adapter<ListFoodAdapater.ListFoodViewHolder>{

    private Context context;
    private List<FoodModel> popularList;
    private  String email,Id_Product;

    public ListFoodAdapater(Context context, List<FoodModel> popularList,String email) {
        this.context = context;
        this.popularList = popularList;
        this.email=email;
    }

    /* getItemCount() : cho biết số phần tử của dữ liệu
     onCreateViewHolder : tạo ra đối tượng ViewHolder, trong nó chứa View hiện thị dữ liệu
     onBindViewHolder : chuyển dữ liệu phần tử vào ViewHolder*/
    @NonNull
    @Override
    public ListFoodAdapater.ListFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.popular_recycler, parent, false);

        // here we need to create a layout for recyclerview cell items.
        return new ListFoodAdapater.ListFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListFoodAdapater.ListFoodViewHolder holder, int position) {

        FoodModel hero = popularList.get(position);

        Glide.with(context).load(context.getResources().
                getIdentifier(popularList.get(position).
                        get_Image(), "drawable", context.getPackageName())). //Lay anh ra tu resource
                into(holder.popularImage);
        //Glide.with(context).load(popularList.get(position).get_Image()).into(holder.popularImage);
        holder.popularName.setText(hero.get_NameProduct());
        holder.popularPrice.setText(String.valueOf(hero.get_Price()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Id_Product =hero.get_ProductID();
                Intent i = new Intent(context, DetailFoodActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("ID_Product",hero.get_ProductID());
                bundle.putString("Name_Product",hero.get_NameProduct());
                bundle.putString("Price",String.valueOf(hero.get_Price()));
                bundle.putString("Info",hero.get_Info());
                bundle.putString("Image",hero.get_Image());
                bundle.putString("Sold",hero.get_Sold());
                bundle.putString("username",email);

                i.putExtras(bundle);
                context.startActivity(i);

            }
        });
    }
    @Override
    public int getItemCount() {
        return  popularList.size();
    }

    public  static class ListFoodViewHolder extends RecyclerView.ViewHolder{
        ImageView popularImage;
        TextView popularName,popularPrice;

        public ListFoodViewHolder(@NonNull View itemView) {
            super(itemView);
            popularPrice = itemView.findViewById(R.id.cart_totalprice);
            popularName = itemView.findViewById(R.id.cart_name);
            popularImage = itemView.findViewById(R.id.cart_image);
        }
    }


}
