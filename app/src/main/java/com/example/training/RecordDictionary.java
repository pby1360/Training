package com.example.training;

public class RecordDictionary {
    private String recordNo;
    private String recordTime;

    public String getRecordNo() {
        return recordNo;
    }

    public void setRecordNo(String recordNo) {
        this.recordNo = recordNo;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public RecordDictionary(String recordNo, String recordTime) {
        this.recordNo = recordNo;
        this.recordTime = recordTime;
    }
}
