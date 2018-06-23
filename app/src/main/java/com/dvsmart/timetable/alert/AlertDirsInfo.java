package com.dvsmart.timetable.alert;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.dvsmart.timetable.Helper;
import com.dvsmart.timetable.R;
import com.dvsmart.timetable.adapter.AdapterDir;
import com.dvsmart.timetable.adapter.GridviewAdapter;
import com.dvsmart.timetable.konstr.KonstTable;
import com.dvsmart.timetable.konstr.Trans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;


public class AlertDirsInfo extends Dialog {


    private Activity activity;
    private Helper helper;
    private Trans listTrans;
    private ListView lv;
    private TextView tvDir;
    private String [] path;
    private String txt;
    public AlertDirsInfo(Activity a, Trans listTrans_, String [] path_,String txt_) {
        super(a, R.style.AppTheme);
        activity = a;
        helper = new Helper(activity);
        listTrans = listTrans_;
        path = path_;
        txt = txt_;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(Color.parseColor("#76000000")));
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        setContentView(R.layout.alert_dirs);
        tvDir = findViewById(R.id.textView2);
        lv = findViewById(R.id.listdir);
        ArrayList<KonstTable> ls = new ArrayList<>();



        tvDir.setText(txt);

        Map<String, Integer> mapDir = listTrans.getList();
        Map<String, String> mapat = listTrans.getListat();
        Map<String, String> mapto = listTrans.getListto();



        for (int i = 0; i < mapDir.size(); i++) {
            KonstTable konstTable = new KonstTable();

            konstTable.setDirection_(getKey(i));
            konstTable.setTimeDirection_(mapto.get(String.valueOf(i)));
            konstTable.setTimeTo_(mapto.get(String.valueOf(i)));
            konstTable.setTimeOff_(mapat.get(String.valueOf(i)));
            Log.i("onCreate", konstTable.getTimeOff_());


            ls.add(konstTable);

        }

        lv.setAdapter(new GridviewAdapter(activity, ls,path));

        lv.setOnItemClickListener((parent, view, position, id) -> {


            
        });


        findViewById(R.id.imageView5).setOnClickListener(v -> {
            //helper.setPreference("agreement", "1");
            dismiss();
         });
//        findViewById(R.id.button).setOnClickListener(v -> {
//          activity.finish();
//        });


    }

    private  String getKey(int i) {

        Map<String, Integer> map1 = listTrans.getList();
        Set<Map.Entry<String,Integer>> entrySet = map1.entrySet();

        String  str2= "";
        for (Map.Entry<String,Integer> pair : entrySet) {
            if (pair.getValue() == i ) {
                 str2 = pair.getKey();// нашли наше значение и возвращаем  ключ
            }
        }
        return str2;
    }
    public class AlertExit extends Dialog {
        Button btnClose, btRate;

        public AlertExit(Activity a) {
            super(a, R.style.AppTheme);
            // TODO Auto-generated constructor stub

        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            // hide notification bar
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#76000000")));
            // requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.alert_exit);
            btnClose = (Button)findViewById(R.id.button2);
            btRate =  findViewById(R.id.button3);

            TextView tvExit =  findViewById(R.id.textView15);




            btnClose.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                activity.startActivity(intent);
                activity.finish();
            });
            btRate.setOnClickListener(v -> {
                final String appPackageName = activity.getPackageName(); // getPackageName() from Context or Activity object
                try {
                    activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                    helper.setPreference("israte", "1");
                } catch (android.content.ActivityNotFoundException anfe) {
                    activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
            });

            tvExit.setOnClickListener(v -> dismiss());


        }

    }
    @Override
    public void onBackPressed() {
        new AlertExit(activity).show();
    }
}
