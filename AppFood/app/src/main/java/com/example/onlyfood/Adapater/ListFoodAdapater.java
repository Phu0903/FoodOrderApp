package com.example.onlyfood.Adapater;

import android.content.Context;
import android.content.Intent;
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

public class ListFoodAdapater extends RecyclerView.Adapter<ListFoodAdapater.ListFoodViewHolder>{

    private Context context;
    private List<FoodModel> popularList;

    public ListFoodAdapater(Context context, List<FoodModel> popularList) {
        this.context = context;
        this.popularList = popularList;
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
        Glide.with(context).load(popularList.get(position).get_Image()).into(holder.popularImage);
        holder.popularName.setText(hero.get_NameProduct());
        holder.popularPrice.setText(String.valueOf(hero.get_Price()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailFoodActivity.class);
                i.putExtra("ID_Product",hero.get_ProductID());
                i.putExtra("Name_Product",hero.get_NameProduct());
                i.putExtra("Price",String.valueOf(hero.get_Price()));
                i.putExtra("Info",hero.get_Info());
                i.putExtra("Image",hero.get_Image());
                i.putExtra("Sold",hero.get_Sold());
                i.putExtra("ListFood","1");
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
            popularPrice = itemView.findViewById(R.id.price);
            popularName = itemView.findViewById(R.id.popular_name);
            popularImage = itemView.findViewById(R.id.popular_image);


        }
    }
}
