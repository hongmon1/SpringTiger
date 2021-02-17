package com.example.howtocook_version2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button home_btn, fav_btn, myrep_btn, search_btn;
    EditText editText;

    private DBOpenHelper mDbOpenHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //action bar 없애기
        getSupportActionBar().hide();

        home_btn = findViewById(R.id.home_btn);
        fav_btn = findViewById(R.id.fav_btn);
        myrep_btn = findViewById(R.id.myrep_btn);
        editText = findViewById(R.id.editText); //검색창


        home_btn.setEnabled(false);
        fav_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),FavoriteActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_to_left,R.anim.left_to_right);
            }
        });
        myrep_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyRecipeActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_to_left,R.anim.left_to_right);

            }
        });

        editText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), ListViewActivity.class);
                startActivity(intent);
            }
        });


        //데이터베이스 생성(파라메터 Context) 및 오픈
        mDbOpenHelper = new DBOpenHelper(this);
        try {
            mDbOpenHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void onPause(){
        super.onPause();
        overridePendingTransition(R.anim.right_to_left,R.anim.left_to_right);
    }
}
