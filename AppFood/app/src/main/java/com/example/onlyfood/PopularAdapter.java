package com.example.onlyfood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.onlyfood.model.FoodData;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.PopularViewHolder>{

    private Context context;
    private List<FoodData> popularList;

    public PopularAdapter(Context context, List<FoodData> popularList) {
        this.context = context;
        this.popularList = popularList;
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
        FoodData hero = popularList.get(position);
        Glide.with(context).load(popularList.get(position).get_Image()).into(holder.popularImage);
        holder.popularName.setText(hero.get_NameProduct());
        holder.popularPrice.setText(String.valueOf(hero.get_Price()));

       

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
            popularPrice = itemView.findViewById(R.id.price);
            popularName = itemView.findViewById(R.id.popular_name);
            popularImage = itemView.findViewById(R.id.popular_image);


        }
    }
}
