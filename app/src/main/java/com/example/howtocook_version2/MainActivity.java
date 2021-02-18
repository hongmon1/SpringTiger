package com.example.howtocook_version2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    Button home_btn, fav_btn, myrep_btn;
    Button cate1_btn, cate2_btn, cate3_btn, cate4_btn, cate5_btn;
    EditText editText;
    FloatingActionButton danbee_btn;

    private DBOpenHelper mDbOpenHelper;
    private RecipeDBOpenHelper rDBOpenHelper;

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


        cate1_btn = findViewById(R.id.cate1_btn);
        cate2_btn = findViewById(R.id.cate2_btn);
        cate3_btn = findViewById(R.id.cate3_btn);
        cate4_btn = findViewById(R.id.cate4_btn);
        cate5_btn = findViewById(R.id.cate5_btn);


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
                Intent intent = new Intent(getApplicationContext(), MyRecipeListActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_to_left,R.anim.left_to_right);

            }
        });

        editText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        cate1_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
                intent.putExtra("분류1",1);
                startActivity(intent);
            }
        });

        cate2_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
                intent.putExtra("분류2",2);
                startActivity(intent);
            }
        });
        cate3_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
                intent.putExtra("분류3",3);
                startActivity(intent);
            }
        });
        cate4_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
                intent.putExtra("분류4",4);
                startActivity(intent);
            }
        });
        cate5_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
                intent.putExtra("분류5",5);
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

        rDBOpenHelper = new RecipeDBOpenHelper(this);
        SQLiteDatabase db = rDBOpenHelper.getWritableDatabase();
        Log.d("testDB",db.toString());
        Cursor cursor = db.rawQuery("SELECT * FROM recipes",null);
        String test = cursor.getString(cursor.getColumnIndex("recipe_name"));
        Log.d("testDB",test);

        danbee_btn = findViewById(R.id.danbee_btn);


        //단비 챗봇 플로팅 버튼
        //클릭시 danbeeactivity로 전환
        danbee_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DanbeeActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);
            }
        });
    }

    protected void onPause(){
        super.onPause();
        overridePendingTransition(R.anim.right_to_left,R.anim.left_to_right);
    }
}
