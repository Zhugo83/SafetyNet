package com.example.SafetyNet.model;

import java.util.List;

public class Data {
    private List<Person> persons;
    private List<FireStation> firestations;
    private List<MedicalRecord> medicalrecords;

    public List<Person> getPerson() {
        return persons;
    }
    public void setPerson(List<Person> person) {
        persons = person;
    }
    public List<FireStation> getFireStation() {
        return firestations;
    }
    public void setFireStation(List<FireStation> fireStation) {
        firestations = fireStation;
    }
    public List<MedicalRecord> getMedicalRecord() {
        return medicalrecords;
    }
    public void setMedicalRecord(List<MedicalRecord> medicalRecord) {
        medicalrecords = medicalRecord;
    }
}
