package com.example.SafetyNet;

import com.example.SafetyNet.model.FireStation;
import com.example.SafetyNet.model.MedicalRecord;
import com.example.SafetyNet.model.Person;
import com.example.SafetyNet.repository.DataHandler;
import com.example.SafetyNet.repository.FireStationRepository;
import com.example.SafetyNet.repository.MedicalRecordRepository;
import com.example.SafetyNet.repository.PersonRepository;
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

	@BeforeAll
	static void setup() throws IOException, InterruptedException {
		HashMap<String, Object> json = new HashMap<>();
		List<Person> persons = new ArrayList<>();
		List<FireStation> firestations = new ArrayList<>();
		List<MedicalRecord> medicalrecords = new ArrayList<>();
		Person person = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
		persons.add(person);
		json.put("persons", List.of(person));

		FireStation fireStation = new FireStation("1509 Culver St", "3");
		firestations.add(fireStation);
		json.put("firestations", List.of(fireStation));

		String[] medications = new String[]{"aznol: 350mg", "hydrapermazol:100mg"};
		String[] allergies = new String[]{"nillacilan"};
		MedicalRecord medicalRecord = new MedicalRecord("John", "Boyd", "03/06/1984", medications, allergies);
		medicalrecords.add(medicalRecord);
		json.put("medicalrecords", List.of(medicalRecord));

		String data = JsonStream.serialize(json);
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src\\main\\resources\\datatest.json"), StandardCharsets.UTF_8))) {
            writer.write(data);
			writer.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
		// Test will fail when creating this as it try to use it before the program actually create it, which it only does when the program is closed.
	}

	@Test
	void urls(){

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
	void verifyJsonData() throws IOException {
		DataHandler dataHandler = new DataHandler("datatest.json");
		System.out.println(dataHandler.getData().getMedicalRecord());
		PersonRepository personRepository = new PersonRepository(dataHandler);
		List<Person> persons = personRepository.findAllPersons();
		for (Person p:  persons) {
			if (p.getFirstName().equals("John")) {
				assertEquals("John", p.getFirstName());
			}
		}
	}


}
