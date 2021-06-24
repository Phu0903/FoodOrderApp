package com.example.onlyfood.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlyfood.Adapater.ListDetailHistoryAdapater;
import com.example.onlyfood.R;
import com.example.onlyfood.model.OrderListModel;

import java.util.ArrayList;
import java.util.List;

public class HistoryDetailActivity extends AppCompatActivity {
    String PhonNumber,Address,ID,Quantity,Total,Name;
    ArrayList<OrderListModel> orderListModels;
    TextView NameHistory,PhoneHistory,AddressHistory,IDOrder,CreateDay,QuantityHistory,TotalHisory;
    RecyclerView ListTtemsRecycerview;
    ListDetailHistoryAdapater ListCart;
    Button BackHome;
    Integer temp =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_items_detail);
        IntentFrom();
        init();
        setText();
        getPopularData(orderListModels);
        BackHome();
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("message_for_history"));

    }
    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Quantity = intent.getStringExtra("quantity");
            temp = Integer.valueOf(Quantity) + temp;
            QuantityHistory.setText(temp.toString() + " items");

        }
    };
    private void setText()
    {
       PhoneHistory.setText(PhonNumber);
       AddressHistory.setText(Address);
       IDOrder.setText(ID);
       TotalHisory.setText(Total);
       NameHistory.setText(Name);
    }
    private void IntentFrom()
    {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        PhonNumber = bundle.getString("PhoneNumber");
        Address = bundle.getString("Address");
        ID = bundle.getString("ID");
        Total = bundle.getString("Total");
        Name = bundle.getString("name");
        orderListModels = (ArrayList<OrderListModel>) bundle.getSerializable("Product");



    }

    private void init()
    {
        NameHistory = findViewById(R.id.Name_History);
        PhoneHistory = findViewById(R.id.Phone_History);
        AddressHistory =findViewById(R.id.Address_History);
        IDOrder =findViewById(R.id.ID_Order);
        CreateDay = findViewById(R.id.Create_Day);
        QuantityHistory = findViewById(R.id.Quantity_History);
        TotalHisory = findViewById(R.id.Total_History);
        ListTtemsRecycerview = findViewById(R.id.List_Products_History);
        BackHome =findViewById(R.id.back_home3);
    }

    private void  getPopularData(List<OrderListModel> popularList){
        ListCart = new ListDetailHistoryAdapater(HistoryDetailActivity.this, popularList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HistoryDetailActivity.this, LinearLayoutManager.VERTICAL, false);
        ListTtemsRecycerview.setLayoutManager(layoutManager);
        ListTtemsRecycerview.setAdapter(ListCart);

    }
    private void BackHome()
    {
        BackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
