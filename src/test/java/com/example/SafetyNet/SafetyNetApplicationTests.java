package com.example.SafetyNet;

import com.example.SafetyNet.model.FireStation;
import com.example.SafetyNet.model.MedicalRecord;
import com.example.SafetyNet.model.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootTest
class SafetyNetApplicationTests {
	private static final Logger logger = LogManager.getLogger(SafetyNetApplicationTests.class);
	private static List<Object> json;

	@BeforeAll
	static void setup() {
		json = new ArrayList<>();
		List<Person> persons = new ArrayList<>();
		List<FireStation> firestations = new ArrayList<>();
		List<MedicalRecord> medicalrecords = new ArrayList<>();
		Person person = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
		persons.add(person);
		json.add(persons);

		FireStation fireStation = new FireStation("1509 Culver St", "3");
		firestations.add(fireStation);
		json.add(firestations);

		String[] medications = new String[]{"aznol: 350mg", "hydrapermazol:100mg"};
		String[] allergies = new String[]{"nillacilan"};
		MedicalRecord medicalRecord = new MedicalRecord("John", "Boyd", "03/06/1984", medications, allergies);

		medicalrecords.add(medicalRecord);
		json.add(medicalrecords);
	}

	@Test
	void urls(){

	}

	@Test
	void verifyJsonData(){
		
	}

}
