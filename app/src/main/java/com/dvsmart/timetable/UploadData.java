package com.dvsmart.timetable;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.dvsmart.timetable.db.ParametrsDB;
import com.dvsmart.timetable.db.Stations;
import com.dvsmart.timetable.db.StationsDB;
import com.dvsmart.timetable.konstr.DirTrans;
import com.dvsmart.timetable.konstr.KonstrByDir;
import com.dvsmart.timetable.konstr.KonstrStation;
import com.dvsmart.timetable.konstr.Trans;
import com.google.common.reflect.TypeToken;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.SetOptions;
import com.google.gson.Gson;
import com.opencsv.CSVReader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UploadData extends AppCompatActivity {
     List<String[]> list2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_data);
        Log.i("exepfghfg", "doInBackground: ");

        //getLastProduct2();



        // load stations
//        String next[] = {};
//        try {
//            CSVReader reader = new CSVReader(new InputStreamReader(getAssets().open("traince_5.csv")));//Specify asset file name
//            //in open();
//            for(;;) {
//                next = reader.readNext();
//                if(next != null) {
//                    list2.add(next);
//                } else {
//                    break;
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Log.i("poiert", "onCreate: "+list2.size());
        //getLastProduct();
    }

//    public void getLastProduct2() {
//        Log.e("startTAG", "Start");
//
//        class GetDataJSON extends AsyncTask<Void, Integer, Void> {
//
//
//
//            @SuppressWarnings("deprecation")
//            @Override
//            protected Void doInBackground(Void... params) {
//                FirebaseFirestore db = FirebaseFirestore.getInstance();
//                 FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
//                        .setTimestampsInSnapshotsEnabled(true)
//                        .build();
//                db.setFirestoreSettings(settings);
//                StationsDB stationsDB = StationsDB.getInstance(UploadData.this);
//
////                final ParametrsDB db = RoomAsset.databaseBuilder(
////                        getApplicationContext(), ParametrsDB.class, "myDBtraince.db").build();
//                String g = loadJSONFromAsset(UploadData.this);
//
//                //DirTrans dirTrans = new DirTrans();
//                Trans trans = new Trans();
//                Type listType = new TypeToken<ArrayList<KonstrStation>>(){}.getType();
//                List<KonstrStation> yourClassList = new Gson().fromJson(g, listType);
//
//                Log.i("exesi", "doInBackground: "+yourClassList.size());
//
//                for (int i = 0; i < yourClassList.size(); i++) {
//                    Log.i("exep i", "doInBackground: "+i);
//
//                    Document doc = null;
//                    try {
//                        doc = Jsoup
//                                .connect(
//                                        "http://poezdato.net"+yourClassList.get(i).getUrl())
//                                .userAgent(
//                                        "Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6").timeout(36000)
//                                .get();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//
//                    Elements imports = doc.select("div[class=table_shadow]");
//                    Elements season = imports.select("td");
//                    Elements urlImp = season.select("a[href]");
//                    Elements calend = doc.select("div[class=calendars_outer]");
//                    Elements calend2 = calend.select("div[class=ui-datepicker-title]");
//                    Elements calend3 = calend.select("table[class=ui-datepicker-calendar, month_frequency]");
//                    Elements calend4 = calend2.select("span[class=ui-state-default ui-state-highlight ui-state-active]");
//                    Elements calend5 = calend2.select("td");
//                    Elements title = doc.select("h1[class=train_schedule]");
//
//
//
//
//
//                    String number_ = "", direction_ = "", timeTo_ = "", timeOff_ = "";
//                    List<Map<String, Object>> allList = new ArrayList<>();
//                    // Log.i("poiywpare", "doInBackground: "+imports);
//
//                    int ind = 0;
//                    int count = 0;
//
//                    Map<String, Integer> lsdata = new HashMap<>();
//                     Map <String, String> timeAt = new HashMap<>();
//                     Map <String , String > timeTo = new HashMap<>();
//                    Log.i("seasonsize", String.valueOf(season.size()/5));
//                    KonstrByDir konstrByDir = new KonstrByDir();
//                    List<String> titleStation = new ArrayList<>();
//
//                    for (Element src : calend5) {
//                       Log.i("calendsdf", src.toString());
////                        Log.i("calendsdf", src.tagName());
//
//
//                    }
//
//                    String titleStr = "";
//                    for (Element src : title) {
//                        Log.i("titledsdf", src.text());
//                        trans.setDir(src.text());
//                    }
//
//
//
//
//
//                    for (Element src : season) {
//
//
//                        switch (ind) {
//                            case 0:
//                                String tlt = src.text().replace(".", ",");
//                                titleStation.add(tlt);
//                                lsdata.put(tlt, count);
//                                stationsDB.listParametrs().insetrStat(new Stations(tlt));
//
//                             //   konstrByDir.setStation(src.text());
//                                ind++;
//                                break;
//                            case 1:
//                              //  lsdata.put("coming", src.text());
//                                //konstrByDir.setComing(src.text());
//                                 timeAt.put(String.valueOf(count), src.text());
//                                 ind++;
//                                break;
//                            case 2:
//                               // lsdata.put("parking", src.text());
//                                //konstrByDir.setParking(src.text());
//                                ind++;
//                                break;
//                            case 3:
//                              //  lsdata.put("departure", src.text());
//                               // konstrByDir.setDeparture(src.text());
//                                timeTo.put( String.valueOf(count), src.text());
//                                ind++;
//                                break;
//                            case 4:
//
//                                trans.setUrl(yourClassList.get(i).url);
//                                ind = 0;
//                                count++;
//
//                                 break;
//                            default:
//                        }
//                    }
//                     String[] array = titleStation.toArray(new String[titleStation.size()]);
//                    Log.i("ludfsd", String.valueOf(array.length));
//                    trans.setList(lsdata);
////                    trans.setListat(timeAt);
////                    trans.setListto(timeTo);
//
//                    //trans.setList_time_at(timeAt);
//
//                   // Log.i("123123", String.valueOf(trans.getStation().size()));
//                    Log.i("123123", trans.getUrl());
//
//
//                    db.collection("poezd").document()
//                            .set(trans, SetOptions.merge());
//
//
//
//
//                }
//                return null;
//            }
//
//            @Override
//            protected void onProgressUpdate(Integer... values) {
//                super.onProgressUpdate(values);
//            }
//
//            @Override
//            protected void onPostExecute(Void r) {
//
//
//            }
//        }
//        GetDataJSON g = new GetDataJSON();
//        g.execute();
//    }

