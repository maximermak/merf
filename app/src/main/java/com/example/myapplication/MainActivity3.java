package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity3 extends AppCompatActivity {

FloatingActionButton nextPage, lastPage, deleteButton;
String accumulate = "", time = "...";
TextView history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        DBHelper dbHelper = new DBHelper(this);

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        nextPage = (FloatingActionButton) findViewById(R.id.page2_3);

        lastPage = (FloatingActionButton) findViewById(R.id.page2_1);

        deleteButton = (FloatingActionButton) findViewById(R.id.deleteButton);

        Intent intent1 = new Intent(this, MainActivity2.class);

        Intent intent2 = new Intent(this, MainActivity4.class);

       history = (TextView) findViewById(R.id.myHistory);

       deleteButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               database.delete(DBHelper.TABLE_DISCRIMINANT,null,null);
               history.setText("");
           }
       });



        Cursor cursor = database.query(DBHelper.TABLE_DISCRIMINANT,null,null,null,null,null,null);
        if(cursor.moveToFirst())
        {
            do{
                int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                int aIndex = cursor.getColumnIndex(DBHelper.KEY_A);
                int bIndex = cursor.getColumnIndex(DBHelper.KEY_B);
                int cIndex = cursor.getColumnIndex(DBHelper.KEY_C);
                int timeIndex = cursor.getColumnIndex(DBHelper.KEY_TIME);
                accumulate += cursor.getInt(idIndex) + ": " + cursor.getString(aIndex) +
                        ", " + cursor.getString(bIndex) + ", " + cursor.getString(cIndex) + " Время: " + cursor.getString(timeIndex) + "\n";
                time = cursor.getString(timeIndex);



            }while(cursor.moveToNext());
        }
        else{
            accumulate = "";
        }
        history.setText(accumulate);
        cursor.close();
        intent2.putExtra("Time", time);



        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent2);
            }
        });

        lastPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent1);
            }
        });
    }
}