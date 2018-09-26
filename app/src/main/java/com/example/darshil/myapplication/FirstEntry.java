package com.example.darshil.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class FirstEntry extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_entry);
        Log.d("Test"," IN THE CLASS");

        final EditText editText = findViewById(R.id.pw);
        Button btnn = findViewById(R.id.btn);
        ImageButton button = findViewById(R.id.back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstEntry.this,MainActivity.class);
                startActivity(intent);

            }
        });

        btnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                    String msg = editText.getText().toString();
                    String fileName = "storePW.txt";

                    try {

                        FileOutputStream fp = openFileOutput(fileName,MODE_PRIVATE);
                        fp.write(msg.getBytes());
                        fp.close();
                        startActivity(new Intent("android.intent.action.PianoSetPass"));

                    } catch (IOException e) {
                        Log.d("Test","Crashed");
                        e.printStackTrace();
                    }

               }
        });


    }



}

