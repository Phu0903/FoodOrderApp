package com.example.onlyfood.Adapater;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
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
import com.example.onlyfood.R;
import com.example.onlyfood.model.CartModel;
import com.example.onlyfood.model.OrderModel;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class ListHistoryAdapater extends RecyclerView.Adapter<ListHistoryAdapater.ListCartViewHolder>{

    private Context context;
    private List<OrderModel> itemCartList;
    public String Temp_Price,Temp_Quantity;

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

    }

    @Override
    public int getItemCount() {
        return  itemCartList.size();
    }

    public  static class ListCartViewHolder extends RecyclerView.ViewHolder{

        TextView TotalHistory,CrateDate;
        public ListCartViewHolder(@NonNull View itemView) {
            super(itemView);
            TotalHistory =itemView.findViewById(R.id.TotalHistory);
            CrateDate = itemView.findViewById(R.id.CreateDay);

        }
    }

}

