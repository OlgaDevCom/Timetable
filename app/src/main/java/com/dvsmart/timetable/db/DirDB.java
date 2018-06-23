package com.dvsmart.timetable.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.dvsmart.timetable.konstr.KonstrByDir;
import com.dvsmart.timetable.konstr.KonstrStation;

/**
 * Created by stanislavtopanov on 12/14/17.
 */



@Database(entities = {KonstrByDir.class}, version = 1, exportSchema = false)
public abstract class DirDB extends RoomDatabase {

   private static volatile DirDB INSTANCE;
   public abstract ListItemDir listItemDir();


   public static DirDB getInstance(Context context) {
      if (INSTANCE == null) {
         synchronized (DirDB.class) {
            if (INSTANCE == null) {
               INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                       DirDB.class, "myDBdir").fallbackToDestructiveMigration()
                       .build();
            }
         }
      }
      return INSTANCE;
   }


}
