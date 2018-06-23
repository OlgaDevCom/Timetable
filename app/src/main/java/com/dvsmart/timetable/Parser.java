//package com.dvsmart.timetable;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.AsyncTask;
//import android.os.Build;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.RequiresApi;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//
//import com.dvsmart.timetable.db.DirDB;
//import com.dvsmart.timetable.db.KonstrDbRecept;
//import com.dvsmart.timetable.db.KonstrRecept;
//import com.dvsmart.timetable.db.OneRowDB;
//import com.dvsmart.timetable.db.ParametrsDB;
//import com.dvsmart.timetable.db.ReceptDB;
//import com.dvsmart.timetable.konstr.KonstrByDir;
//import com.dvsmart.timetable.konstr.KonstrStation;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.common.reflect.TypeToken;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;
//import com.google.gson.Gson;
//import com.huma.room_for_asset.RoomAsset;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.lang.reflect.Type;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//
//
//public class Parser extends AppCompatActivity {
//
//    List<String[]> list2 = new ArrayList<>();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        FirebaseApp.initializeApp(this);
//        setContentView(R.layout.activity_upload_data);
//
//         getLastProduct2();
//
//       // getImageUrl ();
////        String next[] = {};
////        try {
////            CSVReader reader = new CSVReader(new InputStreamReader(getAssets().open("traince_5.csv")));//Specify asset file name
////            //in open();
////            for(;;) {
////                next = reader.readNext();
////                if(next != null) {
////                    list2.add(next);
////                } else {
////                    break;
////                }
////            }
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////        Log.i("poiert", "onCreate: "+list.size());
//
//
//
//    }
//
//
//    @SuppressLint("StaticFieldLeak")
//    private void getImageUrl () {
//        new AsyncTask<Void, Void, Void>() {
//            @Override
//            protected Void doInBackground(Void... voids) {
//
//           ReceptDB receptDB = ReceptDB.getInstance(Parser.this);
//           List<KonstrDbRecept> ls = receptDB.listRecept().getListAllParametrs();
//                FirebaseStorage storage = FirebaseStorage.getInstance();
//                StorageReference storageRef = storage.getReference();
//                Log.i("lsdfdf", "doInBackground: "+ls.size());
////                for (int i = 3855; i < ls.size(); i++) {
////
////                    Bitmap bitmap = getBitmapFromURL(ls.get(i).getImageUrl());
////
////                       if(bitmap != null)
////                       {
////                           StorageReference mountainImagesRef = storageRef.child("img_recepts/"+String.valueOf(i));
////                           ByteArrayOutputStream baos = new ByteArrayOutputStream();
////                           bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
////                           byte[] data = baos.toByteArray();
////
////                           UploadTask uploadTask = mountainImagesRef.putBytes(data);
////                           uploadTask.addOnFailureListener(new OnFailureListener() {
////                               @Override
////                               public void onFailure(@NonNull Exception exception) {
////                                   Log.i("sd", "onFailure: "+exception);
////                               }
////                           }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
////                               @Override
////                               public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
////                                   // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
////                                   Log.i("sdds", "onSuccess: "+taskSnapshot.getDownloadUrl());
////                               }
////                           });
////
////                       }else
////                       {
////                           Log.i("sdds", "Null11: ");
////                       }
////
////
////
////                }
//
//
//
//
//
//                return null;
//            }
//        }.execute();
//    }
//
//    public static Bitmap getBitmapFromURL(String src) {
//        try {
//            URL url = new URL(src);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setDoInput(true);
//            connection.connect();
//            InputStream input = connection.getInputStream();
//            Bitmap myBitmap = BitmapFactory.decodeStream(input);
//            return myBitmap;
//        } catch (IOException e) {
//            // Log exception
//            return null;
//        }
//    }
//
//    public void getLastProduct2() {
//        Log.e("startTAG", "Start");
//
//        class GetDataJSON extends AsyncTask<Void, Integer, Void> {
//
//
//
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//            @SuppressWarnings("deprecation")
//            @Override
//            protected Void doInBackground(Void... params) {
//                ParametrsDB parametrsDB = ParametrsDB.getInstance(Parser.this);
//                DirDB dirDB  = DirDB.getInstance(Parser.this);
//
//                OneRowDB oneRowDB = OneRowDB.getInstance(Parser.this);
//                ReceptDB receptDB = ReceptDB.getInstance(Parser.this);
//                List<KonstrRecept> ls = oneRowDB.listItemDir().getListAllParametrs();
//
//                Log.i("exesi", "doInBackground: "+parametrsDB.isOpen());
//
//
//
//
////                for (int i = 0; i < yourClassList.size(); i++) {
////                    Log.i("sdfsdf", "doInBackground: "+yourClassList.get(i).url);
////
////                }
//
//                for (int i = 0; i < yourClassList.size(); i++) {
//                    Log.i("exep i",  yourClassList.get(i).url+" dond: "+i);
//                    Document doc = null;
//                    try {doc = Jsoup
//                            .connect(
//                                    "http://poezdato.net"+yourClassList.get(i).getUrl())
//                            .userAgent(
//                                    "Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6").timeout(36000)
//                            .get();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//
//                    Elements imports = doc.select("div[class=post]");
//                    Elements season = imports.select("li[class=ingredient]");
//                    Elements imageRec = imports.select("li[class=instruction]");
//                    Elements image = imports.select("img");
//
//
//
//                     ArrayList<String> instr = new ArrayList<>();
//                     ArrayList<String> ingr = new ArrayList<>();
//                    KonstrDbRecept konstrDbRecept = new KonstrDbRecept();
//                    for (Element src : season) {
//                       // Log.i("seasonMeta2 time", src.text());
//                        ingr.add(src.text());
//                    }
//
//                    konstrDbRecept.setIngredient(ingr);
//                    for (Element src : imageRec) {
//
//                         Log.i("imageRec time", src.text());
//                        instr.add(src.text());
//                    }
//                    konstrDbRecept.setInstruction(instr);
//                    for (Element src : image) {
//                        konstrDbRecept.setImageUrl(src.attr("src"));
//                        konstrDbRecept.setTitle(src.attr("title"));
//                        Log.i("image time", src.attr("src"));
//                        Log.i("image time", src.attr("title"));
//                    }
//                  //  receptDB.listRecept().insetrListMyProdItem(konstrDbRecept);
//                }
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
//                ParametrsDB parametrsDB = ParametrsDB.getInstance(Parser.this);
//
//                for (int i = 0; i < list2.size(); i++) {
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
//
//
//                    for (Element src : urlImp) {
//
//                        Log.i("parser", "doInBackground: "+src.text());
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
//}
