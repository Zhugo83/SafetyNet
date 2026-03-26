package com.example.SafetyNet.controler;

import com.example.SafetyNet.model.Persons;
import com.example.SafetyNet.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonControler {

    @Autowired
    private PersonService personService;

    @PostMapping
    public Persons addPerson(@RequestBody Persons person) {
        return personService.addPerson(person);
    }
    @PutMapping
    public Persons updatePerson(@RequestBody Persons person) {
        return personService.updatePerson(person);
    }

    @DeleteMapping
    public Persons deletePerson(@RequestBody Persons person) {
        return personService.deletePerson(person);
    }
}
