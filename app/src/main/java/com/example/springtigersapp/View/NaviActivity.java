package com.example.springtigersapp.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.springtigersapp.Model.Navi;
import com.example.springtigersapp.R;

public class NaviActivity extends AppCompatActivity {

    Button button1, button2, button3, button8;
    Navi navi = new Navi(NaviActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navi);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button8 = findViewById(R.id.button8);

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navi.turnGPSOn();
            }
        });

    }


}
