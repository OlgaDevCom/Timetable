package com.dvsmart.timetable.db;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.IgnoreExtraProperties;


@IgnoreExtraProperties
@Entity
public class Stations {

    @Exclude


    @NonNull
    @PrimaryKey
    private String station;


    public Stations() {
    }

    public Stations(String station) {
        this.station = station;
    }


    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }
}
