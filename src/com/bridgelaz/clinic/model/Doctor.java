package com.bridgelaz.clinic.model;

public class Doctor {
	private String doctorName;
	private int doctorId;
	private String specialization;
	private String availability;
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	
	
	@Override
	public String toString() {
		return "Doctor [doctorName=" + doctorName + ", doctorId=" + doctorId + ", specialization=" + specialization
				+ ", availability=" + availability + "]";
	}
	
	
	

}
