package com.example.sujan.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by sujan on 7/29/2017.
 */

public class EditMessage extends AppCompatActivity {
    String messageText;
    int position;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task);
        Intent intent=getIntent();
        messageText=intent.getStringExtra(Intent_Constant.INTENT_MESSAGE_DATA);
        position= intent.getIntExtra(Intent_Constant.ITEM_POSITION,-1);
        EditText messageData= (EditText)findViewById(R.id.edt_1);
        messageData.setText(messageText);
    }

    public void save(View view) {
        String changedMessageText=((EditText)findViewById(R.id.edt_1)).getText().toString();
        Intent intent=new Intent();
        intent.putExtra(Intent_Constant.INTENT_CHANGE_MESSAGE,changedMessageText);
        intent.putExtra(Intent_Constant.ITEM_POSITION,position);
        finish();
    }
}
