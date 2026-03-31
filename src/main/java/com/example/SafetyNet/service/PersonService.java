package com.example.SafetyNet.service;

import com.example.SafetyNet.model.Person;
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
}
