package com.example.SafetyNet.service;

import com.example.SafetyNet.model.MedicalRecord;
import com.example.SafetyNet.model.Person;
import com.example.SafetyNet.repository.FireStationRepository;
import com.example.SafetyNet.repository.MedicalRecordRepository;
import com.example.SafetyNet.repository.PersonRepository;
import com.example.SafetyNet.service.dto.personInfoDto;
import com.example.SafetyNet.utils.Utils;
import org.springframework.stereotype.Service;

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

                    int age = Utils.birthdayToAge(md.getBirthdate());
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

    public List<personInfoDto> personInfo(String name, String lastname) {
        List<personInfoDto> Info = new ArrayList<>();
        List<Person> persons = personRepository.findAllPersons();
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAllRecords();
        personInfoDto thisPersonInfo = new personInfoDto();
        for (Person p : persons){
            for (MedicalRecord md : medicalRecords){
                if (p.getFirstName().equals(name) && p.getLastName().equals(lastname) && p.getFirstName().equals(md.getFirstName()) && p.getLastName().equals(md.getLastName())) {
                    thisPersonInfo.setNom(p.getLastName());
                    thisPersonInfo.setAge(Utils.birthdayToAge(md.getBirthdate()));
                    thisPersonInfo.setAddress(p.getAddress());
                    thisPersonInfo.setEmail(p.getEmail());
                    thisPersonInfo.setAllergies(md.getAllergies());
                    thisPersonInfo.setMedications(md.getMedications());
                    Info.add(thisPersonInfo);
                }
            }
        }
        return Info;
    }
}
