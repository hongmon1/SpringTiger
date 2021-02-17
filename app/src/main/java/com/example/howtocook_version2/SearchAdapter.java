package com.example.howtocook_version2;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflate;
    private ViewHolder viewHolder;
    private ArrayList<InfoClass> InfoList;//어뎁터에 추가된 데이터를 저장하는 list

    public SearchAdapter(Context context, ArrayList<InfoClass> list){
        this.InfoList = list;
        this.context = context;
        this.inflate = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return InfoList.size();
    }
    @Override
    public Object getItem(int i) {
        return null;
    }
    @Override
    public long getItemId(int i) { return 0; }
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        final Context context = viewGroup.getContext();

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_listview, null);
        }
        //화면에 대한 참조
        TextView textView = convertView.findViewById(R.id.label);
       // TextView textView = convertView.findViewById(R.id.listView);
        //데이터셋에서 position에 위치한 데이터 참조
        InfoClass infoClass = InfoList.get(position);

        //리스트뷰에 데이터 반영
        textView.setText(infoClass.get_name());

        return convertView;
    }
    class ViewHolder{
        public TextView label;
    }
}
