package com.example.SafetyNet.model;

import java.util.List;

public class Data {
    private List<Persons> Person;
    private List<FireStations> FireStation;
    private List<MedicalRecords> MedicalRecord;
    public List<Persons> getPerson() {
        return Person;
    }
    public void setPerson(List<Persons> person) {
        Person = person;
    }
    public List<FireStations> getFireStation() {
        return FireStation;
    }
    public void setFireStation(List<FireStations> fireStation) {
        FireStation = fireStation;
    }
    public List<MedicalRecords> getMedicalRecord() {
        return MedicalRecord;
    }
    public void setMedicalRecord(List<MedicalRecords> medicalRecord) {
        MedicalRecord = medicalRecord;
    }
}
