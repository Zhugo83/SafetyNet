package com.example.SafetyNet.controler;

import com.example.SafetyNet.model.FireStations;
import com.example.SafetyNet.model.Persons;
import com.example.SafetyNet.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonControler {

    //@Autowired
    private final PersonService personService;

    PersonControler(PersonService personService) {
        this.personService = personService;
    }

    /*@PostMapping("/person")
    public Persons addPerson(@RequestBody Persons person) {
        return personService.addPerson(person);
    }
    @PutMapping("/person")
    public Persons updatePerson(@RequestBody Persons person) {
        return personService.updatePerson(person);
    }

    @DeleteMapping("/person")
    public Persons deletePerson(@RequestBody Persons person) {
        return personService.deletePerson(person);
    }*/

    @GetMapping("communityEmail")
    public List<String> communityEmail(@RequestParam(name = "city") String city){
        return personService.communityEmail(city);
    }
}
