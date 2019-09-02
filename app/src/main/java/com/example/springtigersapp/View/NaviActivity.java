package com.example.springtigersapp.View;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.springtigersapp.Model.Navi;
import com.example.springtigersapp.R;

public class NaviActivity extends AppCompatActivity {

    Button button8;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navi);




        button8 = findViewById(R.id.button);
        button8.setOnClickListener(new View.OnClickListener() {
            Navi navi = new Navi(NaviActivity.this);
            @Override
            public void onClick(View view) {
                navi.turnGPSOn();
            }
        });
    }

}
