package com.example.training;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "memo")
public class Memo {

    @PrimaryKey(autoGenerate = true)
    public int mid;

    @ColumnInfo(name = "contents")
    public String contents;

    @ColumnInfo(name = "date")
    public String date;

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

        public Memo(String contents, String date) {
        this.contents = contents;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Memo{" +
                "mid=" + mid +
                ", contents='" + contents + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}


