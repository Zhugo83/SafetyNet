package com.example.SafetyNet.repository;

import org.springframework.stereotype.Repository;

@Repository
public class FireStationRepository {
    private final DataHandler dataHandler;
    public FireStationRepository(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }
}
