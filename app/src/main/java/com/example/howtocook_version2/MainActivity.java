package com.example.howtocook_version2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
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

    Button home_btn, fav_btn, myrep_btn;
    EditText editText;

    ListView listview = new ListView();

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
        editText = findViewById(R.id.editText);

        listview.editSearch = (EditText) findViewById(R.id.editText);
        listview.listView = (android.widget.ListView)findViewById(R.id.listView);

        listview.list = new ArrayList<String>();
        listview.settingList();     //임시데이터
        listview.arraylist = new ArrayList<String>();
        listview.arraylist.addAll(listview.list);
        listview.adapter = new com.example.howtocook_version2.SearchAdapter(listview.list, this);
        listview.listView.setAdapter(listview.adapter);

        // input창에 검색어를 입력시 "addTextChangedListener" 이벤트 리스너를 정의
        listview. editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) {
                String text = listview.editSearch.getText().toString();
                listview.search(text);
            }
        });


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

        listview.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(MainActivity.this, FavoriteActivity.class);
           //     it.putExtra("it_listData", data[position]);   어떤 값이 선택되었는지 알려줘야한다
                startActivity(it);
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
