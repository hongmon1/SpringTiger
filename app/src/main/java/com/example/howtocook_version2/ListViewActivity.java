package com.example.howtocook_version2;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {
    private EditText editText;
    private List<String> list;
    private android.widget.ListView listView;
    private SearchAdapter adapter;
    private ArrayList<String> arraylist;

    private DBOpenHelper mDbOpenHelper;
    private Cursor mCursor;
    private static final String TAG = "db";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        //action bar 없애기
        getSupportActionBar().hide();

        editText = findViewById(R.id.editText);

        list = new ArrayList<String>();
        arraylist = new ArrayList<String>();
        arraylist.addAll(list);
        adapter = new com.example.howtocook_version2.SearchAdapter(list, this);
        listView.setAdapter(adapter);

        //임시데이터
        settingList();



        //  doWhileCursorToArray();     //db값이 들어간 리스트

        mDbOpenHelper = new DBOpenHelper(this);
        try {
            mDbOpenHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // input창에 검색어를 입력시 "addTextChangedListener" 이벤트 리스너를 정의
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) {
                String text = editText.getText().toString();
                search(text);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListViewActivity.this, FavoriteActivity.class);
                //     it.putExtra("it_listData", data[position]);   어떤 값이 선택되었는지 알려줘야한다
                startActivity(intent);
            }
        });
    }

    // 검색을 수행하는 메소드
    private void search(String charText) {
        // 문자 입력시마다 리스트를 지우고 새로 뿌려준다.
        list.clear();

        // 문자 입력이 없을때는 모든 데이터를 보여준다.
        if (charText.length() == 0) {
            list.addAll(arraylist);
        }
        // 문자 입력을 할때..
        else
        {
            // 리스트의 모든 데이터를 검색한다.
            for(int i = 0;i < arraylist.size(); i++)
            {
                // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                if (arraylist.get(i).toLowerCase().contains(charText))
                {
                    // 검색된 데이터를 리스트에 추가한다.
                    list.add(arraylist.get(i));
                }
            }
        }
        // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
        adapter.notifyDataSetChanged();
    }
/*
    private void doWhileCursorToArray(){
        mCursor = null;
        //DB에 있는 모든 컬럼을 가져옴
        mCursor = mDbOpenHelper.getMyrepAllColumns();

        while (mCursor.moveToNext()) {
            //InfoClass에 입력된 값을 압력
            Log.d(TAG, mCursor.getString(mCursor.getColumnIndex("recipe_name")));
            list.add(mCursor.getString(mCursor.getColumnIndex("recipe_name"))); //원래 이거
        }
        mCursor.close();
    }

*/

    private void settingList(){
        list.add("apple");
        list.add("banana");
        list.add("choco");
        list.add("bread");
    }
}
