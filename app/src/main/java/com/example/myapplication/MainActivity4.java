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
    TextView lastUpdate,amountUses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        lastPage = (FloatingActionButton) findViewById(R.id.page3_2);

        lastUpdate = (TextView) findViewById(R.id.lastUpdate);
        amountUses = findViewById(R.id.amountOfUses);

        Intent intent = new Intent(this, MainActivity3.class);

        Intent intent1 = new Intent(this, MainActivity2.class);


        if(getIntent().getStringExtra("Time") != null && getIntent().getStringExtra("AmountUses") != null &&
                getIntent().getStringExtra("ifDeleteButPressed").equals("1")) {
            lastUpdate.append(getIntent().getStringExtra("Time"));
            amountUses.append(getIntent().getStringExtra("AmountUses"));
        }else
        {
            lastUpdate.append("...");
            amountUses.append("...");
        }


        lastUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!getIntent().getStringExtra("Time").equals(""))
                    intent1.putExtra("anna", "1");
                    startActivity(intent1);
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