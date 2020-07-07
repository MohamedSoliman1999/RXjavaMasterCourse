package com.example.rxjavamastercourse.UI.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.rxjavamastercourse.R;
import com.example.rxjavamastercourse.RXApp;
import com.example.rxjavamastercourse.UI.SecondActivity.Activity.SecondActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void nextActivityClick(View view) {
        Intent intent=new Intent(getApplicationContext(), SecondActivity.class);
        startActivity(intent);
    }
}