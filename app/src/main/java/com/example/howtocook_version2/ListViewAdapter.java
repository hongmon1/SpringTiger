package com.example.howtocook_version2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

//https://recipes4dev.tistory.com/43
public class ListViewAdapter extends BaseAdapter {

    //adapter에 추가된 데이터를 저장하기 위한 arraylist
    private ArrayList<ListViewItem> listViewItemArrayList = new ArrayList<ListViewItem>();

    public ListViewAdapter(Context context, ArrayList<ListViewItem> listViewItemArrayList){
        this.listViewItemArrayList =listViewItemArrayList;
    }

    //adapter에 사용되는 데이터 개수 리턴 - 필수 구현
    @Override
    public int getCount(){
        return listViewItemArrayList.size();
    }

    //position에 위치한 데이터 화면에 출력하는데 사용될 View 리턴 - 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final int pos = position;
        final Context context = parent.getContext();

        //listview item layout을 inflate, converView 참조 획득
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item,parent,false);
        }

        //화면에 표시될 View로부터 위젯에 대한 참조 획득
        ImageView imageView = convertView.findViewById(R.id.cook_img);

        TextView textView = convertView.findViewById(R.id.cook_name);

        //data set에서 position에 위치한 데이터 참조 획득
        ListViewItem listViewItem = listViewItemArrayList.get(position);

        //아이템 내 각 위젯에 데이터 반영
        imageView.setImageBitmap(StringToBitmap(listViewItem.getImg()));
        textView.setText(listViewItem.getName());

        return convertView;

    }
    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. - 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 - 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemArrayList.get(position) ;
    }

    //


    public static Bitmap StringToBitmap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}
