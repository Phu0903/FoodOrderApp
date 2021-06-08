package com.example.onlyfood.Adapater;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.onlyfood.Activity.DetailFoodActivity;
import com.example.onlyfood.R;
import com.example.onlyfood.model.CartModel;
import com.example.onlyfood.networking.ApiServices;
import com.example.onlyfood.networking.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ListCartAdapater extends RecyclerView.Adapter<ListCartAdapater.ListCartViewHolder>{

    private Context context;
    private List<CartModel> itemCartList;


    public ListCartAdapater(Context context, List<CartModel> popularList) {
        this.context = context;
        this.itemCartList = popularList;

    }


    /* getItemCount() : cho biết số phần tử của dữ liệu
     onCreateViewHolder : tạo ra đối tượng ViewHolder, trong nó chứa View hiện thị dữ liệu
     onBindViewHolder : chuyển dữ liệu phần tử vào ViewHolder*/
    @NonNull
    @Override
    public ListCartAdapater.ListCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_list, parent, false);
        return new ListCartAdapater.ListCartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListCartAdapater.ListCartViewHolder holder, int position) {
        Retrofit retrofit = RetrofitClient.getRetrofitInstance();
        ApiServices jsonPlaceHolderApi = retrofit.create(ApiServices.class);
        CartModel hero = itemCartList.get(position);
        Glide.with(context).load(context.getResources().
                getIdentifier(itemCartList.get(position).
                        get_Image(), "drawable", context.getPackageName())). //Lay anh ra tu resource
                into(holder.Image);
        holder.Name.setText(hero.get_NameProduct());
        holder.Price.setText(String.valueOf(hero.get_Price()));
        holder.quantity.setText(String.valueOf(hero.get_quantity()));
        Log.d("Price",itemCartList.get(position).get_Price());
        holder.remove_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CallListFoodCart(jsonPlaceHolderApi,hero.get_ProductID(),hero.get_email());
                itemCartList.remove(position);
                notifyDataSetChanged();//thay đổi Giao diện


            }
        });
    }


    @Override
    public int getItemCount() {
        return  itemCartList.size();
    }

    public  static class ListCartViewHolder extends RecyclerView.ViewHolder{
        ImageView Image;
        TextView Name,Price,quantity;
        ImageView remove_item;

        public ListCartViewHolder(@NonNull View itemView) {
            super(itemView);
            Price = itemView.findViewById(R.id.cart_totalprice);
            Name = itemView.findViewById(R.id.cart_name);
            Image = itemView.findViewById(R.id.cart_image);
            quantity = itemView.findViewById(R.id.amount);
            remove_item =itemView.findViewById(R.id.delete_item);



        }
    }

    public void CallListFoodCart(ApiServices jsonPlaceHolderApi, String ProductID,String email)
    {

        Call<CartModel> call = jsonPlaceHolderApi.deleteItem(ProductID,email);

        call.enqueue(new Callback<CartModel>() {
            @Override
            public void onResponse(Call<CartModel> call, Response<CartModel> response) {
                if (response.isSuccessful()) {

                    Log.d("Xóa",response.body().toString());


                }


            }
            @Override
            public void onFailure(Call<CartModel> call, Throwable t) {
                Toast.makeText(context,"Failed"+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

}
