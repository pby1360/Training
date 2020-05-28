package com.example.training;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MemoDao {
    @Query("SELECT * FROM MEMO")
    LiveData<List<Memo>> getAll();

    @Query("SELECT * FROM MEMO WHERE date = :date")
    LiveData<List<Memo>> getMemo(String date);

    @Insert
    void insert(Memo memo);

    @Update
    void update(Memo memo);

    @Delete
    void delete(Memo memo);

    @Query("DELETE FROM MEMO")
    void deleteAll();

}
