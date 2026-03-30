package com.example.SafetyNet.model;

import java.util.List;

public class Data {
    private List<Persons> persons;
    private List<FireStations> fireStations;
    private List<MedicalRecords> medicalRecords;
    public List<Persons> getPerson() {
        return persons;
    }
    public void setPerson(List<Persons> person) {
        persons = person;
    }
    public List<FireStations> getFireStation() {
        return fireStations;
    }
    public void setFireStation(List<FireStations> fireStation) {
        fireStations = fireStation;
    }
    public List<MedicalRecords> getMedicalRecord() {
        return medicalRecords;
    }
    public void setMedicalRecord(List<MedicalRecords> medicalRecord) {
        medicalRecords = medicalRecord;
    }
}
