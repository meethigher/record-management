package com.domain;

public class Record {
	private int id;
	private String name;
	private String title;
	private String datetime;
	private String place;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public Record() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Record(int id, String name, String title, String datetime, String place) {
		super();
		this.id = id;
		this.name = name;
		this.title = title;
		this.datetime = datetime;
		this.place = place;
	}
}
