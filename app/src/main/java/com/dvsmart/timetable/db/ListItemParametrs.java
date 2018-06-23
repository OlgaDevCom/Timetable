package com.dvsmart.timetable.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.dvsmart.timetable.konstr.KonstrStation;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by stanislavtopanov on 12/14/17.
 */
@Dao
public interface ListItemParametrs {


    @Query("SELECT COUNT(*) from com_trans")
    int countProd();

    @Query("SELECT * FROM com_trans ORDER BY name")
    List<KonstrStation> getListAllParametrs();




    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insetrListMyProd(List<KonstrStation> parametrs);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insetrListMyProdItem(KonstrStation parametrs);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insetrMyProd(KonstrStation parametrs);




}
