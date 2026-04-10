package com.example.SafetyNet.service;

import com.example.SafetyNet.model.FireStation;
import com.example.SafetyNet.model.MedicalRecord;
import com.example.SafetyNet.model.Person;
import com.example.SafetyNet.repository.FireStationRepository;
import com.example.SafetyNet.repository.MedicalRecordRepository;
import com.example.SafetyNet.repository.PersonRepository;
import com.example.SafetyNet.service.dto.ChildAlertDto;
import com.example.SafetyNet.service.dto.FloodDto;
import com.example.SafetyNet.service.dto.PersonInfoDto;
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


    public List<PersonInfoDto> personInfo(String name, String lastname) {
        List<PersonInfoDto> Info = new ArrayList<>();
        List<Person> persons = personRepository.findAllPersons();
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAllRecords();
        for (Person p : persons) {
            for (MedicalRecord md : medicalRecords) {
                if (p.getFirstName().equals(name) && p.getLastName().equals(lastname) && p.getFirstName().equals(md.getFirstName()) && p.getLastName().equals(md.getLastName())) {
                    PersonInfoDto thisPersonInfo = new PersonInfoDto();
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

    public List<ChildAlertDto> childAlert(String address) {

        List<ChildAlertDto> info = new ArrayList<>();
        List<Person> persons = personRepository.findAllPersons();
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAllRecords();
        List<String> familyMembers = new ArrayList<>();
        for (Person p : persons) {
            for (MedicalRecord md : medicalRecords) {
                if (p.getAddress().equals(address) && p.getFirstName().equals(md.getFirstName()) && p.getLastName().equals(md.getLastName())) {
                    ChildAlertDto child = new ChildAlertDto();
                    int age = Utils.birthdayToAge(md.getBirthdate());
                    if (age <= 18) {
                        child.setName(p.getFirstName());
                        child.setLastName(p.getLastName());
                        child.setAge(age);
                        child.setFamilyMembers(familyMembers);
                        info.add(child);
                    } else {
                        familyMembers.add(p.getFirstName() + " " + p.getLastName());
                    }
                }
            }
        }
        return info;

    }

    public List<FloodDto> flood(String stations) {

        List<FloodDto> Info = new ArrayList<>();
        List<Person> persons = personRepository.findAllPersons();
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAllRecords();
        List<FireStation> fireStations = fireStationRepository.findAllStations();
        for (FireStation f : fireStations) {
            for (Person p : persons) {
                for (MedicalRecord md : medicalRecords) {
                    if (f.getStation().equals(stations) && p.getAddress().equals(f.getAddress()) && p.getFirstName().equals(md.getFirstName()) && p.getLastName().equals(md.getLastName())) {
                        FloodDto person = new FloodDto();
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
        return Info;
    }
}

