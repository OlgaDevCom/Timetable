package com.dvsmart.timetable.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.dvsmart.timetable.konstr.DirTrans;

/**
 * Created by stanislavtopanov on 12/14/17.
 */



@Database(entities = {Stations.class}, version = 1, exportSchema = false)
public abstract class StationsDB extends RoomDatabase {

   private static volatile StationsDB INSTANCE;

   public abstract LSDirTrans listParametrs();

   public static StationsDB getInstance(Context context) {
      if (INSTANCE == null) {
         synchronized (Stations.class) {
            if (INSTANCE == null) {
               INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                       StationsDB.class, "stations").fallbackToDestructiveMigration()
                       .build();
            }
         }
      }
      return INSTANCE;
   }


}
