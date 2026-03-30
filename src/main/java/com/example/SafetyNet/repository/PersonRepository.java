package com.example.SafetyNet.repository;

import com.example.SafetyNet.model.Persons;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonRepository {
    private final DataHandler dataHandler;
    public PersonRepository(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }

    public List<Persons> findAllPersons() {
        return dataHandler.getData().getPerson();
    }
}
