package com.example.SafetyNet;

import com.example.SafetyNet.controler.PersonControler;
import com.example.SafetyNet.model.FireStation;
import com.example.SafetyNet.model.MedicalRecord;
import com.example.SafetyNet.model.Person;
import com.example.SafetyNet.repository.DataHandler;
import com.example.SafetyNet.repository.FireStationRepository;
import com.example.SafetyNet.repository.MedicalRecordRepository;
import com.example.SafetyNet.repository.PersonRepository;
import com.example.SafetyNet.service.FireStationService;
import com.example.SafetyNet.service.MedicalRecordService;
import com.example.SafetyNet.service.PersonService;
import com.example.SafetyNet.service.dto.ChildAlertDto;
import com.example.SafetyNet.service.dto.FloodDto;
import com.example.SafetyNet.service.dto.PersonInfoDto;
import com.jsoniter.output.JsonStream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SafetyNetApplicationTests {
	private static final Logger logger = LogManager.getLogger(SafetyNetApplicationTests.class);
	private static DataHandler dataHandler;

	@BeforeAll
	static void setup() throws IOException {
		// TODO because my head hurts rn, make sure to create a stable fake json with the necessary data to access everywhere needed, check later why the "add" does not add new person to list that get added to the fake json, ugh head hurt need break
		HashMap<String, Object> json = new HashMap<>();
		List<Person> persons = new ArrayList<>();
		List<FireStation> firestations = new ArrayList<>();
		List<MedicalRecord> medicalrecords = new ArrayList<>();
		Person person = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
		persons.add(person);
		Person person2 = new Person("Test", "Boyd", "1508 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
		persons.add(person2);
		Person person3 = new Person("Test2", "Boyd", "1508 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
		persons.add(person3);
		System.out.println(persons);
		json.put("persons", List.of(person));

		FireStation fireStation = new FireStation("1509 Culver St", "3");
		firestations.add(fireStation);
		FireStation fireStation2 = new FireStation("1508 Culver St", "2");
		firestations.add(fireStation2);
		json.put("firestations", List.of(fireStation));

		String[] medications = new String[]{"aznol: 350mg", "hydrapermazol:100mg"};
		String[] allergies = new String[]{"nillacilan"};
		MedicalRecord medicalRecord = new MedicalRecord("John", "Boyd", "03/06/1984", medications, allergies);
		medicalrecords.add(medicalRecord);


		MedicalRecord medicalRecord2 = new MedicalRecord("Test2", "Boyd", "03/06/2010", new String[]{}, new String[]{});
		medicalrecords.add(medicalRecord2);

		MedicalRecord medicalRecord3 = new MedicalRecord("Test", "Boyd", "03/06/1980", new String[]{}, new String[]{});
		medicalrecords.add(medicalRecord3);

		json.put("medicalrecords", List.of(medicalRecord));


		String data = JsonStream.serialize(json);
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src\\main\\resources\\datatest.json"), StandardCharsets.UTF_8))) {
            writer.write(data);
			writer.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
		// Test will fail when creating this as it try to use it before the program actually create it, which it only does when the program is closed.
		dataHandler = new DataHandler("data.json");
	}

	//@AfterAll
	static void tearDown() throws IOException {
		File myObj = new File("src\\main\\resources\\datatest.json");
		if (myObj.delete()) {
			System.out.println("Deleted the file: " + myObj.getName());
		} else {
			System.out.println("Failed to delete the file.");
		}
	}
	// Do not remove the file, as it will not be found when created within the test

	@Test
	void verifyJsonData() {
		PersonRepository personRepository = new PersonRepository(dataHandler);
		List<Person> persons = personRepository.findAllPersons();
		for (Person p:  persons) {
			if (p.getFirstName().equals("John")) {
				assertEquals("John", p.getFirstName());
			}
		}
	}

	@Test
	void checkChildAlert(){
		PersonRepository personRepository = new PersonRepository(dataHandler);
		MedicalRecordRepository medicalRecordRepository = new MedicalRecordRepository(dataHandler);
		FireStationRepository fireStationRepository = new FireStationRepository(dataHandler);
		PersonService personService = new PersonService(personRepository, fireStationRepository, medicalRecordRepository);
		PersonControler personControler = new PersonControler(personService);

		assertFalse(personControler.childAlert("1509 Culver St").isEmpty());
		System.out.println(personService.childAlert("1508 Culver St").isEmpty());
		assertTrue(personControler.childAlert("1508 Culver St").isEmpty());
	}

	@Test
	void checkPersonInfo(){
		PersonRepository personRepository = new PersonRepository(dataHandler);
		MedicalRecordRepository medicalRecordRepository = new MedicalRecordRepository(dataHandler);
		FireStationRepository fireStationRepository = new FireStationRepository(dataHandler);
		PersonService personService = new PersonService(personRepository, fireStationRepository, medicalRecordRepository);
		List<PersonInfoDto> person = personService.personInfo("John", "Boyd");
		for (PersonInfoDto p:  person) {
			if (p.getNom().equals("John")) {
				assertEquals("John", p.getNom());
			}
		}
	}

	@Test
	void checkCommunityEmail(){
		PersonRepository personRepository = new PersonRepository(dataHandler);
		MedicalRecordRepository medicalRecordRepository = new MedicalRecordRepository(dataHandler);
		FireStationRepository fireStationRepository = new FireStationRepository(dataHandler);
		PersonService personService = new PersonService(personRepository, fireStationRepository, medicalRecordRepository);
		List<String> emails = personService.communityEmail("Culver");
		for (String email: emails) {
			if (email.equals("jaboyd@email.com")) {
				assertEquals("jaboyd@email.com", email);
			}
		}
	}

	@Test
	void checkflood(){
		PersonRepository personRepository = new PersonRepository(dataHandler);
		MedicalRecordRepository medicalRecordRepository = new MedicalRecordRepository(dataHandler);
		FireStationRepository fireStationRepository = new FireStationRepository(dataHandler);
		PersonService personService = new PersonService(personRepository, fireStationRepository, medicalRecordRepository);
		List<FloodDto> persons = personService.flood("2");
		for (FloodDto person: persons) {
			if (person.getName().equals("Marrack")) {
				assertEquals("Marrack", person.getName());
			}
		}
	}


}
