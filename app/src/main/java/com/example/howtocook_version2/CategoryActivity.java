package com.example.howtocook_version2;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    Button home_btn, fav_btn, myrep_btn;

    private DBOpenHelper mDbOpenHelper;
    private Cursor mCursor;
    private Cursor vCursor;

    private String recipe_id;
    private String cook_img;
    private String cook_name;
    private String cook_id;


    private String wb_name;

    private ArrayList<ListViewItem> alist;
    private ListViewAdapter listViewAdapter;
    private ListView listView;
    private ListViewItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        //action bar 없애기
        getSupportActionBar().hide();
/*
        //하단 탭
        fav_btn.setEnabled(false);
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
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
*/
        Intent intent = getIntent();
        wb_name = intent.getExtras().getString("name");

        alist = new ArrayList<ListViewItem>();

        mDbOpenHelper = new DBOpenHelper(this);
        try {
            mDbOpenHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
     //   mDbOpenHelper.insertColumn("abc", "aa","bb","cc","dd","분류1");
     //   mDbOpenHelper.insertColumn("cde", "aa","bb","cc","dd","분류1");
     //   mDbOpenHelper.insertColumn("fga", "aa","bb","cc","dd","분류1");


        listViewAdapter = new ListViewAdapter(this,alist);
        listView = findViewById(R.id.listview);
        listView.setAdapter(listViewAdapter);

        mCursor = null;
        mCursor = mDbOpenHelper.getRepAllColumns();

        while(mCursor.moveToNext()){
            if(mCursor.getString(mCursor.getColumnIndex("recipe_cate")).equals(wb_name)){
                cook_img = mCursor.getString(mCursor.getColumnIndex("recipe_image"));
                cook_name = mCursor.getString(mCursor.getColumnIndex("recipe_name"));
                item = new ListViewItem(cook_img, cook_name);

                alist.add(item);
                listViewAdapter.notifyDataSetChanged();
            }
        }
        mCursor.close();


/*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mCursor = mDbOpenHelper.getFavAllColumns();
                mCursor.moveToPosition(i);

                String idRecipe = mCursor.getString(mCursor.getColumnIndex("recipe_id"));
                //long idRecipe_int = Integer.parseInt(idRecipe);
                mCursor.close();
                Intent it = new Intent(getApplicationContext(), MyRecipeActivity.class);   // 인텐트 처리

                it.putExtra("it_idRecipe", idRecipe);
                //Log.d("","idRedcipe : "+idRecipe);
                startActivity(it);
            }
        });

*/
    }
}
