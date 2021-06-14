package com.example.onlyfood.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.onlyfood.R;

public class NotificationActivity extends AppCompatActivity {
    Button BackToHome;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            email= bundle.getString("notification");
        }
        else {
            Log.d("null","null");
        }
        BackToHome = findViewById(R.id.Back_To_Home);
        BackToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotificationActivity.this,MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("username",email);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });
    }

}