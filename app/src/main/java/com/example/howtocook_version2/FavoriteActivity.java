package com.example.howtocook_version2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {
    Button home_btn, fav_btn, myrep_btn;


    private DBOpenHelper mDbOpenHelper;
    private Cursor mCursor;
    private Cursor vCursor;

    private String recipe_id;
    private String cook_img;
    private String cook_name;
    private String cook_id;

    private ArrayList<ListViewItem> alist;
    private ListViewAdapter listViewAdapter;
    private ListView listView;
    private ListViewItem item;

    private static final String TAG1 = "TestDataBase";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        //action bar 없애기
        getSupportActionBar().hide();


        home_btn = findViewById(R.id.home_btn);
        fav_btn = findViewById(R.id.fav_btn);
        myrep_btn = findViewById(R.id.myrep_btn);

        //하단 탭 버튼 세팅
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


        mDbOpenHelper = new DBOpenHelper(this);
        mDbOpenHelper.open();
        // favorite_recipe_id 가져오기
        //String name, String ingre, String desc, String com, String image
        mDbOpenHelper.insertColumn("떡볶이","떡","1.떡을 삶는다","comment","https://recipe1.ezmember.co.kr/cache/recipe/2016/06/08/24c312f82313faaf1e4d5ef98761efcb1.jpg");


        alist = new ArrayList<ListViewItem>();
        listViewAdapter = new ListViewAdapter(this,alist);
        listView = findViewById(R.id.listview);
        listView.setAdapter(listViewAdapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                showDeleteDialog(i);
                return true;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                mCursor = mDbOpenHelper.getFavAllColumns();
                mCursor.moveToPosition(i);

                String idRecipe = mCursor.getString(mCursor.getColumnIndex("recipe_id"));
                //long idRecipe_int = Integer.parseInt(idRecipe);
                mCursor.close();
                Intent it = new Intent(getApplicationContext(), FavoriteContentActivity.class);   // 인텐트 처리

                it.putExtra("it_idRecipe", idRecipe);
                //Log.d("","idRedcipe : "+idRecipe);
                startActivity(it);
            }
        });




        /*
        for(int i = 0 ; i <3; i++) {
            mDbOpenHelper.insertColumn(i);
        }
        //mDbOpenHelper.deleteFavColumn(1);
*/
        mCursor = null;
        mCursor = mDbOpenHelper.getFavAllColumns();
        Log.d("dbTest", "fav Count = " + mCursor.getCount());

        while(mCursor.moveToNext()){
            recipe_id = mCursor.getString(mCursor.getColumnIndex("recipe_id"));
            //Log.d("dbTest", recipe_id);
            vCursor = null;
            vCursor = mDbOpenHelper.getRepAllColumns();

            while(vCursor.moveToNext()){

                cook_id = vCursor.getString(vCursor.getColumnIndex("_id"));
                cook_name = vCursor.getString(vCursor.getColumnIndex("recipe_name"));
                cook_img = vCursor.getString(vCursor.getColumnIndex("recipe_image"));

                //Log.d("dbTest", cook_name);
                if(cook_id.equals(recipe_id)) {
                    item = new ListViewItem(cook_img, cook_name);
                    alist.add(item);
                    listViewAdapter.notifyDataSetChanged();
                }
            }
            vCursor.close();
        }

        mCursor.close();


    }

    protected void onPause(){
        super.onPause();
        overridePendingTransition(R.anim.right_to_left,R.anim.left_to_right);
    }

    private void showDeleteDialog(int index){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("삭제");
        builder.setMessage("레시피를 삭제하시겠습니까?");
        final int rep_index = index;

        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mCursor = mDbOpenHelper.getFavAllColumns();
                        mCursor.moveToPosition(rep_index);
                        //delete db 하기

                        String delete = mCursor.getString(0);
                        long delete_int = Integer.parseInt(delete);
                        boolean result = mDbOpenHelper.deleteFavColumn(delete_int);

                        if(result){
                            alist.remove(rep_index);
                            listViewAdapter.notifyDataSetChanged();
                            Toast.makeText(getApplicationContext(), "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                        }

                        mCursor.close();


                    }
                });
        builder.setNegativeButton("아니오",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),"삭제 취소",Toast.LENGTH_SHORT).show();
                    }
                }
        );

        builder.show();
    }

}
