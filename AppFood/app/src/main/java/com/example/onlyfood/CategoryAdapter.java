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
import com.example.onlyfood.model.Category;
import com.example.onlyfood.model.FoodData;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<PopularAdapter.PopularViewHolder>{

    private Context context;
    private List<Category> categoryList;

    public CategoryAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    /* getItemCount() : cho biết số phần tử của dữ liệu
     onCreateViewHolder : tạo ra đối tượng ViewHolder, trong nó chứa View hiện thị dữ liệu
     onBindViewHolder : chuyển dữ liệu phần tử vào ViewHolder*/
    @NonNull
    @Override
    public PopularAdapter.PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.popular_recycler_items, parent, false);
        // here we need to create a layout for recyclerview cell items.
        return new PopularAdapter.PopularViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.PopularViewHolder holder, int position) {
        Category hero = categoryList.get(position);
        //lay anh tu drawble url luu o git
        Glide.with(context).load(context.getResources().
                getIdentifier(categoryList.get(position).
                        get_ImageCategory(), "drawable", context.getPackageName())).
                into(holder.popularImage);
        holder.popularName.setText(hero.get_NameCategory());


        /*holder.popularName.setText(popularList.get(position).get_NameProduct());*/
    }

    @Override
    public int getItemCount() {
        return  categoryList.size();
    }
    public  static class PopularViewHolder extends RecyclerView.ViewHolder{
        ImageView popularImage;
        TextView popularName;
        public PopularViewHolder(@NonNull View itemView) {
            super(itemView);
            popularName = itemView.findViewById(R.id.all_menu_name);
            popularImage = itemView.findViewById(R.id.all_menu_image);

        }
    }
}
