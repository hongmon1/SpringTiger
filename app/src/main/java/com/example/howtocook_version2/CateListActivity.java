package com.example.howtocook_version2;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CateListActivity extends AppCompatActivity {
    Button home_btn, fav_btn, myrep_btn;


    private DBOpenHelper mDbOpenHelper;
    private Cursor mCursor;
    //private Cursor vCursor;

    private String cate_id;
    private String cook_img;
    private String cook_name;
    private String cook_cate;


    private ArrayList<ListViewItem> alist;
    private ListViewAdapter listViewAdapter;
    private ListView listView;
    private ListViewItem item;

    private List<String> list;
    private List<String> id_list;

    Intent it2 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catelist);

        it2 = this.getIntent();
        cate_id = it2.getExtras().getString("it_idRecipe");
        //action bar 없애기
        getSupportActionBar().hide();


        //하단 탭 버튼 세팅
        home_btn = findViewById(R.id.home_btn);
        fav_btn = findViewById(R.id.fav_btn);
        myrep_btn = findViewById(R.id.myrep_btn);
        //editText = findViewById(R.id.editText); //검색창

        home_btn.setEnabled(false);
        fav_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FavoriteActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);
            }
        });
        myrep_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyRecipeListActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);

            }
        });




        list = new ArrayList<String>();
        id_list = new ArrayList<String>();


        mDbOpenHelper = new DBOpenHelper(this);
        mDbOpenHelper.open();
        // favorite_recipe_id 가져오기

        alist = new ArrayList<ListViewItem>();
        listViewAdapter = new ListViewAdapter(this, alist);
        listView = findViewById(R.id.listview);
        listView.setAdapter(listViewAdapter);

        /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String foodName = autoFoodName.getText().toString();
                String foodId = "null";
                for(int i =0; i< list.size(); i++){
                    if(list.get(i).equals(foodName)){
                        foodId = id_list.get(i);
                        break;
                    }
                }
                Intent intent = new Intent(CateListActivity.this, SearchContentActivity.class);
                intent.putExtra("it_idRecipe", foodId);
                startActivity(intent);
            }
        });
        */

        //String name, String ingre, String desc, String com, String image
        /*for(int i = 0 ; i <15; i++) {
            mDbOpenHelper.insertColumn("떡볶이"+i,"떡","1.떡을 삶는다","comment","empty image");
        }
        for(int i = 0 ; i <3; i++) {
            mDbOpenHelper.insertColumn(i);
        }*/
        //mDbOpenHelper.deleteFavColumn(1);

        mCursor = null;
        mCursor = mDbOpenHelper.getRepAllColumns();
        Log.d("dbTest", "rep Count = " + mCursor.getCount());

        while (mCursor.moveToNext()) {
            //recipe_id = mCursor.getString(mCursor.getColumnIndex("recipe_id"));
            //Log.d("dbTest", recipe_id);
            cook_cate = mCursor.getString(mCursor.getColumnIndex("recipe_cate"));
            cook_name = mCursor.getString(mCursor.getColumnIndex("recipe_name"));
            cook_img = mCursor.getString(mCursor.getColumnIndex("recipe_image"));

            //Log.d("dbTest", cook_name);
            if (cate_id.equals(cook_cate)) {
                item = new ListViewItem(cook_img, cook_name);
                alist.add(item);
                listViewAdapter.notifyDataSetChanged();
            }

        }

        mCursor.close();


    }

    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);
    }





}
