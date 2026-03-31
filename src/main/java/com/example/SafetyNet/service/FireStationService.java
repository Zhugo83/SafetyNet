package com.example.SafetyNet.service;

import com.example.SafetyNet.model.FireStation;
import com.example.SafetyNet.model.Person;
import com.example.SafetyNet.repository.FireStationRepository;
import com.example.SafetyNet.repository.MedicalRecordRepository;
import com.example.SafetyNet.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FireStationService {

    private final PersonRepository personRepository;
    private final FireStationRepository fireStationRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    public FireStationService(PersonRepository personRepository, FireStationRepository fireStationRepository, MedicalRecordRepository medicalRecordRepository) {
        this.fireStationRepository = fireStationRepository;
        this.personRepository = personRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }
    public FireStation addFireStation(FireStation fireStation) {
        return null;
    }

    public FireStation updateFireStation(FireStation fireStation) {
        return null;
    }

    public FireStation deleteFireStation(FireStation fireStation) {
        return null;
    }

    public FireStation fire(String fireStation) {
        return null;
    }

    public List<String> phoneAlert(String fireStation) {
        List<String> phones = new ArrayList<>();
        List<Person> persons = personRepository.findAllPersons();
        List<FireStation> fireStations = fireStationRepository.getFireStation();
        for (FireStation fs : fireStations) {
            for (Person person : persons) {
                if (fs.getStation().equals(fireStation) && fs.getAddress().equals(person.getAddress())) {
                    phones.add(person.getPhone());
                }
            }
        }
        return phones;
    }
}
