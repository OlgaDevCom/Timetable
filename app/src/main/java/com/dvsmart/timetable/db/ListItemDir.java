package com.dvsmart.timetable.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.dvsmart.timetable.konstr.KonstrByDir;
import com.dvsmart.timetable.konstr.KonstrStation;

import java.util.List;


/**
 * Created by stanislavtopanov on 12/14/17.
 */
@Dao
public interface ListItemDir {


    @Query("SELECT COUNT(*) from com_dir")
    int countProd();

    @Query("SELECT * FROM com_dir ORDER BY url")
    List<KonstrByDir> getListAllParametrs();




    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insetrListMyProd(List<KonstrByDir> parametrs);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insetrListMyProdItem(KonstrByDir parametrs);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insetrMyProd(KonstrByDir parametrs);




}
