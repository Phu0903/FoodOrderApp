package com.example.onlyfood.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlyfood.R;
import com.example.onlyfood.model.OrderListModel;

import java.util.ArrayList;
import java.util.List;

public class HistoryDetailActivity extends AppCompatActivity {
    String PhonNumber,Address,ID,Quantity,Product,Total;
    ArrayList<OrderListModel> orderListModels;
    TextView NameHistory,PhoneHistory,AddressHistory,IDOrder,CreateDay,QuantityHistory,TotalHisory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_items);
        IntentFrom();
        init();
        setText();


    }
    private void setText()
    {
       PhoneHistory.setText(PhonNumber);
       AddressHistory.setText(Address);
       IDOrder.setText(ID);
       QuantityHistory.setText(Quantity);
       TotalHisory.setText(Total);
    }
    private void IntentFrom()
    {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        PhonNumber = bundle.getString("PhoneNumber");
        Address = bundle.getString("Address");
        ID = bundle.getString("ID");
        Quantity = bundle.getString("Quantity");
        Total = bundle.getString("Total");
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
    }
}
