package com.bridgelaz.clinic.model;

public class Clinic {

	private int clinicid;
	private String clinicname;
	private String clinicplace;
	
	
	
	public Clinic() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Clinic(int clinicid, String clinicname, String clinicplace) {
		super();
		this.clinicid = clinicid;
		this.clinicname = clinicname;
		this.clinicplace = clinicplace;
	}
	public int getClinicid() {
		return clinicid;
	}
	public void setClinicid(int clinicid) {
		this.clinicid = clinicid;
	}
	public String getClinicname() {
		return clinicname;
	}
	public void setClinicname(String clinicname) {
		this.clinicname = clinicname;
	}
	public String getClinicplace() {
		return clinicplace;
	}
	public void setClinicplace(String clinicplace) {
		this.clinicplace = clinicplace;
	}
	@Override
	public String toString() {
		return "Clinic [clinicid=" + clinicid + ", clinicname=" + clinicname + ", clinicplace=" + clinicplace + "]";
	}
	
	

	
}
