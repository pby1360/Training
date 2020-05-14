package com.example.training;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Memo {
    @PrimaryKey
    public int mid;

    @ColumnInfo(name = "contents")
    public String contents;

    @ColumnInfo(name = "date")
    public String date;
}
