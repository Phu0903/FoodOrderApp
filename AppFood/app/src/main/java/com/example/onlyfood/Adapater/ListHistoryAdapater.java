package com.example.onlyfood.Adapater;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.onlyfood.Activity.DetailFoodActivity;
import com.example.onlyfood.Activity.HistoryDetailActivity;
import com.example.onlyfood.R;
import com.example.onlyfood.model.CartModel;
import com.example.onlyfood.model.OrderListModel;
import com.example.onlyfood.model.OrderModel;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListHistoryAdapater extends RecyclerView.Adapter<ListHistoryAdapater.ListCartViewHolder>{

    private Context context;
    private List<OrderModel> itemCartList;
    public String Temp_Price,Temp_Quantity;
   private ArrayList<OrderListModel> orderListModels;
   private Integer total = 0;
    public ListHistoryAdapater(Context context, List<OrderModel> popularList) {
        this.context = context;
        this.itemCartList = popularList;

    }

    /* getItemCount() : cho biết số phần tử của dữ liệu
     onCreateViewHolder : tạo ra đối tượng ViewHolder, trong nó chứa View hiện thị dữ liệu
     onBindViewHolder : chuyển dữ liệu phần tử vào ViewHolder*/
    @NonNull
    @Override
    public ListHistoryAdapater.ListCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.history_purchase_items, parent, false);
        return new ListHistoryAdapater.ListCartViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ListHistoryAdapater.ListCartViewHolder holder, int position) {
        OrderModel hero = itemCartList.get(position);

        holder.TotalHistory.setText("$ "+String.valueOf(hero.get_total()));
        //
        orderListModels = new ArrayList<>();
        for (OrderListModel orderList : hero.get_product())
        {
            orderListModels.add(orderList);
        }
        for (OrderListModel list : orderListModels)
        {
            total = Integer.valueOf(list.get_Quantity() )+ total;
        }
        holder.NumberItems.setText(total.toString());
        //Format date
        Date date1 = hero.get_createDay();
        SimpleDateFormat localDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String sTime = localDateFormat.format(date1);
        String[] s1 = sTime.split(" ");
        //today
        Date datenow = new Date(System.currentTimeMillis());
        String today = localDateFormat.format(datenow);
        String[] s2 = today.split(" ");
        //yesterday
        Date yesterday = new Date(System.currentTimeMillis() - 24*60*60*1000);
        String yesterday_temp = localDateFormat.format(yesterday);
        String[] s3= yesterday_temp.split(" ");
        if( s3[0].equals(s1[0]))
        {
            holder.CrateDate.setText("Yesterday");
        }
        else if(s1[0].equals(s2[0]))
        {
            holder.CrateDate.setText("Today");
        }
        else
        {
            holder.CrateDate.setText(String.valueOf(s1[0]));
        }
        /*PhonNumber = bundle.getString("PhoneNumber");
        Address = bundle.getString("Address");
        ID = bundle.getString("ID");
        Quantity = bundle.getString("Quantity");
        Total = bundle.getString("Total");
        orderListModels = (ArrayList<OrderListModel>) bundle.getSerializable("Product");*/
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, HistoryDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("PhoneNumber",hero.get_phonenumber());
                bundle.putString("Address",hero.get_address());
                bundle.putString("ID",hero.get_OrderID());
                bundle.putString("Quantity",total.toString());
                bundle.putString("Total",hero.get_total());
                bundle.putSerializable("Product",(Serializable)orderListModels);
                i.putExtras(bundle);
                context.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return  itemCartList.size();
    }

    public  static class ListCartViewHolder extends RecyclerView.ViewHolder{

        TextView TotalHistory,CrateDate,NumberItems;
        public ListCartViewHolder(@NonNull View itemView) {
            super(itemView);
            TotalHistory =itemView.findViewById(R.id.TotalHistory);
            CrateDate = itemView.findViewById(R.id.CreateDay);
            NumberItems = itemView.findViewById(R.id.number_items);

        }
    }

}
