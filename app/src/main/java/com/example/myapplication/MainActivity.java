package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startBtn = findViewById(R.id.startBtn);
        Button closeButton = findViewById(R.id.closeBtn);

        closeButton.setOnClickListener(v -> {
            finish();
        });

        //Check if name is valid, if so then set difficulty based on radioButton
        startBtn.setOnClickListener(v -> {
            Intent config = new Intent(MainActivity.this, ConfigurationActivity.class);
            startActivity(config);
            finish();
        });
    }
}
