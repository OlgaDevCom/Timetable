package com.dvsmart.timetable.konstr;
//


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
@Entity(tableName = "com_dir")
public class KonstrByDir {
    @Exclude

    @PrimaryKey (autoGenerate = true)
    public  int id;
    public String station;
    public String coming;
    public String parking;
    public String departure;
    public String in_transit;
    public String url;


    public KonstrByDir() {
    }


    public int getId() {
        return id;
    }

    public void setId( int id) {
        this.id = id;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getComing() {
        return coming;
    }

    public void setComing(String coming) {
        this.coming = coming;
    }

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getIn_transit() {
        return in_transit;
    }

    public void setIn_transit(String in_transit) {
        this.in_transit = in_transit;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
