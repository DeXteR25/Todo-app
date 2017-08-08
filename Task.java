package com.example.sujan.todo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by sujan on 7/27/2017.
 */

public class Task extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task);

    }


    public void save(View view) {
        String file_name="Todo";
        String message= ((EditText)findViewById(R.id.edt_1)).getText().toString();
        if(message.equals("")){

        }
        else{
            Intent intent=new Intent();
            intent.putExtra(Intent_Constant.INTENT_MESSAGE_FIELD,message);
            setResult(Intent_Constant.INTENT_RESULT_CODE,intent);
            finish();
        }
        try {
            FileOutputStream fileOutputStream= openFileOutput(file_name,MODE_PRIVATE);
            fileOutputStream.write(message.getBytes());
            fileOutputStream.close();
            Toast.makeText(getApplicationContext(),"File Saved",Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
