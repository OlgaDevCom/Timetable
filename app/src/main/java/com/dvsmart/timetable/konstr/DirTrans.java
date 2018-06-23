package com.dvsmart.timetable.konstr;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.firebase.firestore.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;


@IgnoreExtraProperties
public class DirTrans {


  private String url;
  private int id;
  private String dir;
  //private Map<String, Integer> stations;



    public DirTrans() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public Map<String, Integer> getStations() {
//        return stations;
//    }
//
//    public void setStations(Map<String, Integer> stations) {
//        this.stations = stations;
//    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
}
