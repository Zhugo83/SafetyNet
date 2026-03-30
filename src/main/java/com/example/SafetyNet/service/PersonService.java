package com.example.SafetyNet.service;

import com.example.SafetyNet.model.FireStations;
import com.example.SafetyNet.model.MedicalRecords;
import com.example.SafetyNet.model.Persons;
import com.example.SafetyNet.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Persons addPerson(Persons person) {
        return null;
    }

    public Persons updatePerson(Persons person) {
        return null;
    }

    public Persons deletePerson(Persons person) {
        return null;
    }

    public List<String> communityEmail(String city) {
        List<String> emails = new ArrayList<>();
        List<Persons> persons = personRepository.findAllPersons();
        for (Persons person : persons){
            if (person.getCity().equals(city)){
                emails.add(person.getEmail());
            }
        }
        return emails;
    }
}
