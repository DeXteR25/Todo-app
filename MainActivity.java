package com.example.sujan.todo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import static android.media.CamcorderProfile.get;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity {
    Toolbar  mtoolbar;
    ListView lst_vew;
    ArrayList<String> arraylist;
    ArrayAdapter<String>arrayAdapter;
    String message;
    int posion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar mToolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        lst_vew=(ListView)findViewById(R.id.Lst_vew);
        arraylist=new ArrayList<>();
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arraylist);
        lst_vew.setAdapter(arrayAdapter);
        lst_vew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,EditMessage.class);
                intent.putExtra(Intent_Constant.INTENT_MESSAGE_DATA,arraylist.get(position).toString());
                intent.putExtra(Intent_Constant.ITEM_POSITION,position);
                startActivityForResult(intent,Intent_Constant.INTENT_REQUEST_CODE_TWO);
            }
        });
        try {
            Scanner sc= new Scanner(openFileInput("Todo.txt"));
            while (sc.hasNextLine()){
                String data= sc.nextLine();
                arrayAdapter.add(data);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.mymenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_about_us)
        {
            Intent intent=new Intent(getApplicationContext(),About_Us.class);
            Toast.makeText(MainActivity.this,"About Us",Toast.LENGTH_LONG).show();
            startActivity(intent);
        }
        if (item.getItemId()==R.id.action_setting){
            Toast.makeText(MainActivity.this,"Setting",Toast.LENGTH_LONG).show();
        }
        if(item.getItemId()==R.id.action_delect){
            Toast.makeText(MainActivity.this,"Delect",Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }


    public void sendMessage(View view) {
        Intent intent=new Intent(getApplicationContext(),Task.class);
        startActivityForResult(intent,Intent_Constant.INTENT_REQUEST_CODE);
    }

    @Override
    public void onBackPressed() {
        try {
            PrintWriter pw= new PrintWriter(openFileOutput("Todo.txt", Context.MODE_PRIVATE));
            for(String data:arraylist){
                pw.println(data);
            }
                pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if(resultCode==Intent_Constant.INTENT_RESULT_CODE) {
           message = data.getStringExtra(Intent_Constant.INTENT_MESSAGE_FIELD);
           arraylist.add(message);
           arrayAdapter.notifyDataSetChanged();
       }
       else if(resultCode==Intent_Constant.INTENT_RESULT_CODE_TWO){
           message=data.getStringExtra(Intent_Constant.INTENT_CHANGE_MESSAGE);
            posion=data.getIntExtra(Intent_Constant.ITEM_POSITION,-1);
           arraylist.remove(posion);
           arraylist.add(posion,message);
            arrayAdapter.notifyDataSetChanged();
       }

    }


}
