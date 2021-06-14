package com.example.onlyfood.Adapater;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.onlyfood.R;
import com.example.onlyfood.model.CartModel;
import com.example.onlyfood.model.OrderListModel;

import java.util.List;

public class ListDetailHistoryAdapater extends RecyclerView.Adapter<ListDetailHistoryAdapater.ListCartViewHolder>{

    private Context context;
    private List<OrderListModel> itemCartList;

    public ListDetailHistoryAdapater(Context context, List<OrderListModel> popularList) {
        this.context = context;
        this.itemCartList = popularList;

    }

    @NonNull
    @Override
    public ListDetailHistoryAdapater.ListCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.check_out_items, parent, false);
        return new ListDetailHistoryAdapater.ListCartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListDetailHistoryAdapater.ListCartViewHolder holder, int position) {
        OrderListModel hero = itemCartList.get(position);
        Glide.with(context).load(context.getResources().
                getIdentifier(itemCartList.get(position).
                        get_Image(), "drawable", context.getPackageName())). //Lay anh ra tu resource
                into(holder.Image);
        holder.Name.setText(hero.get_NameProduct());
        holder.Price.setText(String.valueOf(hero.get_Price()));
        holder.quantity.setText(String.valueOf(hero.get_Quantity()));


        Intent intent = new Intent("message_for_history");
        intent.putExtra("quantity",hero.get_Quantity());
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
