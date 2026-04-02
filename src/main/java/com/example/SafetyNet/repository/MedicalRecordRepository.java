package com.example.SafetyNet.repository;

import com.example.SafetyNet.model.MedicalRecord;
import com.example.SafetyNet.model.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicalRecordRepository {
    private final DataHandler dataHandler;
    public MedicalRecordRepository(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }

    public List<MedicalRecord> findAllRecords() {
        return dataHandler.getData().getMedicalRecord();
    }
}
