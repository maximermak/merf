package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String nowPass, nowLog;
    Button entBut;
    TextView password, login, error, signUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbHelper = new DBHelper(this);

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        signUp = (TextView) findViewById(R.id.sign_up);

        error = (TextView) findViewById(R.id.error);

        error.setTextColor(Color.RED);

        entBut = (Button) findViewById(R.id.Enter);

        password = (TextView) findViewById(R.id.editTextTextPassword);

        login = (TextView) findViewById(R.id.editTextTextEmailAddress);



        Intent intentRegistration = new Intent(this, MainActivity5.class);

        Intent intent = new Intent(this, MainActivity2.class);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentRegistration);
            }
        });

        entBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nowLog = login.getText().toString();
                nowPass = password.getText().toString();
                if(!nowLog.trim().equals("") && !nowPass.trim().equals("")) {
                    Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null, null,
                            null, null, null);
                    if (cursor.moveToFirst()) {
                        do {
                            int logIndex = cursor.getColumnIndex(DBHelper.KEY_LOGIN);
                            int passIndex = cursor.getColumnIndex(DBHelper.KEY_PASSWORD);
                            if (nowLog.equals(cursor.getString(logIndex)) && nowPass.equals(cursor.getString(passIndex))) {
                                startActivity(intent);
                            }
                        } while (cursor.moveToNext());
                    } else {
                        error.setText("Неравильный логин или пароль..");
                        cursor.close();
                    }



                }
                else{
                    error.setText("Введите логин и пароль..");
                }
            }
        });


    }
}