package com.dvsmart.timetable.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.dvsmart.timetable.konstr.KonstrStation;

/**
 * Created by stanislavtopanov on 12/14/17.
 */



@Database(entities = {KonstrStation.class}, version = 1, exportSchema = false)
public abstract class ParametrsDB extends RoomDatabase {

   private static volatile ParametrsDB INSTANCE;

   public abstract ListItemParametrs listParametrs();

//
//   public static ParametrsDB getInstance(Context context) {
//      if (INSTANCE == null) {
//         synchronized (KonstrStation.class) {
//            if (INSTANCE == null) {
//               INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
//                       ParametrsDB.class, "myDBtraince.db").fallbackToDestructiveMigration()
//                       .build();
//            }
//         }
//      }
//      return INSTANCE;
//   }


}
