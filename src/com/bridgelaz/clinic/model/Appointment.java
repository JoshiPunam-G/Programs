package com.bridgelaz.clinic.model;

import java.util.Date;

public class Appointment {
	private int appid;
	private String appdate;
	private int  appnumber;
	private String time;

	public Appointment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Appointment(int appid, String appdate, int appnumber, String time) {
		super();
		this.appid = appid;
		this.appdate = appdate;
		this.appnumber = appnumber;
		this.time = time;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "Appointment [appid=" + appid + ", appdate=" + appdate + ", appnumber=" + appnumber + ", time=" + time
				+ "]";
	}
	public int getAppid() {
		return appid;
	}
	public void setAppid(int appid) {
		this.appid = appid;
	}
	public String getAppdate() {
		return appdate;
	}
	public void setAppdate(String appdate) {
		this.appdate = appdate;
	}
	public int getAppnumber() {
		return appnumber;
	}
	public void setAppnumber(int appnumber) {
		this.appnumber = appnumber;
	}
	
	
	
	

}
