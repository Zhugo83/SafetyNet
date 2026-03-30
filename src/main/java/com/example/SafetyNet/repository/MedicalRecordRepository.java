package com.example.SafetyNet.repository;

import org.springframework.stereotype.Repository;

@Repository
public class MedicalRecordRepository {
    private final DataHandler dataHandler;
    public MedicalRecordRepository(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }
}
