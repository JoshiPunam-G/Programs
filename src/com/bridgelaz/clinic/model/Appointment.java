package com.bridgelaz.clinic.model;

import java.util.Date;

public class Appointment {
		private String dname;
		private String pname;
		private String date;
		private int count;
		
		
		public String getDname() {
			return dname;
		}
		public void setDname(String dname) {
			this.dname = dname;
		}
		public String getPname() {
			return pname;
		}
		public void setPname(String pname) {
			this.pname = pname;
		}
		
		public Appointment() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Appointment(String dname, String pname, String date, int count) {
			super();
			this.dname = dname;
			this.pname = pname;
			this.date = date;
			this.count = count;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		@Override
		public String toString() {
			return "Appointment [dname=" + dname + ", pname=" + pname + ", date=" + date + ", count=" + count + "]";
		}
	
		
	
	
	
	

}
