package com.example.springtigersapp.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class LogoActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(LogoActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
