package com.example.training;

public class MemoDictionary {
	private int id;
	private String contents;
	private String date;

	public MemoDictionary(int id, String contents, String date)
	{
		this.id = id;
		this.contents = contents;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
}
