package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity3 extends AppCompatActivity {

FloatingActionButton nextPage, lastPage, deleteButton;
String accumulate = "", time = "...";
   int amountUses = 0;
   String tmp;
   String ifDelete = "1";

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

       history.setMovementMethod(new ScrollingMovementMethod());

       deleteButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               database.delete(DBHelper.TABLE_DISCRIMINANT,null,null);
               history.setText("");
               ifDelete = "0";
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
                amountUses = cursor.getInt(idIndex);
                tmp = "" + amountUses;

            }while(cursor.moveToNext());
        }
        else{
            accumulate = "";
        }
        history.setText(accumulate);
        cursor.close();
        intent2.putExtra("Time", time);
        intent2.putExtra("AmountUses",tmp);



        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent2.putExtra("ifDeleteButPressed", ifDelete);
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