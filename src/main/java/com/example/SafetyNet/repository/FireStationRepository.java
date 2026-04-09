package com.example.SafetyNet.repository;

import com.example.SafetyNet.model.FireStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FireStationRepository {

    //@Autowired
    private final DataHandler dataHandler;

    public FireStationRepository(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }


    public List<FireStation> findAllStations() {
        return dataHandler.getData().getFireStation();
    }

}
