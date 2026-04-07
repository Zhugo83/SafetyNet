package com.example.SafetyNet.service;

import com.example.SafetyNet.model.FireStation;
import com.example.SafetyNet.model.MedicalRecord;
import com.example.SafetyNet.model.Person;
import com.example.SafetyNet.repository.FireStationRepository;
import com.example.SafetyNet.repository.MedicalRecordRepository;
import com.example.SafetyNet.repository.PersonRepository;
import com.example.SafetyNet.service.dto.childAlertDto;
import com.example.SafetyNet.service.dto.floodDto;
import com.example.SafetyNet.service.dto.personInfoDto;
import com.example.SafetyNet.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    ;
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
        for (Person person : persons) {
            if (person.getCity().equals(city)) {
                emails.add(person.getEmail());
            }
        }
        return emails;
    }


    public List<personInfoDto> personInfo(String name, String lastname) {
        List<personInfoDto> Info = new ArrayList<>();
        List<Person> persons = personRepository.findAllPersons();
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAllRecords();
        personInfoDto thisPersonInfo = new personInfoDto();
        for (Person p : persons) {
            for (MedicalRecord md : medicalRecords) {
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

    public List<childAlertDto> childAlert(String address) {

        List<childAlertDto> Info = new ArrayList<>();
        List<Person> persons = personRepository.findAllPersons();
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAllRecords();
        childAlertDto child = new childAlertDto();
        List<String> familyMembers = new ArrayList<>();
        boolean hasChild = false;
        for (Person p : persons) {
            for (MedicalRecord md : medicalRecords) {
                if (p.getAddress().equals(address) && p.getFirstName().equals(md.getFirstName()) && p.getLastName().equals(md.getLastName())) {
                    int age = Utils.birthdayToAge(md.getBirthdate());
                    if (age <= 18) {
                        child.setName(p.getFirstName());
                        child.setLastName(p.getLastName());
                        child.setAge(age);
                        hasChild = true;
                    } else {
                        familyMembers.add(p.getFirstName() + " " + p.getLastName());
                        if (hasChild) {
                            child.setFamilyMembers(familyMembers);
                            Info.add(child);
                        }
                    }
                }
            }
        }
        return Info;

    }

    public List<floodDto> flood(String stations) {

        List<floodDto> Info = new ArrayList<>();
        List<Person> persons = personRepository.findAllPersons();
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAllRecords();
        List<FireStation> fireStations = fireStationRepository.getFireStation();
        floodDto person = new floodDto();
        for (FireStation f : fireStations) {
            if (f.getStation().equals(stations)) {
                for (Person p : persons) {
                    for (MedicalRecord md : medicalRecords) {
                        if (p.getAddress().equals(f.getAddress()) && p.getFirstName().equals(md.getFirstName()) && p.getLastName().equals(md.getLastName())) {
                            int age = Utils.birthdayToAge(md.getBirthdate());
                            person.setName(p.getLastName());
                            person.setPhoneNumber(p.getPhone());
                            person.setAge(age);
                            person.setMedications(md.getMedications());
                            person.setAllergies(md.getAllergies());
                            Info.add(person);
                        }
                    }
                }
            }

        }
        return Info;
    }
}

