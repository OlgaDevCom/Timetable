package com.dvsmart.timetable;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.dvsmart.timetable.alert.AlertDirs;
import com.dvsmart.timetable.db.Stations;
import com.dvsmart.timetable.konstr.DirTrans;
import com.dvsmart.timetable.konstr.KonstrStation;
import com.dvsmart.timetable.konstr.Trans;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.common.reflect.TypeToken;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import io.fabric.sdk.android.Fabric;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ListStations extends AppCompatActivity {

    private List<Trans> listTrans = new ArrayList<>();
    private List<Trans> listFiltered = new ArrayList<>();

    private List<Stations> titles = new ArrayList<>();
    private List<String> arr = new ArrayList<>();
    private List<String> secondList = new ArrayList<>();

    private FirebaseFirestore db;
    private AutoCompleteTextView autoCompleteTextView ,autoCompleteTextView2;
    private ProgressBar bar;
    private Helper helper;
    private ImageView rem1, rem2;
    private ImageButton imrev;
    private Button btFind;
    private String [] search = {"",""};
    private boolean isLoad = false;
    private com.google.android.gms.ads.AdRequest adRequest;
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_list_stations);
        helper = new Helper(this);

        final Fabric fabric = new Fabric.Builder(this)
                .kits(new Crashlytics())
                .debuggable(true)
                .build();
        Fabric.with(fabric);


        autoCompleteTextView = findViewById(R.id.editText);
        autoCompleteTextView2 = findViewById(R.id.editText3);
        rem1 = findViewById(R.id.imageView2);
        rem2 = findViewById(R.id.imageView3);
        imrev = findViewById(R.id.imageView4);
        bar = findViewById(R.id.progressBar);
        btFind = findViewById(R.id.button2);
        db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .setPersistenceEnabled(true)

                .build();
        db.setFirestoreSettings(settings);

        String g = loadJSONFromAsset(ListStations.this);

        Type listType = new TypeToken<ArrayList<Stations>>(){}.getType();
        titles = new Gson().fromJson(g, listType);
        Log.i("odfdfs", String.valueOf(titles.size()));

        for (int i = 0; i < titles.size(); i++) {
            arr.add(titles.get(i).getStation());
        }

        //String[] array = yourClassList.toArray(new String[yourClassList.size()]);
        String[] myArray  = arr.toArray(new String[arr.size()]);


        Log.i("odfdfs", String.valueOf(myArray.length));
        autoCompleteTextView.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, myArray));
        autoCompleteTextView2.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, myArray));

        if(!helper.getPreference("first").equals("0"))
        {
            autoCompleteTextView.setText(helper.getPreference("first"));
            search[0] = helper.getPreference("first");
        }
        if(!helper.getPreference("second").equals("0"))
        {
            autoCompleteTextView2.setText(helper.getPreference("second"));
            search[1] = helper.getPreference("second");
        }
        autoCompleteTextView.dismissDropDown();

        autoCompleteTextView.setOnItemClickListener((parent, view, position, id) -> {
            Log.i("autoCompleteTextView", String.valueOf(position));
            autoCompleteTextView2.requestFocus();
        });

        rem1.setOnClickListener(v -> autoCompleteTextView.setText(""));
        rem2.setOnClickListener(v -> autoCompleteTextView2.setText(""));


        imrev.setOnClickListener(v -> {
            String a1 = autoCompleteTextView.getText().toString();
            String a2 = autoCompleteTextView2.getText().toString();
            autoCompleteTextView2.setText(a1);
            autoCompleteTextView.setText(a2);
            autoCompleteTextView.dismissDropDown();
        });

        btFind.setOnClickListener(v -> {

             if(search[0].length() > 0)
             {
                 if(search[1].length() > 0)
                 {
                     getDataFromFire();
                 }else
                 {
                     Toast.makeText(this, "Выберите маршрут", Toast.LENGTH_LONG).show();
                 }
             }else
             {
                 Toast.makeText(this, "Выберите маршрут", Toast.LENGTH_LONG).show();
             }


        });









        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String j = s.toString();
                 if(j.length() > 0)
                {
                    Log.i("lfiolsd", j);
                    int f = arr.indexOf(j);
                     if(f != -1)
                     {
                         Log.i("titlessdsd", arr.get(f));
                         helper.setPreference("first", arr.get(f));
                         search[0] = arr.get(f);
                        // getDataFromFire(arr.get(f), j2);
                     }
                }else
                {
                    helper.setPreference("first", "0");

                }

            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        autoCompleteTextView2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                 String j = s.toString();
                 if(j.length() > 0)
                {
                    int f = arr.indexOf(j);
                    if(f != -1)
                    {
                        Log.i("titlessdsd", arr.get(f));
                        helper.setPreference("second", arr.get(f));
                        search[1] = arr.get(f);
                    }
                }else
                {
                    helper.setPreference("first", "0");
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }



     private void FindPath(){

     listFiltered.clear();
         for (int i = 0; i < listTrans.size(); i++) {

             Map<String, Integer> map = listTrans.get(i).getList();

             int g = (map.get(search[0]) != null) ? map.get(search[0]) : 0;
             int g2 = (map.get(search[1]) != null) ? map.get(search[1]) : 0;
            // Log.i("FindPath", g + "  "+g2);

             if(g < g2)
             {
               listFiltered.add(listTrans.get(i));
             }
         }
         Log.i("FindPath", String.valueOf(listFiltered.size()));

        if(!listFiltered.isEmpty())
        {
            AlertDirs alert =   new AlertDirs(ListStations.this,listFiltered, search);
            alert.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationSlide;
            alert.show();

            alert.setOnDismissListener(dialog -> {
                displayInterstitial();
            });
        }else
        {
            Toast.makeText(this, "Маршрутов не найдено", Toast.LENGTH_LONG).show();
        }

     }


     private void loadAds()
     {

         interstitialAd = new InterstitialAd(this);
         if(helper.getPreference("ads").equals("0"))
         {
             interstitialAd.setAdUnitId(getResources().getString(R.string.vetall));
             helper.setPreference("ads","1");
             Log.i("adsset", "onCreate: 1");
         }else
         {
             interstitialAd.setAdUnitId(getResources().getString(R.string.stas));
             helper.setPreference("ads","0");
             Log.i("adsset", "onCreate: 0");
         }
         adRequest = new com.google.android.gms.ads.AdRequest.Builder()
                 .build();
         interstitialAd.setAdListener(new AdListener() {
             @Override
             public void onAdLoaded() {
                 Log.i("onAdLoaded", "onAdLoaded: ");
             }

             @Override
             public void onAdFailedToLoad(int errorCode) {
                  Log.i("onAdLoaded", "onAdFailedToLoad: ");

             }
         });
         interstitialAd.loadAd(adRequest);
     }


    public void displayInterstitial() {
        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        loadAds();
    }

    private void ReloadSecondData()
    {
//        secondList.clear();
//        for (int i = 0; i < listTrans.size(); i++) {
//
//            Map<String, Integer> f = listTrans.get(i).getList();
//
//            for(String key : f.keySet()){
//
//                if(secondList.indexOf(key) == -1)
//                {
//                    secondList.add(key);
//                }
//            }
//        }
//        String[] myArray  = secondList.toArray(new String[secondList.size()]);
//
//        autoCompleteTextView2.setAdapter(new ArrayAdapter<>(this,
//                android.R.layout.simple_dropdown_item_1line, myArray));
        FindPath();
    }

    private void getDataFromFire()
    {
        if(!isLoad)
        {
            isLoad = true;
            bar.setVisibility(View.VISIBLE);

            Log.i("ksjdfsd", search[0]);
            Log.i("ksjdfsd",  search[1]);

            db.collection("poezd")
                    .whereGreaterThanOrEqualTo("list."+search[0], -1)
                    // .whereEqualTo("url", "/raspisanie-elektrichki/6696--krasnograd--harkov-pass/")

                    .get()
                    .addOnCompleteListener(task -> {

                        Log.i("addOnsdeListener", String.valueOf(task.getResult().getMetadata().isFromCache()));
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //
//                            Log.d("fdfgdf", document.getId() + " => " + document.toObject(Trans.class).getListto());
//                            Log.d("fdfgdf", document.getId() + " => " + document.toObject(Trans.class).getListat());
//
//                            Log.d("fdfgdf", document.getId() + " => " + document.toObject(Trans.class).getList());

                                if(document.toObject(Trans.class).getDir() != null)
                                {
                                    listTrans.add(document.toObject(Trans.class));
                                    //Log.i("getDataFromFiresd", String.valueOf(listTrans.get(listTrans.size()-1).getList()));
                                }

                            }
                        } else {
                            Log.d("fdfgdf", "Error getting documents: ", task.getException());
                        }
                        bar.setVisibility(View.INVISIBLE);
                        ReloadSecondData();
                        isLoad = false;
                    });
        }

    }

    public String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("stations.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
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
            btnClose =  findViewById(R.id.button2);
            btRate =  findViewById(R.id.button3);

            TextView tvExit =  findViewById(R.id.textView15);


            findViewById(R.id.textView15).setOnClickListener(v -> dismiss());



            btnClose.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            });
            btRate.setOnClickListener(v -> {
                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                    helper.setPreference("israte", "1");
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
            });

            tvExit.setOnClickListener(v -> dismiss());

        }

    }
    @Override
    public void onBackPressed() {

        new AlertExit(this).show();
    }
}
