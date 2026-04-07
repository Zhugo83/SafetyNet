package com.example.SafetyNet.controler;

import com.example.SafetyNet.service.PersonService;
import com.example.SafetyNet.service.dto.childAlertDto;
import com.example.SafetyNet.service.dto.floodDto;
import com.example.SafetyNet.service.dto.personInfoDto;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@RestController
public class PersonControler {
    private static final Logger logger = LogManager.getLogger(PersonControler.class);
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
        logger.info("Request sent for ```communityEmail```");
        return personService.communityEmail(city);
    }

    @GetMapping("childAlert")
    public List<childAlertDto> childAlert(@RequestParam(name = "address") String address){
        logger.info("Request sent for ```childAlert```");
        return personService.childAlert(address);
    }

    @GetMapping("personInfo")
    public List<personInfoDto> personInfo(@RequestParam(name = "firstName") String name, @RequestParam(name = "lastName") String lastname) {
        logger.info("Request sent for ```personInfo```");
        return personService.personInfo(name, lastname);
    }

    @GetMapping("flood/stations")
    public List<floodDto> flood(@RequestParam(name = "stations") String stations){
        logger.info("Request sent for ```flood```");
        return personService.flood(stations);
    }
}
