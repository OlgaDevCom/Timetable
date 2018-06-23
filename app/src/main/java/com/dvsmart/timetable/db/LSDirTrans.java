package com.dvsmart.timetable.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.dvsmart.timetable.konstr.DirTrans;

import java.util.List;

/**
 * Created by stanislavtopanov on 12/14/17.
 */
@Dao

public interface LSDirTrans {



//    @Query("SELECT * FROM KonstrTime")
//    List<KonstrTime> getListAllParametrs();

    @Query("SELECT * FROM Stations")
    Stations getParametrById();





    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insetrItemParametrs(List<Stations> parametrs);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insetrStat(Stations parametrs);

    @Query("DELETE FROM Stations")
    void all_remove_item();


    @Delete
    void deleteParametrs(Stations parametrs);



}
