package com.example.SafetyNet.service;

import com.example.SafetyNet.model.FireStation;
import com.example.SafetyNet.model.MedicalRecord;
import com.example.SafetyNet.model.Person;
import com.example.SafetyNet.repository.FireStationRepository;
import com.example.SafetyNet.repository.MedicalRecordRepository;
import com.example.SafetyNet.repository.PersonRepository;
import com.example.SafetyNet.service.dto.FireDto;
import com.example.SafetyNet.service.dto.FirestationDto;
import com.example.SafetyNet.utils.Utils;
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

    public List<FireDto> fire(String address) {
        List<FireDto> Info = new ArrayList<>();
        List<Person> persons = personRepository.findAllPersons();
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAllRecords();
        List<FireStation> fireStations = fireStationRepository.findAllStations();
        for (FireStation fs : fireStations) {
            for (Person person : persons) {
                for (MedicalRecord md : medicalRecords){
                    if (fs.getAddress().equals(address) && fs.getAddress().equals(person.getAddress()) && person.getFirstName().equals(md.getFirstName()) && person.getLastName().equals(md.getLastName())) {
                        FireDto information = new FireDto();
                        information.setNumstation(fs.getStation());
                        information.setPhoneNumber(person.getPhone());
                        information.setLastName(person.getLastName());
                        information.setMedications(md.getMedications());
                        information.setAllergies(md.getAllergies());
                        information.setAge(Utils.birthdayToAge(md.getBirthdate()));
                        Info.add(information);

                    }
                }
            }
        }
        return Info;
    }

    public List<String> phoneAlert(String fireStation) {
        List<String> phones = new ArrayList<>();
        List<Person> persons = personRepository.findAllPersons();
        List<FireStation> fireStations = fireStationRepository.findAllStations();
        for (FireStation fs : fireStations) {
            for (Person person : persons) {
                if (fs.getStation().equals(fireStation) && fs.getAddress().equals(person.getAddress())) {
                    phones.add(person.getPhone());
                }
            }
        }
        return phones;
    }

    public List<FirestationDto> stationNumber(String stationNumber) {
        List<FirestationDto> Info = new ArrayList<>();
        List<Person> persons = personRepository.findAllPersons();
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAllRecords();
        List<FireStation> fireStations = fireStationRepository.findAllStations();
        int adults = 0;
        int children = 0;
        for (FireStation fs : fireStations) {
            for (Person person : persons) {
                for (MedicalRecord md : medicalRecords){
                    if (fs.getStation().equals(stationNumber) && fs.getAddress().equals(person.getAddress()) && person.getFirstName().equals(md.getFirstName()) && person.getLastName().equals(md.getLastName())) {
                        FirestationDto individual = new FirestationDto();
                        individual.setName(person.getFirstName());
                        individual.setLastName(person.getLastName());
                        individual.setPhone(person.getPhone());
                        individual.setAddress(person.getAddress());
                        individual.setAge(Utils.birthdayToAge(md.getBirthdate()));
                        int age = Utils.birthdayToAge(md.getBirthdate());
                        if (age >= 18){
                            adults+=1;
                        } else {
                            children+=1;
                        }
                        Info.add(individual);
                    }
                }
            }
        }
        return Info;
    }
}
