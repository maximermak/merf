package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity5 extends AppCompatActivity {
    String nowLog, nowPass;
    Button createBut,backBut;
    TextView login, password;
    TextView created;
    boolean createdBoolean = false;
    boolean TorF;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        login = (TextView) findViewById(R.id.inputerLogin);

        password = (TextView) findViewById(R.id.inputerPassword);

        createBut = (Button) findViewById(R.id.toCreate);

        created = (TextView) findViewById(R.id.alreadyCreated);

        Intent intent = new Intent(this, MainActivity.class);

        DBHelper dbHelper = new DBHelper(this);

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues =  new ContentValues();

        backBut = (Button) findViewById(R.id.backBut);

        createBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = database.query(DBHelper.TABLE_CONTACTS,null,null,null,
                        null,null,null);
                nowLog = login.getText().toString();
                nowPass = password.getText().toString();
                int loginIndex = cursor.getColumnIndex(DBHelper.KEY_LOGIN);
                int passwordIndex = cursor.getColumnIndex(DBHelper.KEY_PASSWORD);
                if(cursor.moveToFirst()) {
                    do {
                        if(cursor.getString(loginIndex).equals(nowLog) && cursor.getString(passwordIndex).equals(nowPass))
                        {
                            created.setText("Такой логин и пароль уже сущестует..");
                            createdBoolean = true;
                        }
                    }while(cursor.moveToNext());
                }
                if (!login.getText().toString().trim().equals("") && !password.getText().toString().trim().equals("") && !createdBoolean){
                    contentValues.put(DBHelper.KEY_LOGIN, nowLog);
                    contentValues.put(DBHelper.KEY_PASSWORD, nowPass);
                    database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);
                    startActivity(intent);
                }
                if (login.getText().toString().trim().equals("") && password.getText().toString().trim().equals(""))
                {
                    created.setText("Введите логин и пароль..");
                    TorF = true;
                }
                else if(login.getText().toString().trim().equals("") && !TorF) {
                    created.setText("Введите логин..");
                }
                else if(password.getText().toString().trim().equals("") && !TorF) {
                    created.setText("Введите пароль..");
                }
                TorF = false;
                cursor.close();
            }
        });


        backBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

    }
}