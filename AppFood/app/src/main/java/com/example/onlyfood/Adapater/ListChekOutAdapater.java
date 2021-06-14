package com.example.onlyfood.Adapater;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.onlyfood.R;
import com.example.onlyfood.model.CartModel;
import com.example.onlyfood.networking.ApiServices;
import com.example.onlyfood.networking.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ListChekOutAdapater extends RecyclerView.Adapter<ListChekOutAdapater.ListCartViewHolder>{

    private Context context;
    private List<CartModel> itemCartList;

    public ListChekOutAdapater(Context context, List<CartModel> popularList) {
        this.context = context;
        this.itemCartList = popularList;

    }

    @NonNull
    @Override
    public ListChekOutAdapater.ListCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.check_out_items, parent, false);
        return new ListChekOutAdapater.ListCartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListChekOutAdapater.ListCartViewHolder holder, int position) {
        CartModel hero = itemCartList.get(position);
        Glide.with(context).load(context.getResources().
                getIdentifier(itemCartList.get(position).
                        get_Image(), "drawable", context.getPackageName())). //Lay anh ra tu resource
                into(holder.Image);
        holder.Name.setText(hero.get_NameProduct());
        holder.Price.setText(String.valueOf(hero.get_Price()));
        holder.quantity.setText(String.valueOf(hero.get_quantity()));



        Intent intent = new Intent("message");
        intent.putExtra("quantity",hero.get_quantity());
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

    }



    @Override
    public int getItemCount() {
        return  itemCartList.size();
    }

    public  static class ListCartViewHolder extends RecyclerView.ViewHolder{
        ImageView Image;
        TextView Name,Price,quantity;
        public ListCartViewHolder(@NonNull View itemView) {
            super(itemView);
            Price = itemView.findViewById(R.id.cart_totalprice);
            Name = itemView.findViewById(R.id.cart_name);
            Image = itemView.findViewById(R.id.cart_image);
            quantity = itemView.findViewById(R.id.quantity_checkout);

        }
    }
}
