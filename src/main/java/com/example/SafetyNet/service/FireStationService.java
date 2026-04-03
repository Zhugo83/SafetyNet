package com.example.SafetyNet.service;

import com.example.SafetyNet.model.FireStation;
import com.example.SafetyNet.model.MedicalRecord;
import com.example.SafetyNet.model.Person;
import com.example.SafetyNet.repository.FireStationRepository;
import com.example.SafetyNet.repository.MedicalRecordRepository;
import com.example.SafetyNet.repository.PersonRepository;
import com.example.SafetyNet.utils.Utils;
import org.springframework.stereotype.Service;


import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDate;
import java.time.Period;

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

    public List<String> fire(String address) {
        List<String> Info = new ArrayList<>();
        List<Person> persons = personRepository.findAllPersons();
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAllRecords();
        List<FireStation> fireStations = fireStationRepository.getFireStation();
        for (FireStation fs : fireStations) {
            for (Person person : persons) {
                for (MedicalRecord md : medicalRecords){
                    if (fs.getAddress().equals(address) && fs.getAddress().equals(person.getAddress()) && person.getFirstName().equals(md.getFirstName()) && person.getLastName().equals(md.getLastName())) {
                        int age = Utils.birthdayToAge(md.getBirthdate());

                        Info.add("Fire Station: " + fs.getStation() + " " + person.getLastName() + " " + person.getPhone() + " " + age + " Medications: " + Arrays.toString(md.getMedications()) + " Allergies: " + Arrays.toString(md.getAllergies()));
                        break;
                    }
                }
            }
        }
        return Info;
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

    public List<String> stationNumber(String stationNumber) {
        List<String> Info = new ArrayList<>();
        List<Person> persons = personRepository.findAllPersons();
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAllRecords();
        List<FireStation> fireStations = fireStationRepository.getFireStation();
        int adults = 0;
        int children = 0;
        for (FireStation fs : fireStations) {
            for (Person person : persons) {
                for (MedicalRecord md : medicalRecords){
                    if (fs.getStation().equals(stationNumber) && fs.getAddress().equals(person.getAddress()) && person.getFirstName().equals(md.getFirstName()) && person.getLastName().equals(md.getLastName())) {
                        Info.add(person.getFirstName() + " " + person.getLastName() + " " + person.getAddress() + " " + person.getPhone());

                        int age = Utils.birthdayToAge(md.getBirthdate());
                        if (age >= 18){
                            adults+=1;
                        } else {
                            children+=1;
                        }
                        break;
                    }
                }
            }
        }
        Info.add("Nombre d'adults: " +  adults + " Nombre d'enfants: " +  children);
        return Info;
    }
}
