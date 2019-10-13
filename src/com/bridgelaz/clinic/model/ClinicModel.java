package com.bridgelaz.clinic.model;

import java.util.List;

public class ClinicModel {
List<Doctor> doctor;
List<Patient> patient;
List<User> user;
List<Clinic> clinic;
List<Appointment> appointement;

public List<Doctor> getDoctor() {
	return doctor;
}
public void setDoctor(List<Doctor> doctor) {
	this.doctor = doctor;
}
public List<Patient> getPatient() {
	return patient;
}
public void setPatient(List<Patient> patient) {
	this.patient = patient;
}
public List<User> getUser() {
	return user;
}
public void setUser(List<User> user) {
	this.user = user;
}
public List<Clinic> getClinic() {
	return clinic;
}
public void setClinic(List<Clinic> clinic) {
	this.clinic = clinic;
}
public List<Appointment> getAppointement() {
	return appointement;
}
public void setAppointement(List<Appointment> appointement) {
	this.appointement = appointement;
}
@Override
public String toString() {
	return "ClinicModel [doctor=" + doctor + ", patient=" + patient + ", user=" + user + ", clinic=" + clinic
			+ ", appointement=" + appointement + "]";
}




}
