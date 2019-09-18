package com.example.springtigersapp.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.springtigersapp.Model.Navi;
import com.example.springtigersapp.R;

public class MainActivity extends AppCompatActivity {

    Button button;
    Button button_search;
    //안녕예주야
    //안녕소현아
    //안녕혜지야
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NaviActivity.class);
                startActivity(intent);
            }
        });

        button_search = findViewById(R.id.button_search);
        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, DBActivity.class);
                startActivity(intent);
            }
        });


    }
}
