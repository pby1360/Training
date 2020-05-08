package com.example.training;

public class MemoDictionary {
	private String memo;
	private String memoNo;

	public MemoDictionary(String memo, String memoNo)
	{
		this.memo = memo;
		this.memoNo = memoNo;
	}

	public void setMemoNo(String memoNo)
	{
		this.memoNo = memoNo;
	}

	public String getMemoNo()
	{
		return memoNo;
	}
	
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getMemo()	{
		return memo;
	}
	
}
