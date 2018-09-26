package com.example.darshil.myapplication;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private ListView applist;
    private Button lock;
    private Button unlock;
    private ArrayAdapter<String> list_of_apps;
    private ArrayList<String> List_of_apps = new ArrayList<>();

    public void installedApps(){
        List<PackageInfo> packList = getPackageManager().getInstalledPackages(0);
        for(int i = 0; i < packList.size(); i++){
            PackageInfo packInfo = packList.get(i);
            if((packInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0)
                List_of_apps.add(packInfo.applicationInfo.loadLabel(getPackageManager()).toString());
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        lock = (Button) findViewById(R.id.lock);
        unlock = (Button) findViewById(R.id.unlock);
        applist = (ListView) findViewById(R.id.List_Apps);


        lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent changePw = new Intent(LoginActivity.this,Piano.class);
//                changePw.putExtra("Check",10);
//                startActivity(changePw);

            }
        });

        unlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    list_of_apps = new ArrayAdapter<>(LoginActivity.this, android.R.layout.simple_list_item_1, List_of_apps);
    applist.setAdapter(list_of_apps);
    list_of_apps.notifyDataSetChanged();
    installedApps();

    applist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override

        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



        }
    });


    }

     MainActivity obj = new MainActivity();
}
