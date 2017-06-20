package com.example.project1a;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public  void startGame(View view){
        if(view.getId() == R.id.btn_start){
            Intent i = new Intent(MainActivity.this,GameActivity.class);
            startActivity(i);
        }
    }
}
