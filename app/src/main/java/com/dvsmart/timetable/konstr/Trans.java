package com.dvsmart.timetable.konstr;

import com.google.firebase.firestore.IgnoreExtraProperties;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


@IgnoreExtraProperties
public class Trans {


    private String url;
    private String dir;
    // private Map <String, Integer> station;
    private Map<String,Integer> list;
    private Map<String,String> listat;
    private Map<String,String> listto;


    public Trans() {
    }

    public String getUrl() {
        return "http://poezdato.net"+url;
    }

    public Map<String, String> getListat() {
        return new TreeMap<>(listat);
    }

    public void setListat(Map<String, String> listat) {
        this.listat = listat;
    }

    public Map<String, String> getListto() {
        return new TreeMap<>(listto);
    }

    public void setListto(Map<String, String> listto) {
        this.listto = listto;
    }

    //    public Map<String, String> getList_time_at() {
//        return list_time_at;
//    }
//
//    public void setList_time_at(Map<String, String> list_time_at) {
//        this.list_time_at = list_time_at;
//    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Integer> getList() {
        return sortByValue(list);
    }

    public void setList(Map<String, Integer> list) {
        this.list = list;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }



    private static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) {

        // 1. Convert Map to List of Map
        List<Map.Entry<String, Integer>> list =
                new LinkedList<>(unsortMap.entrySet());

        Collections.sort(list, (o1, o2) -> (o1.getValue()).compareTo(o2.getValue()));

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }



}
