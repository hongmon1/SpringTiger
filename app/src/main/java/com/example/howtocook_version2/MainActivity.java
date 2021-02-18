package com.example.howtocook_version2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button home_btn, fav_btn, myrep_btn;
    Button cate1, cate2, cate3, cate4, cate5;
    EditText editText;
    FloatingActionButton danbee_btn;

    private DBOpenHelper mDbOpenHelper;

    ImageButton button_food;
    AutoCompleteTextView autoFoodName;
    private List<String> list;
    private List<String> id_list;

    private Cursor mCursor;
    String food_name;
    String food_id;

    ImageButton button_cate1;
    ImageButton button_cate2;
    ImageButton button_cate3;
    ImageButton button_cate4;
    ImageButton button_cate5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        //action bar 없애기
        getSupportActionBar().hide();

        home_btn = findViewById(R.id.home_btn);
        fav_btn = findViewById(R.id.fav_btn);
        myrep_btn = findViewById(R.id.myrep_btn);
        //editText = findViewById(R.id.editText); //검색창

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
/*
        editText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
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
*/
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

        button_food = (ImageButton)findViewById(R.id.btn_food);
        autoFoodName = (AutoCompleteTextView)findViewById(R.id.autoFoodName);

        mDbOpenHelper = new DBOpenHelper(this);
        mDbOpenHelper.open();
        /*
        for(int i = 0 ; i <30; i++) {
            mDbOpenHelper.deleteRepColumn(i);
        }
*/
        mDbOpenHelper.deleteRepColumn(30);

        for(int i = 0 ; i <15; i++) {
            mDbOpenHelper.insertColumn("떡볶이"+i,"떡","1.떡을 삶는다","1","empty image");
        }
        for(int i = 15 ; i <30; i++) {
            mDbOpenHelper.insertColumn("떡볶이"+i,"떡","1.떡을 삶는다","2","empty image");
        }


        list = new ArrayList<String>();
        id_list = new ArrayList<String>();
        settingList(list, id_list);

        autoFoodName.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, list));

        button_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String foodName = autoFoodName.getText().toString();
                String foodId = "null";
                for(int i =0; i< list.size(); i++){
                    if(list.get(i).equals(foodName)){
                        foodId = id_list.get(i);
                        break;
                    }
                }
                Intent intent = new Intent(MainActivity.this, SearchContentActivity.class);
                intent.putExtra("it_idRecipe", foodId);
                startActivity(intent);

            }
        });


        //Category
        button_cate1 = (ImageButton)findViewById(R.id.cate1_btn);
        button_cate2 = (ImageButton)findViewById(R.id.cate2_btn);
        button_cate3 = (ImageButton)findViewById(R.id.cate3_btn);
        button_cate4 = (ImageButton)findViewById(R.id.cate4_btn);
        button_cate5 = (ImageButton)findViewById(R.id.cate5_btn);


        button_cate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), CateListActivity.class);
                intent.putExtra("it_idRecipe", "1");
                startActivity(intent);

            }
        });

        button_cate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), CateListActivity.class);
                intent.putExtra("it_idRecipe", "2");
                startActivity(intent);

            }
        });

        button_cate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), CateListActivity.class);
                intent.putExtra("it_idRecipe", "3");
                startActivity(intent);

            }
        });

        button_cate4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), CateListActivity.class);
                intent.putExtra("it_idRecipe", "4");
                startActivity(intent);

            }
        });

        button_cate5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), CateListActivity.class);
                intent.putExtra("it_idRecipe", "5");
                startActivity(intent);

            }
        });

    }

    protected void onPause(){
        super.onPause();
        overridePendingTransition(R.anim.right_to_left,R.anim.left_to_right);
    }

    private void settingList(List<String> list, List<String> id_list){
        mCursor = mDbOpenHelper.getRepAllColumns();

        while(mCursor.moveToNext()){

            food_id = mCursor.getString(mCursor.getColumnIndex("_id"));
            food_name = mCursor.getString(mCursor.getColumnIndex("recipe_name"));
            list.add(food_name);
            id_list.add(food_id);
            //cook_img = mCursor.getString(mCursor.getColumnIndex("recipe_image"));

            //Log.d("dbTest", cook_name);

        }
        mCursor.close();

    }
}
