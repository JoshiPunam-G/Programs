package com.bridgelaz.clinic.model;

public class Doctor {
	private String doctorName;
	private int doctorId;
	private String specialization;
	private String availability;
	
	
	
	public Doctor(String doctorName, int doctorId, String specialization, String availability) {
		super();
		this.doctorName = doctorName;
		this.doctorId = doctorId;
		this.specialization = specialization;
		this.availability = availability;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public Doctor() {
		super();
		// TODO Auto-generated constructor stub
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
