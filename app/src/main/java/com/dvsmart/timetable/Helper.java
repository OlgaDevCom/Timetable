package com.dvsmart.timetable;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Helper {

    private Activity activity;

    public Helper(Activity activity) {
        this.activity = activity;
    }


    public void setPreference(String key, String data)
    {
        final  String MyPREFERENCES = "MyPrefs" ;
        SharedPreferences sharedpreferences = activity.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(key, data);
        editor.commit();
    }

    public String getPreference(String key)
    {
        final  String MyPREFERENCES = "MyPrefs" ;
        SharedPreferences sharedpreferences = activity.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        return  sharedpreferences.getString(key, "0");
    }
    public static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) {

        // 1. Convert Map to List of Map
        List<Map.Entry<String, Integer>> list =
                new LinkedList<>(unsortMap.entrySet());

        Collections.sort(list, (o1, o2) -> (o1.getValue()).compareTo(o2.getValue()));

        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
}
