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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MyRecipeListActivity extends AppCompatActivity {

    private Button home_btn, fav_btn, myrep_btn;

    private ArrayList<ListViewItem> alist;
    private ListViewAdapter listViewAdapter;
    private ListView listView;
    private DBOpenHelper mDbOpenHelper;
    private Cursor mCursor;

    private String cook_img;
    private String cook_name;

    private ListViewItem item;
    private String intent_name;

    FloatingActionButton myrec_add_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_my_recipe_list);

        getSupportActionBar().hide();

        home_btn = findViewById(R.id.home_btn);
        fav_btn = findViewById(R.id.fav_btn);
        myrep_btn = findViewById(R.id.myrep_btn);

        alist = new ArrayList<ListViewItem>();
        listViewAdapter = new ListViewAdapter(this,alist);
        listView = findViewById(R.id.listview);
        listView.setAdapter(listViewAdapter);

        mDbOpenHelper = new DBOpenHelper(this);
        mDbOpenHelper.open();

        //하단 탭 버튼 세팅
        myrep_btn.setEnabled(false);
        fav_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),FavoriteActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_to_left,R.anim.left_to_right);

            }
        });
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_to_left,R.anim.left_to_right);

            }
        });

        myrec_add_btn = findViewById(R.id.myrec_add_btn);
        //클릭시 MyrecipeRegisterctivity로 전환
        myrec_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyRecipeRegisterActivity.class);
                //Intent intent = new Intent(getApplicationContext(),TestActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);
            }
        });

        //임시데이터. 나중에 지워주세요
        //mDbOpenHelper.insertColumn("a","a","a","a");
        //mDbOpenHelper.insertColumn("b","a","a","a");
        //mDbOpenHelper.insertColumn("c","a","a","a");

        mCursor = null;
        mCursor = mDbOpenHelper.getMyrepAllColumns();
        Log.d("dbTest", "Count = " + mCursor.getCount());

        //리스트뷰에 myrecipe table 내용 내보내기
        while(mCursor.moveToNext()){

            cook_name = mCursor.getString(mCursor.getColumnIndex("myrecipe_name"));
            cook_img = mCursor.getString(mCursor.getColumnIndex("myrecipe_image"));

            Log.d("dbTest", cook_name);

            item = new ListViewItem(cook_img,cook_name);
            alist.add(item);
            listViewAdapter.notifyDataSetChanged();
        }

        mCursor.close();

        //꾹 눌렀을 때 삭제하기
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                showDeleteDialog(i);
                return true;
            }
        });

        //클릭 시 MyRecipeActivity로 전환
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    moveToRecipe(i);
            }
        });
    }

    protected void onPause(){
        super.onPause();
        overridePendingTransition(R.anim.right_to_left,R.anim.left_to_right);
    }

    //뒤로가기 시 업데이트 필요?
    protected void onResume(){
        super.onResume();
        alist.clear();
        mCursor = null;
        mCursor = mDbOpenHelper.getMyrepAllColumns();
        Log.d("dbTest", "Count = " + mCursor.getCount());

        //리스트뷰에 myrecipe table 내용 내보내기
        while(mCursor.moveToNext()){

            cook_name = mCursor.getString(mCursor.getColumnIndex("myrecipe_name"));
            cook_img = mCursor.getString(mCursor.getColumnIndex("myrecipe_image"));

            Log.d("dbTest", cook_name);

            item = new ListViewItem(cook_img,cook_name);
            alist.add(item);
            listViewAdapter.notifyDataSetChanged();
        }

        mCursor.close();
    }

    //삭제 다이얼로그
    private void showDeleteDialog(int index){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("삭제");
        builder.setMessage("레시피를 삭제하시겠습니까?");
        final int rep_index = index;

        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mCursor = mDbOpenHelper.getMyrepAllColumns();
                        //리스트뷰 위치로 Cursor 이동
                        mCursor.moveToPosition(rep_index);

                        String delete = mCursor.getString(1);

                        //name을 이용해서 지우기 - id는 밀려나기 때문에
                        boolean result = mDbOpenHelper.deleteMyrepColumn(delete);

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

    public void moveToRecipe(int i){

        //name으로 검색하기 위해 해당 위치 레시피 이름 검색
        mCursor = mDbOpenHelper.getMyrepAllColumns();
        //리스트뷰 위치로 Cursor 이동
        mCursor.moveToPosition(i);

        intent_name = mCursor.getString(mCursor.getColumnIndex("myrecipe_name"));

        mCursor.close();

        //intent에 넘겨줌
        Intent intent = new Intent(getApplicationContext(), MyRecipeActivity.class);
        intent.putExtra("name",intent_name);
        Log.d("test",intent_name);
        startActivity(intent);
    }

}
