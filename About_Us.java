package com.example.sujan.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.Inflater;

/**
 * Created by sujan on 7/26/2017.
 */

public class About_Us extends AppCompatActivity {
           @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
               setContentView(R.layout.aboutus);
           }

    public void sendback(View view) {
        Intent intent= new Intent(About_Us.this,MainActivity.class);
        startActivity(intent);

    }
}
