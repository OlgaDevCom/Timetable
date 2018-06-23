package com.dvsmart.timetable.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dvsmart.timetable.R;
import com.dvsmart.timetable.konstr.Trans;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AdapterDir extends BaseAdapter {
    private List<Trans> listProduct;
    private Activity activity;
    private String [] titles;
    public AdapterDir(Activity activity, List<Trans> resList_, String [] titles_) {
        super();
        listProduct = resList_;
        titles = titles_;
        this.activity = activity;

       // Log.i("poipoi", "AdapterDaySt: "+titles);

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        //Log.i("poipoi", "AdapterDayStasdf: "+listProduct.size());
        return listProduct.size();
    }

    @Override
    public String getItem(int position) {
        // TODO Auto-generated method stub
        return listProduct.get(position).getUrl();
    }



    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public static class ViewHolder
    {
        public TextView titl;
        public TextView time1;
        public TextView time2;
        public TextView dir1;
        public TextView dir2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder view;
        LayoutInflater inflator = activity.getLayoutInflater();
        if(convertView==null)
        {
            view = new ViewHolder();
            convertView = inflator.inflate(R.layout.row_dir, null);

             view.time1 = convertView.findViewById(R.id.textView8);
             view.time2 = convertView.findViewById(R.id.textView9);
             view.dir1 = convertView.findViewById(R.id.textView4);
             view.dir2 = convertView.findViewById(R.id.textView5);
             view.titl = convertView.findViewById(R.id.textView3);

            // view.txtResult = (TextView) convertView.findViewById(R.id.textView323);
            convertView.setTag(view);
        }else
        {
            view = (ViewHolder) convertView.getTag();
        }




        Trans trans = listProduct.get(position);
       // String path = trans.getList().
        view.titl.setText(getKey(trans));
          view.dir1.setText(titles[0]);
          view.dir2.setText(titles[1]);
        Map<String, String> mapat = listProduct.get(position).getListat();
        Map<String, String> mapto = listProduct.get(position).getListto();

        Log.i("sdfsdf", titles[0]);
        Log.i("sdfsdf", titles[1]);
        Log.i("sdfsdf", String.valueOf(trans.getList().size()));


        int id = trans.getList().get(titles[0]);
        int id2 = trans.getList().get(titles[1]);
//        Log.i("getView1", String.valueOf(id));
//        Log.i("getView1", String.valueOf(id2));
//        String idat = mapat.get(id);
//        String idto =   mapto.get(id2);

//        Log.i("getView22", mapat.toString());
//        Log.i("getView22", mapto.toString());
//        Log.i("getView", mapat.get(String.valueOf(id)));
//        Log.i("getView", mapto.get(String.valueOf(id2)));

          view.time1.setText(mapto.get(String.valueOf(id)));
          view.time2.setText(mapat.get(String.valueOf(id2)));

//        view.date.setText(listProduct.get(position).getDate());


        return convertView;
    }
private  String getKey(Trans trans) {

    Map<String, Integer> map1 = trans.getList();
    Set<Map.Entry<String,Integer>> entrySet = map1.entrySet();
    final int size = map1.size() -1;
   // Log.i("getKey2", String.valueOf(size));

    String  str1 = "", str2= "";
    for (Map.Entry<String,Integer> pair : entrySet) {
       // Log.i("getKey", String.valueOf(pair.getValue()));
        if (pair.getValue() == size) {
            str2 = pair.getKey();// нашли наше значение и возвращаем  ключ
        }
    }
    for (Map.Entry<String,Integer> pair : entrySet) {
       // Log.i("getKey", String.valueOf(pair.getValue()));

        if (pair.getValue() == 0) {
            str1 = pair.getKey();// нашли наше значение и возвращаем  ключ
        }
    }
 return str1+ " - "+str2;
}
}