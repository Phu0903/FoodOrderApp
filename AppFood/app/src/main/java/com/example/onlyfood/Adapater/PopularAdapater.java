package com.example.onlyfood.Adapater;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.onlyfood.Activity.DetailFoodActivity;
import com.example.onlyfood.R;
import com.example.onlyfood.model.FoodModel;

import java.util.List;

public class PopularAdapater extends RecyclerView.Adapter<PopularAdapater.PopularViewHolder>{

    private Context context;
    private List<FoodModel> popularList;
    private  String email;

    public PopularAdapater(Context context, List<FoodModel> popularList,String email) {
        this.context = context;
        this.popularList = popularList;
        this.email = email;
    }

   /* getItemCount() : cho biết số phần tử của dữ liệu
    onCreateViewHolder : tạo ra đối tượng ViewHolder, trong nó chứa View hiện thị dữ liệu
    onBindViewHolder : chuyển dữ liệu phần tử vào ViewHolder*/
    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.popular_recycler, parent, false);
        // here we need to create a layout for recyclerview cell items.
        return new PopularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, int position) {
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
                Intent i = new Intent(context, DetailFoodActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("ID_Product",hero.get_ProductID());
                bundle.putString("Name_Product",hero.get_NameProduct());
                bundle.putString("Price",String.valueOf(hero.get_Price()));
                bundle.putString("Info",hero.get_Info());
                bundle.putString("Image",hero.get_Image());
                bundle.putString("Sold",hero.get_Sold());
                bundle.putString("username",email);
                bundle.putString("ListFood","1");
                i.putExtras(bundle);
                context.startActivity(i);

            }
        });

       

    }

    @Override
    public int getItemCount() {
        return  popularList.size();
    }

    public  static class PopularViewHolder extends RecyclerView.ViewHolder{
        ImageView popularImage;
        TextView popularName,popularPrice;

        public PopularViewHolder(@NonNull View itemView) {
            super(itemView);
            popularPrice = itemView.findViewById(R.id.cart_totalprice);
            popularName = itemView.findViewById(R.id.cart_name);
            popularImage = itemView.findViewById(R.id.cart_image);


        }
    }
}
