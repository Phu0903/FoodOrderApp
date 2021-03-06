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
import com.example.onlyfood.Activity.CategoryDetailActivity;
import com.example.onlyfood.R;
import com.example.onlyfood.model.CategoryModel;

import java.util.List;

public class CategoryAdapater extends RecyclerView.Adapter<CategoryAdapater.CategoryViewHolder>{

    private Context context; //Call context
    private List<CategoryModel> categoryList; //Data
    private String email;

    //constructor Category
    public CategoryAdapater(Context context, List<CategoryModel> categoryList,String email) {
        this.context = context;
        this.categoryList = categoryList;
        this.email = email;
    }

    /* getItemCount() : cho biết số phần tử của dữ liệu
     onCreateViewHolder : tạo ra đối tượng ViewHolder, trong nó chứa View hiện thị dữ liệu
     onBindViewHolder : chuyển dữ liệu phần tử vào ViewHolder*/
    @NonNull
    @Override
    public CategoryAdapater.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_recycler_items, parent, false);
        // here we need to create a layout for recyclerview cell items.
        return new CategoryAdapater.CategoryViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull CategoryAdapater.CategoryViewHolder holder, int position) {
        CategoryModel hero = categoryList.get(position);
        //lay anh tu drawble url luu o git
        Glide.with(context).load(context.getResources().
                getIdentifier(categoryList.get(position).
                        get_ImageCategory(), "drawable", context.getPackageName())). //Lay anh ra tu resource
                into(holder.popularImage);

        //load ten product
        holder.popularName.setText(hero.get_NameCategory());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("email",email);
                bundle.putString("name",hero.get_NameCategory());
                bundle.putString("image",hero.get_ImageCategory());
                Intent i = new Intent(context, CategoryDetailActivity.class);
                i.putExtras(bundle);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return  categoryList.size();
    }
    public  static class CategoryViewHolder extends RecyclerView.ViewHolder{
        ImageView popularImage;
        TextView popularName;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            popularName = itemView.findViewById(R.id.cart_name);
            popularImage = itemView.findViewById(R.id.cart_image);

        }
    }
}