//    public void getLastProduct() {
//        Log.e("startTAG", "Start");
//        final String[] result = {null};
//
//        class GetDataJSON extends AsyncTask<Void, Integer, Void> {
//
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//
//
//            }
//
//            @SuppressWarnings("deprecation")
//            @Override
//            protected Void doInBackground(Void... params) {
//                ParametrsDB parametrsDB = new ParametrsDB();
//
//                for (int i = 1443; i < list2.size(); i++) {
//                    Log.i("exep i", "doInBackground: "+i);
//
//                    Document doc = null;
//                    try {
//                        doc = Jsoup
//                                .connect(
//                                        "http://poezdato.net"+list2.get(i)[1])
//                                .userAgent(
//                                        "Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6").timeout(36000)
//                                .get();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//
//
//                    Elements imports = doc.select("div[class=table_shadow]");
//                    Elements season = imports.select("td");
//                    Elements urlImp = season.select("a[href]");
//
//
//                    String number_ = "", direction_ = "", timeTo_ = "", timeOff_ = "";
//
//                    // Log.i("poiywpare", "doInBackground: "+imports);
//
//                    int ind = 0;
//
//
//
//                    for (Element src : urlImp) {
//
//                        if(ind==0)
//                        {
//                            Log.i("text", "ground: "+ind+"  "+src.attr("href"));
//                            Log.i("text", "ground: "+src.text());
//
//                            KonstrStation konstrStation = new KonstrStation();
//                            konstrStation.setUrl(src.attr("href"));
//                            konstrStation.setName(list2.get(i)[0]);
//                            parametrsDB.listParametrs().insetrMyProd(konstrStation);
//                            ind++;
//                        }else if(ind==3)
//                        {
//                            ind=0;
//                        }else
//                        {
//                            ind++;
//                        }
//
//
//
//                    }
//                }
//
//
//
//                return null;
//            }
//
//            @Override
//            protected void onProgressUpdate(Integer... values) {
//                super.onProgressUpdate(values);
//            }
//
//            @Override
//            protected void onPostExecute(Void r) {
//
//
//            }
//        }
//        GetDataJSON g = new GetDataJSON();
//        g.execute();
//    }
//
//    public String loadJSONFromAsset(Context context) {
//        String json = null;
//        try {
//            InputStream is = context.getAssets().open("com_trans.json");
//
//            int size = is.available();
//
//            byte[] buffer = new byte[size];
//
//            is.read(buffer);
//
//            is.close();
//
//            json = new String(buffer, "UTF-8");
//
//
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//        return json;
//
//    }
}
