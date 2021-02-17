package com.example.howtocook_version2;


import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListView {

    public ListView(){}

    public List<String> list;
    public android.widget.ListView listView;
    public EditText editSearch;
    public SearchAdapter adapter;
    public ArrayList<String> arraylist;


    //csv파일 갖고오기
   // FileReader file = new FileReader(fileName);
   // BufferedReader buffer = new BufferedReader(file);
   // String line = "";
   // String tableName ="TABLE_NAME";
   // String columns = "_id, name, dt1, dt2, dt3";
   // String str1 = "INSERT INTO " + tableName + " (" + columns + ") values(";
   // String str2 = ");";


    public void search(String charText) {
        list.clear();

        // 문자 입력이 없을때는 모든 데이터를 보여준다.
        if (charText.length() == 0) {
            list.addAll(arraylist);
        }
        else {
            for(int i = 0;i < arraylist.size(); i++) {
                // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                if (arraylist.get(i).toLowerCase().contains(charText)) {
                    list.add(arraylist.get(i));
                }
            }
        }
        // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
        adapter.notifyDataSetChanged();
    }
    public void settingList(){
        list.add("apple");
        list.add("banana");
        list.add("choco");
        list.add("bread");

   /*     db.beginTransaction();
        while ((line = buffer.readLine()) != null) {
            StringBuilder sb = new StringBuilder(str1);
            String[] str = line.split(",");
            sb.append("'" + str[0] + "',");
            sb.append(str[1] + "',");
            sb.append(str[2] + "',");
            sb.append(str[3] + "'");
            sb.append(str[4] + "'");
            sb.append(str2);
            db.execSQL(sb.toString());
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        */
    }
}

