package com.example.SafetyNet.service;

import com.example.SafetyNet.model.FireStation;
import com.example.SafetyNet.model.MedicalRecord;
import com.example.SafetyNet.model.Person;
import com.example.SafetyNet.repository.FireStationRepository;
import com.example.SafetyNet.repository.MedicalRecordRepository;
import com.example.SafetyNet.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;;
    private final FireStationRepository fireStationRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    public PersonService(PersonRepository personRepository, FireStationRepository fireStationRepository, MedicalRecordRepository medicalRecordRepository) {
        this.fireStationRepository = fireStationRepository;
        this.personRepository = personRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public Person addPerson(Person person) {
        return null;
    }

    public Person updatePerson(Person person) {
        return null;
    }

    public Person deletePerson(Person person) {
        return null;
    }

    public List<String> communityEmail(String city) {
        List<String> emails = new ArrayList<>();
        List<Person> persons = personRepository.findAllPersons();
        for (Person person : persons){
            if (person.getCity().equals(city)){
                emails.add(person.getEmail());
            }
        }
        return emails;
    }

    public List<String> childAlert(String address) {
        List<String> Info = new ArrayList<>();
        List<Person> persons = personRepository.findAllPersons();
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAllRecords();
        boolean hasChild = false;
        for (Person person : persons) {
            for (MedicalRecord md : medicalRecords){
                if (person.getFirstName().equals(md.getFirstName()) && person.getLastName().equals(md.getLastName())) {

                    LocalDate currentDate = LocalDate.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    LocalDate birthDate = LocalDate.parse(md.getBirthdate(), formatter);
                    Period period = Period.between(birthDate, currentDate);
                    int age = period.getYears();
                    if (age <= 18){
                        hasChild = true;
                        Info.add(person.getFirstName() + " " + person.getLastName() + "Age: " + age);
                    }
                    else {
                        Info.add(person.getFirstName() + " " + person.getLastName());
                    }
                    break;
                }
            }
        }
        if (hasChild){
            return Info;
        } else {
            return null;
        }
    }
}
