package com.dvsmart.timetable.alert;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


import com.dvsmart.timetable.Helper;

import com.dvsmart.timetable.ListStations;
import com.dvsmart.timetable.R;
import com.dvsmart.timetable.adapter.AdapterDir;
import com.dvsmart.timetable.konstr.Trans;


import java.util.List;
import java.util.Objects;


public class AlertDirs extends Dialog {


    private Activity activity;
    private Helper helper;
    private List<Trans> listTrans;
    private ListView lv;
    private TextView tvDir;
    private String [] path;

    public AlertDirs(Activity a, List<Trans> listTrans_, String [] path_) {
        super(a, R.style.AppTheme);
        activity = a;
        helper = new Helper(activity);
        listTrans = listTrans_;
        path = path_;
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

        lv.setAdapter(new AdapterDir(activity, listTrans,path));


        tvDir.setText(path[0] + " - "+path[1]);


        lv.setOnItemClickListener((parent, view, position, id) -> {

            TextView textView =   view.findViewById(R.id.textView3);
            String text = textView.getText().toString();

            AlertDirsInfo alert =   new AlertDirsInfo(activity,listTrans.get(position),path, text);
            alert.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationSlide;
            alert.show();

        });


        findViewById(R.id.imageView5).setOnClickListener(v -> {
            //helper.setPreference("agreement", "1");
            dismiss();
         });
//        findViewById(R.id.button).setOnClickListener(v -> {
//          activity.finish();
//        });


    }



}
