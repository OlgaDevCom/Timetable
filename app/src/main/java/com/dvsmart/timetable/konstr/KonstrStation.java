package com.dvsmart.timetable.konstr;
//


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
@Entity(tableName = "com_trans")
public class KonstrStation {
    @Exclude


    @NonNull
    @PrimaryKey
    public  String url;
    public String name;

    public KonstrStation() {
    }


    @NonNull
    public String getUrl() {
        return url;
    }

    public void setUrl(@NonNull String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
