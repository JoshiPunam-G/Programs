package com.bridgelaz.clinic.model;

public class Patient {
private String patientName;
private int patientId;
private long mobileNo;
private int age;



public Patient(String patientName, int patientId, long mobileNo, int age) {
	super();
	this.patientName = patientName;
	this.patientId = patientId;
	this.mobileNo = mobileNo;
	this.age = age;
}
public String getPatientName() {
	return patientName;
}
public Patient() {
	super();
	// TODO Auto-generated constructor stub
}
public void setPatientName(String patientName) {
	this.patientName = patientName;
}
public int getPatientId() {
	return patientId;
}
public void setPatientId(int patientId) {
	this.patientId = patientId;
}
public long getMobileNo() {
	return mobileNo;
}
public void setMobileNo(long mobileNo) {
	this.mobileNo = mobileNo;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}


@Override
public String toString() {
	return "Patient [patientName=" + patientName + ", patientId=" + patientId + ", mobileNo=" + mobileNo + ", age="
			+ age + "]";
}


}
