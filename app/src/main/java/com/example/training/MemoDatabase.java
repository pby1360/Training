package com.example.training;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Memo.class}, version = 1)
public abstract class MemoDatabase extends RoomDatabase {

    private static MemoDatabase INSTANCE;

    public abstract MemoDao memoDao();

    public static MemoDatabase getAppDatabase(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, MemoDatabase.class, "memo-db").build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
