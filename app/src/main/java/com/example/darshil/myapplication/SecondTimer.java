package com.example.darshil.myapplication;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SecondTimer extends AppCompatActivity {
    private TextView ask_pass;
    private EditText type_pass;
    private Button submit_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer_second);

        ask_pass = (TextView) findViewById(R.id.display_req_pass);
        type_pass = (EditText) findViewById(R.id.enter_pass);
        submit_pass = (Button) findViewById(R.id.submit_area);
        submit_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFromFile();
                if(readFromFile() == 1){
                    startActivity(new Intent(SecondTimer.this, LoginActivity.class));
                }
            }
        });

    }
    public int readFromFile()
    {

        String ret = type_pass.getText().toString();

        try {
           // InputStream inputStream = openFileInput("storePW.txt");



            FileInputStream finp = openFileInput("storePW.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(finp);
            BufferedReader passcode_correct = new BufferedReader(inputStreamReader);
                   String answer= passcode_correct.readLine();
            Log.d("Test","Answer: "+answer+" ret: "+ret);
//                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//                    String passcode_correct = bufferedReader.readLine();

                        if(ret.equals(answer)){
                            Toast.makeText(getApplicationContext(),"Passcode Accepted",Toast.LENGTH_SHORT).show();
                            return 1;

                        } else{
                            Toast.makeText(getApplicationContext(),"Incorrect Passcode",Toast.LENGTH_SHORT).show();
                            return 0;
                        }




        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
        return 2;
    }
}
