package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity4 extends AppCompatActivity {

    FloatingActionButton lastPage;
    TextView lastUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        lastPage = (FloatingActionButton) findViewById(R.id.page3_2);

        lastUpdate = (TextView) findViewById(R.id.lastUpdate);

        Intent intent = new Intent(this, MainActivity3.class);

        Intent intent1 = new Intent(this, MainActivity2.class);

        lastUpdate.append(getIntent().getStringExtra("Time"));

        lastUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });


        lastPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

    }
}