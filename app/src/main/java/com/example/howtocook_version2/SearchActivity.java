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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private EditText editText;
    private ListView listView;

    //데이터를 넣은 리스트변수
    private ArrayList<InfoClass> arrlist;
    private ArrayList<InfoClass> arrlist2;
   // private ArrayList<String> strlist;
   // private ArrayList<String> strlist2;

    private String id;
    private String name;
    private InfoClass item;

    private SearchAdapter adapter;
    private DBOpenHelper mDbOpenHelper;
    private Cursor mCursor;
    private List idList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        //action bar 없애기
        getSupportActionBar().hide();


        //리스트 생성
        arrlist = new ArrayList<InfoClass>();


      //  final String[] strlist = arrlist.toArray(new String[arrlist.size()]);
        adapter = new SearchAdapter(this,arrlist);
        editText = findViewById(R.id.editText);
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        mDbOpenHelper = new DBOpenHelper(this);
        mDbOpenHelper.open();

       // mDbOpenHelper.insertColumn("apple", "aa","bb","cc","dd");
       // mDbOpenHelper.insertColumn("bread", "ee","ff","gg","hh");



        doWhileCursorToArray();


        //  strlist = new ArrayList<String>();
        //리스트의 데이터를 2리스트에 복사(복사본 생성)
        arrlist2 = new ArrayList<InfoClass>();
        //strlist2 = new ArrayList<String>();
        arrlist2.addAll(arrlist);
        //strlist2.addAll(strlist);

        //검색어를 입력시 이벤트 리스너
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) {
                Log.i("tag","log0");
                String text = editText.getText().toString();
                search(text);
            }
        });

        //리스트뷰에서 셀 클릭 시 레시피로 화면 전환
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mCursor = mDbOpenHelper.getRepAllColumns();
                mCursor.moveToPosition(i);

                String idRecipe = mCursor.getString(mCursor.getColumnIndex("_id"));
                //long idRecipe_int = Integer.parseInt(idRecipe);
                mCursor.close();
                Intent it = new Intent(getApplicationContext(), SearchContentActivity.class);   // 인텐트 처리

                it.putExtra("it_idRecipe", idRecipe);
                //Log.d("","idRedcipe : "+idRecipe);
                startActivity(it);
            }
        });
    }

    // 검색을 수행하는 메소드 원래 인자 String charText
    private void search(String charText) {
        // 문자 입력시마다 리스트를 지우고 새로 뿌려준다.
        arrlist.clear();
        Log.i("tag","log");
        // 문자 입력이 없을때는 모든 데이터를 보여준다.
       if (charText.length() == 0) {
           Log.i("tag","log2");
           arrlist.addAll(arrlist2);
       }
        // 문자 입력을 할때..
        else
        {
            // 리스트의 모든 데이터를 검색한다.
            for(int i = 0;i < arrlist2.size(); i++) {
                Log.i("tag","log3");
                // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                //if (arrlist.get(i).toLowerCase().contains(charText))
                if (arrlist2.get(i).name.contains(charText)) {          //@
                    // 검색된 데이터를 리스트에 추가한다.
                   // Log.d("tag","log");
                    arrlist.add(arrlist2.get(i));
                }
            }
        }
        // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
        adapter.notifyDataSetChanged();
    }

    private void doWhileCursorToArray(){
        mCursor = null;
        //DB에 있는 모든 컬럼을 가져옴
        mCursor = mDbOpenHelper.getRepAllColumns();

        while (mCursor.moveToNext()) {
            //InfoClass에 입력된 값을 압력
            InfoClass info = new InfoClass();

            id = mCursor.getString(mCursor.getColumnIndex("_id"));
            int ID = Integer.parseInt(id);
            name = mCursor.getString(mCursor.getColumnIndex("recipe_name"));
            item = new InfoClass(ID, name);
            item = new InfoClass(name);

            arrlist.add(item);

           // Log.d(TAG, mCursor.getString(mCursor.getColumnIndex("recipe_name")));
            //arrlist.add(mCursor.getString(mCursor.getColumnIndex("recipe_name"))); //원래 이거
        }
        mCursor.close();
    }

}
