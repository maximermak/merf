package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {
    int a,b,c;
    FloatingActionButton nextPage;
    TextView tvA, tvB, tvC, toSee;
    int tmp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        DBHelper dbHelper = new DBHelper(this);

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();

        nextPage = (FloatingActionButton) findViewById(R.id.page12);

        tvA = (TextView) findViewById(R.id.tieA);

        tvB = (TextView) findViewById(R.id.tieB);

        tvC = (TextView) findViewById(R.id.tieC);

        toSee = (TextView) findViewById(R.id.toSeeAText);

        Intent intent = new Intent(this, MainActivity3.class);



        tvA.setText(getIntent().getStringExtra("justA"));

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                    if(!tvA.getText().toString().trim().equals("") && !tvB.getText().toString().trim().equals("") && !tvC.getText().toString().trim().equals("")) {
                        a = Integer.parseInt(tvA.getText().toString());
                        b = Integer.parseInt(tvB.getText().toString());
                        c = Integer.parseInt(tvC.getText().toString());
                        toSee.setText(Discriminant.discText(a,b,c));
                        String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
                        cv.put(DBHelper.KEY_A,a);
                        cv.put(DBHelper.KEY_B,b);
                        cv.put(DBHelper.KEY_C,c);
                        cv.put(DBHelper.KEY_TIME, currentTime);
                        database.insert(DBHelper.TABLE_DISCRIMINANT, null, cv);

                    }



                    if(tvA.getText().toString().trim().equals("") || tvB.getText().toString().trim().equals("") || tvC.getText().toString().trim().equals("")) {

                        String untypedA = "";
                        String untypedB = "";
                        String untypedC = "";



                        if(tvA.getText().toString().trim().equals(""))
                        {
                            untypedA = "a ";
                        }
                        if(tvB.getText().toString().trim().equals(""))
                        {

                            untypedB = " b";

                        }
                        if(tvC.getText().toString().trim().equals(""))
                        {
                            untypedC = " c";
                        }
                        String untypedText = "Введите " + untypedA + untypedB + untypedC;
                        toSee.setText(untypedText);
                    }
            }
        };
        if(getIntent().getStringExtra("anna") != null) {
            tmp = Integer.parseInt(getIntent().getStringExtra("anna"));
        }
        if(tmp == 1) {
            Cursor cursor = database.query(DBHelper.TABLE_DISCRIMINANT, null, null, null, null, null, null);
            if (cursor.moveToLast()) {
                do {
                    tvA.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.KEY_A)));
                    tvB.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.KEY_B)));
                    tvC.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.KEY_C)));
                } while (cursor.moveToNext());
            }
            tmp = 0;
            cursor.close();
        }


        tvA.addTextChangedListener(textWatcher);
        tvB.addTextChangedListener(textWatcher);
        tvC.addTextChangedListener(textWatcher);

        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });






    }
}