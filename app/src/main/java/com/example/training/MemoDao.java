package com.example.training;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MemoDao {
    @Query("SELECT * FROM MEMO")
    List<Memo> getAll();

    @Insert
    void insertAll(Memo...memos);

}
