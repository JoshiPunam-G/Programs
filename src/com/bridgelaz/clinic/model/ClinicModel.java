package com.bridgelaz.clinic.model;

import java.util.List;

public class ClinicModel {
List<Doctor> doctor;
List<Patient> patient;
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

@Override
public String toString() {
	return "ClinicModel [doctor=" + doctor + ", patient=" + patient + "]";
}



}
