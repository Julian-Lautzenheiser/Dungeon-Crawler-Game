package com.example.myapplication.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startBtn = findViewById(R.id.startBtn);
        Button closeButton = findViewById(R.id.closeBtn);

        closeButton.setOnClickListener(v -> {
            finish();
        });

        startBtn.setOnClickListener(v -> {
            Intent config = new Intent(MainActivity.this, ConfigurationActivity.class);
            startActivity(config);
            finish();
        });
    }
}
